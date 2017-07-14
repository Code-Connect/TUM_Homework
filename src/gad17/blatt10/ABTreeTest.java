package gad17.blatt10;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

public class ABTreeTest {
    private ABTree test;

    private Object getField(String key) throws Exception {
        Field field = test.getClass().getDeclaredField(key);
        field.setAccessible(true);
        return field.get(test);
    }

    private Object getField(Object o, String key) throws Exception {
        Field field = o.getClass().getDeclaredField(key);
        field.setAccessible(true);
        return field.get(o);
    }

    private void setField(String key, Object value) throws Exception {
        Field field = test.getClass().getDeclaredField(key);
        field.setAccessible(true);
        field.set(test, value);
    }

    private ABTree.ABTreeNode buildNodeHeight(int key, int height) throws Exception {
        return buildNodeHeight(key, height, height);
    }

    private ABTree.ABTreeNode buildNodeHeight(int key, int height1, int height2) throws Exception {
        return buildNodeHeight(key, height1, height2,
                height -> (int) Math.pow(height, height)); //TODO only works with height <= 4
    }

    private ABTree.ABTreeNode buildNodeHeight(int key, int height1, int height2, Function<Integer, Integer> delta) throws Exception {
        if (height1 <= 1 || height2 <= 1)
            return (ABTree.ABTreeNode) getConstructor(int.class).newInstance(test, key);

        Class<?> node = Class.forName(getPackage() + ".ABTree$ABTreeNode");
        return (ABTree.ABTreeNode) getConstructor(int.class, node, node).newInstance(test, key,
                buildNodeHeight(key - delta.apply(height1), height1 - 1),
                buildNodeHeight(key + delta.apply(height2), height2 - 1));
    }

    private ABTree.ABTreeNode buildNode(ABTree.ABTreeNode[] children, Integer... keys) throws Exception {
        return (ABTree.ABTreeNode) getConstructor(new Class[]{ArrayList.class, ArrayList.class})
                .newInstance(test, new ArrayList<>(Arrays.asList(keys)),
                        new ArrayList<>(Arrays.asList(children)));
    }

    private ABTree.ABTreeNode buildNode(Integer... keys) throws Exception {
        ABTree.ABTreeNode[] leafs = new ABTree.ABTreeNode[keys.length + 1];
        for (int i = 0; i < keys.length + 1; i++)
            leafs[i] = ((ABTree.ABTreeNode) getConstructor("ABTreeLeaf", new Class[0]).newInstance(test));
        return buildNode(leafs, keys);
    }

    private Constructor<?> getConstructor(Class... parameters) throws ClassNotFoundException, NoSuchMethodException {
        return getConstructor("ABTreeInnerNode", parameters);
    }

    private Constructor<?> getConstructor(String name, Class... parameters) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> innerClazz = Class.forName(getPackage() + ".ABTree$" + name);
        ArrayList<Class> ts = new ArrayList(Arrays.asList(parameters));
        ts.add(0, test.getClass());
        Class[] param = ts.toArray(new Class[0]);

        Constructor<?> constructor = innerClazz.getDeclaredConstructor(param);
        constructor.setAccessible(true);
        return constructor;
    }

    private String getPackage() {
        return test.getClass().getPackage().toString().replace("package ", "");
    }

    private void assertTree(ABTree.ABTreeNode expected) {
        Assert.assertEquals(dot(expected), test.dot());
    }

    String dot(ABTree.ABTreeNode root) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n");
        sb.append("\tnode [shape=record];\n");
        if (root != null)
            root.dot(sb, 0);
        sb.append("}");
        return sb.toString();
    }

    private void insert(int... keys) {
        for (int key : keys) test.insert(key);
    }

    private String getString(ABTree node) {
        return node.dot();
    }

    @Before
    public void setUp() throws Exception {
        test = new ABTree(2, 4);
    }

    @Test(expected = Exception.class)
    public void constructor_inputA_invalid_throwsException() throws Exception {
        new ABTree(1, 10);
    }

    @Test(expected = Exception.class)
    public void constructor_inputB_invalid_throwsException() throws Exception {
        new ABTree(2, 2);
    }

    @Test
    public void constructor_inputA_invalid_Random_throwsException() throws Exception {
        int a = (int) (1 - Math.random() * Integer.MAX_VALUE);
        try {
            new ABTree(a, a * 2);
            Assert.fail("a invalid: " + a);
        } catch (Exception e) {
        }
    }

    @Test
    public void constructor_inputB_invalid_Random_throwsException() throws Exception {
        int a = (int) (2 + Math.random() * Integer.MAX_VALUE);
        int b = a * 2 - 2;
        try {
            new ABTree(a, b);
            Assert.fail("b invalid: " + b);
        } catch (Exception e) {
        }
    }

    @Test
    public void constructor_input2_3_thenSavesAB() throws Exception {
        test = new ABTree(2, 3);
        Assert.assertEquals(2, getField("a"));
        Assert.assertEquals(3, getField("b"));
    }

    @Test
    public void constructor_inputRandom_thenSavesAB() throws Exception {
        Random r = new Random();
        int a = 2 + r.nextInt(Integer.MAX_VALUE / 3);
        int b = a * 2 + r.nextInt(Integer.MAX_VALUE - 2 * a);
        test = new ABTree(a, b);
        Assert.assertEquals(a, getField("a"));
        Assert.assertEquals(b, getField("b"));
    }


    @Test
    public void height_givenEmpty_returns0() throws Exception {
        Assert.assertEquals(0, test.height());
    }

    @Test
    public void height_givenEmptyRoot_returns1() throws Exception {
        setField("root", buildNodeHeight(0, 1));
        Assert.assertEquals(1, test.height());
    }

    @Test
    public void height_givenRootHeight1_returns2() throws Exception {
        setField("root", buildNodeHeight(0, 2));
        Assert.assertEquals(2, test.height());
    }

    @Test
    public void height_givenRootHeightRandom() throws Exception {
        int height = 1 + new Random().nextInt(19);
        setField("root", buildNodeHeight(0, height));
        Assert.assertEquals(height, test.height());
    }

    @Test
    public void validAB_givenEmpty_returnsFalse() throws Exception {
        Assert.assertFalse(test.validAB());
    }

    @Test
    public void validAB__givenDifferentHeights_returnsTrue() throws Exception {
        setField("root", buildNodeHeight(0, 2, 3));
        Assert.assertFalse(getString(test), test.validAB());
    }

    @Test
    public void validAB_givenSameHeights_returnsTrue() throws Exception {
        setField("root", buildNodeHeight(0, 4));
        Assert.assertTrue(getString(test), test.validAB());
    }

    @Test
    public void validAB_givenNotEnoughChildren_returnsFalse() throws Exception {
        test = new ABTree(3, 10);
        setField("root", buildNodeHeight(0, 2));
        Assert.assertFalse(getString(test), test.validAB());
    }

    @Test
    public void validAB_givenOnlyRootNotEnoughChildren_returnsTrue() throws Exception {
        test = new ABTree(3, 10);
        setField("root", buildNodeHeight(0, 1));
        Assert.assertTrue(getString(test), test.validAB());
    }

    @Test
    public void validAB_givenToManyChildren_returnsFalse() throws Exception {
        Integer[] keys = {-4, -2, 2, 4};
        ABTree.ABTreeNode[] children = {buildNode(-5),
                buildNode(-3), buildNode(0), buildNode(3),
                buildNode(5)};
        setField("root", buildNode(children, keys));
        Assert.assertFalse(getString(test), test.validAB());
    }

    @Test
    public void validAB_givenBChildren_returnsTrue() throws Exception {
        Integer[] keys = {-4, -2, 2};
        ABTree.ABTreeNode[] children = {buildNode(-5),
                buildNode(-3), buildNode(0), buildNode(3)};
        setField("root", buildNode(children, keys));
        Assert.assertTrue(getString(test), test.validAB());
    }

    @Test
    public void validAB_givenKeysNotSorted_returnsFalse() throws Exception {
        Integer[] keys = {-4, 2, -2};
        ABTree.ABTreeNode[] children = {buildNode(-5),
                buildNode(-3), buildNode(0), buildNode(3)};
        setField("root", buildNode(children, keys));
        Assert.assertFalse(getString(test), test.validAB());
    }

    @Test
    public void validAB_given1ValidAnd1InValidChild_returnsFalse() throws Exception {
        ABTree.ABTreeNode[] children = {
                buildNode(new ABTree.ABTreeNode[]{buildNode(-10), buildNode(-2)}, -5),
                buildNode(new ABTree.ABTreeNode[]{buildNode(2), buildNode(10), buildNode(20)}, 15, 5),
        };
        setField("root", buildNode(children, 0));
        Assert.assertFalse(getString(test), test.validAB());
    }

    @Test
    public void validAB_givenSameKeyAsChild_returnsFalse() throws Exception {
        ABTree.ABTreeNode[] children = {
                buildNode(new ABTree.ABTreeNode[]{buildNode(-10), buildNode(1)}, 0),
                buildNode(new ABTree.ABTreeNode[]{buildNode(2), buildNode(10)}, 5),
        };
        setField("root", buildNode(children, 0));
        Assert.assertFalse(getString(test), test.validAB());
    }

    @Test
    public void validAB_givenChildKeysInvalidLeft_returnsFalse() throws Exception {
        ABTree.ABTreeNode[] children = {
                buildNode(new ABTree.ABTreeNode[]{buildNode(-10), buildNode(1)}, -5),
                buildNode(new ABTree.ABTreeNode[]{buildNode(2), buildNode(10)}, 5),
        };
        setField("root", buildNode(children, 0));
        Assert.assertFalse(getString(test), test.validAB());
    }

    @Test
    public void validAB_givenChildKeysInvalidRight_returnsFalse() throws Exception {
        ABTree.ABTreeNode[] children = {
                buildNode(new ABTree.ABTreeNode[]{buildNode(-10), buildNode(-2)}, -5),
                buildNode(new ABTree.ABTreeNode[]{buildNode(-1), buildNode(10)}, 5),
        };
        setField("root", buildNode(children, 0));
        Assert.assertFalse(getString(test), test.validAB());
    }

    @Test
    public void find_givenEmpty_returnsFalse() throws Exception {
        Assert.assertFalse(getString(test), test.find(10));
    }

    @Test
    public void find_givenKeyInRoot_returnsTrue() throws Exception {
        setField("root", buildNode(0));
        Assert.assertTrue(getString(test), test.find(0));
    }

    @Test
    public void find_givenKeyNotInRoot_givenOnlyRoot_returnsFalse() throws Exception {
        setField("root", buildNode(0));
        Assert.assertFalse(getString(test), test.find(1));
    }

    @Test
    public void find_givenKeyInChildLeft_returnsTrue() throws Exception {
        ABTree.ABTreeNode[] children = {buildNode(-5), buildNode(5)};
        setField("root", buildNode(children, 0));
        Assert.assertTrue(getString(test), test.find(-5));
    }

    @Test
    public void find_givenKeyInChildRight_returnsTrue() throws Exception {
        ABTree.ABTreeNode[] children = {buildNode(-5), buildNode(5)};
        setField("root", buildNode(children, 0));
        Assert.assertTrue(getString(test), test.find(5));
    }

    @Test
    public void find_givenKeyInChildMiddle_returnsTrue() throws Exception {
        ABTree.ABTreeNode[] children = {buildNode(-5),
                buildNode(5), buildNode(15)};
        setField("root", buildNode(children, 0, 10));
        Assert.assertTrue(getString(test), test.find(5));
    }

    @Test
    public void insert_givenEmpty_inputRandom_thenKeyIs0() throws Exception {
        int key = new Random().nextInt();
        insert(key);
        Assert.assertEquals(Arrays.asList(key), getField(getField("root"), "keys"));
        Assert.assertEquals(1, test.height());
    }

    @Test
    public void insert_givenEmpty_input0_1_2_thenKeysAre0_1_2() throws Exception {
        insert(0, 1, 2);
        Assert.assertEquals(Arrays.asList(0, 1, 2), getField(getField("root"), "keys"));
        Assert.assertEquals(1, test.height());
    }

    @Test
    public void insert_givenEmpty_input2_0_1_thenSortKeys() throws Exception {
        insert(2, 0, 1);
        Assert.assertEquals(Arrays.asList(0, 1, 2), getField(getField("root"), "keys"));
    }

    @Test
    public void insert_givenKeys0_1_2_givenInput3_thenHeightIs2() throws Exception {
        insert(0, 1, 2);
        insert(3);
        Assert.assertEquals(getString(test), 2, test.height());
    }

    @Test
    public void insert_givenChild_thenInsertsToChild() throws Exception {
        ABTree.ABTreeNode[] children = {buildNode(-5), buildNode(5)};
        setField("root", buildNode(children, 0));
        ABTree.ABTreeNode[] exChildren = {buildNode(-5), buildNode(1, 5)};
        ABTree.ABTreeNode expected = buildNode(exChildren, 0);

        insert(1);

        assertTree(expected);
    }

    @Test
    public void insert_givenChildIsFull_thenSplitChild() throws Exception {
        ABTree.ABTreeNode[] children = {buildNode(-5), buildNode(1, 2, 3)};
        setField("root", buildNode(children, 0));
        ABTree.ABTreeNode[] exChildren = {buildNode(-5), buildNode(1, 2), buildNode(5)};
        ABTree.ABTreeNode expected = buildNode(exChildren, 0, 3);
        insert(5);

        assertTree(expected);
    }

    @Test
    public void insert_givenChildIsFull_givenMulitpleKeysInRoot_thenJoinToRootWithSoring() throws Exception {
        ABTree.ABTreeNode[] children = {buildNode(-5), buildNode(1, 3, 4), buildNode(15)};
        setField("root", buildNode(children, 0, 10));
        ABTree.ABTreeNode[] exChildren = {buildNode(-5), buildNode(1, 2), buildNode(4), buildNode(15)};

        ABTree.ABTreeNode expected = buildNode(exChildren, 0, 3, 10);
        insert(2);

        assertTree(expected);
    }

    @Test
    public void remove_givenEmpty_returnsFalse() throws Exception {
        Assert.assertEquals(false, test.remove(0));
    }

    @Test
    public void remove_givenRootKey_givenRootEmpty_returnsTrue() throws Exception {
        setField("root", buildNode(0));
        Assert.assertEquals(true, test.remove(0));
    }

    @Test
    public void remove_givenKeyNotInRoot_givenOnlyRoot_returnsFalse() throws Exception {
        setField("root", buildNode(0));
        Assert.assertFalse(getString(test), test.remove(1));
        Assert.assertNotNull(getField("root"));
    }

    @Test
    public void remove_givenRootKey_givenRootEmpty_thenRootIsNull() throws Exception {
        setField("root", buildNode(0));
        test.remove(0);
        Assert.assertEquals(null, getField("root"));
    }

    @Test
    public void remove_givenOnlyKeyInRoot_thenReplace() throws Exception {
        setField("root", buildNodeHeight(0, 2));
        ABTree.ABTreeNode expected = buildNode(-4, 4);

        test.remove(0);

        assertTree(expected);
    }

    @Test
    public void remove_givenKeyInChildLeft_given2KeysInChild_thenRemoves1Key() throws Exception {
        ABTree.ABTreeNode[] children = {buildNode(-10, -5), buildNode(5)};
        setField("root", buildNode(children, 0));
        ABTree.ABTreeNode[] exChildren = {buildNode(-5), buildNode(5)};
        ABTree.ABTreeNode expected = buildNode(exChildren, 0);

        Assert.assertTrue(getString(test), test.remove(-10));
        assertTree(expected);
    }

    @Test
    public void remove_givenKeyInChildLeft_given2KeysInRoot_returnsTrue() throws Exception {
        ABTree.ABTreeNode[] children = {buildNode(-5), buildNode(-1), buildNode(5)};
        setField("root", buildNode(children, -2, 0));
        ABTree.ABTreeNode[] exChildren = {buildNode(-2, -1), buildNode(5)};
        ABTree.ABTreeNode expected = buildNode(exChildren, 0);

        Assert.assertTrue(getString(test), test.remove(-5));
        assertTree(expected);
    }

    @Test
    public void remove_givenKeyInChildLeft_returnsTrue() throws Exception {
        ABTree.ABTreeNode[] children = {buildNode(-5), buildNode(5)};
        setField("root", buildNode(children, 0));
        ABTree.ABTreeNode expected = buildNode(0, 5);

        Assert.assertTrue(getString(test), test.remove(-5));
        assertTree(expected);
    }

    @Test
    public void remove_givenKeyInChildRight_returnsTrue() throws Exception {
        ABTree.ABTreeNode[] children = {buildNode(-5), buildNode(5)};
        setField("root", buildNode(children, 0));
        ABTree.ABTreeNode expected = buildNode(-5, 0);

        Assert.assertTrue(getString(test), test.remove(5));
        assertTree(expected);
    }

    @Test
    public void remove_givenKeyInChildMiddle_returnsTrue() throws Exception {
        ABTree.ABTreeNode[] children = {buildNode(-5),
                buildNode(5), buildNode(15)};
        setField("root", buildNode(children, 0, 10));
        ABTree.ABTreeNode[] exChildren = {buildNode(-5, 0), buildNode(15)};
        ABTree.ABTreeNode expected = buildNode(exChildren, 10);

        Assert.assertTrue(getString(test), test.remove(5));
        assertTree(expected);
    }

    @Test
    public void remove_givenLeft1KeyToBeRemoved_givenOthersMaxKeysLength() throws Exception {
        ABTree.ABTreeNode[] children = {buildNode(-15), buildNode(-7, -6, -5), buildNode(5, 6, 7), buildNode(15, 16, 17)};
        setField("root", buildNode(children, -10, 0, 10));
        ABTree.ABTreeNode[] exChildren = {buildNode(-10, -7), buildNode(-5, 0), buildNode(6, 7), buildNode(15, 16, 17)};
        ABTree.ABTreeNode expected = buildNode(exChildren, -6, 5, 10);

        Assert.assertTrue(getString(test), test.remove(-15));
        assertTree(expected);
    }
}