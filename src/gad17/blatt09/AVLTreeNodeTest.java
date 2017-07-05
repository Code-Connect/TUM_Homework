package gad17.blatt09;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Random;

import static gad17.blatt09.AVLTreeNodeBuilder.node;

public class AVLTreeNodeTest {
    private AVLTreeNode test;

    @Before
    public void setUp() throws Exception {
        test = new AVLTreeNode(1);
    }

    @Test
    public void constructor_input1() throws Exception {
        test = new AVLTreeNode(1);
        Assert.assertEquals(1, getField("key"));
    }

    @Test
    public void constructor_inputRandom() throws Exception {
        int expected = new Random().nextInt();
        test = new AVLTreeNode(expected);
        Assert.assertEquals(expected, getField("key"));
    }

    private Object getField(String key) throws Exception {
        Field field = test.getClass().getDeclaredField(key);
        field.setAccessible(true);
        return field.get(test);
    }

    private void setField(Object value, String key) throws Exception {
        Field field = test.getClass().getDeclaredField(key);
        field.setAccessible(true);
        field.set(test, value);
    }

    @Test
    public void heightIs0_return0() {
        Assert.assertEquals(0, test.height());
    }

    @Test
    public void height_givenLeftIsNewNode_return1() throws Exception {
        setField(new AVLTreeNode(3), "left");
        Assert.assertEquals(1, test.height());
    }

    @Test
    public void height_givenRightIsNewNode_return1() throws Exception {
        setField(new AVLTreeNode(3), "right");
        Assert.assertEquals(1, test.height());
    }

    @Test
    public void height_givenLeftAndRightAreNewNodes_return1() throws Exception {
        setField(new AVLTreeNode(3), "right");
        setField(new AVLTreeNode(2), "left");
        Assert.assertEquals(1, test.height());
    }

    @Test
    public void height_givenLeftHeight1_return2() throws Exception {
        setField(node().withHeight(1), "left");
        Assert.assertEquals(2, test.height());
    }

    @Test
    public void height_givenRightHeight1_return2() throws Exception {
        setField(node().withHeight(1), "right");
        Assert.assertEquals(2, test.height());
    }

    @Test
    public void height_givenRightAndLeftHeightIs1_return2() throws Exception {
        setField(node().withHeight(1), "right");
        setField(node().withHeight(1), "left");
        Assert.assertEquals(2, test.height());
    }

    @Test
    public void height_givenRightHeightIs2AndLeftIs0_return3() throws Exception {
        setField(node().withHeight(2), "right");
        setField(node().withHeight(0), "left");
        Assert.assertEquals(3, test.height());
    }

    @Test
    public void height_givenLeftHeightIs2AndRightIs1_return3() throws Exception {
        setField(node().withHeight(1), "right");
        setField(node().withHeight(2), "left");
        Assert.assertEquals(3, test.height());
    }

    @Test
    public void height_givenLeftAndRightRandom() throws Exception {
        Random r = new Random();
        int h1 = r.nextInt(Integer.MAX_VALUE);
        int h2 = r.nextInt(Integer.MAX_VALUE);
        setField(node().withHeight(h1), "right");
        setField(node().withHeight(h2), "left");
        Assert.assertEquals(Math.max(h1, h2) + 1, test.height());
    }

    @Test
    public void validAVL_givenNewNode_returnsTrue() throws Exception {
        Assert.assertEquals(true, test.validAVL());
    }

    @Test //TODO Review
    public void validAVL_givenLeftHeightIs1_returnsFalse() throws Exception {
        setField(node().withHeight(1).withKey(0), "left");
        setField(-2, "balance");
        Assert.assertEquals(false, test.validAVL());
    }

    @Test
    public void validAVL_givenRightHeightIs0_returnsTrue() throws Exception {
        setField(node().withHeight(0).withKey(2), "right");
        setField(1, "balance");
        Assert.assertEquals(true, test.validAVL());
    }

    @Test
    public void validAVL_givenRightHeightIs0_KeyIs0_returnsFalse() throws Exception {
        setField(node().withHeight(0).withKey(0), "right");
        setField(1, "balance");
        Assert.assertEquals(false, test.validAVL());
    }

    @Test
    public void validAVL_givenLeftHeightIs0_returnsTrue() throws Exception {
        setField(node().withHeight(0).withKey(0), "left");
        setField(-1, "balance");
        Assert.assertEquals(true, test.validAVL());
    }

    @Test
    public void validAVL_givenLeftHeightIs0_KeyIs0_returnsFalse() throws Exception {
        setField(node().withHeight(0).withKey(2), "left");
        setField(-1, "balance");
        Assert.assertEquals(false, test.validAVL());
    }

    @Test //TODO Review
    public void validAVL_givenRightHeightIs1_returnsFalse() throws Exception {
        setField(node().withHeight(1).withKey(2), "right");
        setField(2, "balance");
        Assert.assertEquals(false, test.validAVL());
    }

    @Test
    public void validAVL_givenRightAndLeftHeightIs1_returnsTrue() throws Exception {
        setField(node().withHeight(1).withKey(2), "right");
        setField(node().withHeight(1).withKey(0), "left");
        Assert.assertEquals(true, test.validAVL());
    }

    @Test
    public void validAVL_givenRightAndLeftSame_givenRandom_returnTrue() throws Exception {
        int h = new Random().nextInt(Integer.MAX_VALUE);
        setField(node().withHeight(h).withKey(2), "right");
        setField(node().withHeight(h).withKey(0), "left");
        Assert.assertEquals(true, test.validAVL());
    }

    @Test
    public void validAVL_KeysInLeftAreLessOrEqual() throws Exception {
        setField(-1, "balance");
        setField(node().withHeight(0).withKey(0), "left");
        Assert.assertEquals(true, test.validAVL());
        setField(node().withHeight(0).withKey(1), "left");
        Assert.assertEquals(true, test.validAVL());
    }

    @Test
    public void find_givenKeyNode1_input1_returnTrue() throws Exception {
        Assert.assertEquals(true, test.find(1));
    }

    @Test
    public void find_givenKeyNode1_givenHeightIs0_input2_returnFalse() throws Exception {
        Assert.assertEquals(false, test.find(2));
    }

    @Test
    public void find_givenKeyLeft0_givenHeightIs1_input0_returnTrue() throws Exception {
        setField(node().withHeight(0).withKey(0), "left");
        Assert.assertEquals(true, test.find(0));
    }

    @Test
    public void find_givenKeyLeft1_givenHeightIs1_input2_returnFalse() throws Exception {
        setField(node().withHeight(0), "left");
        Assert.assertEquals(false, test.find(3));
    }

    @Test
    public void find_givenKeyRight2_givenHeightIs1_input2_returnTrue() throws Exception {
        setField(node().withHeight(0).withKey(2), "right");
        Assert.assertEquals(true, test.find(2));
    }

    @Test
    public void find_givenKeyRight2_givenHeightIs2_input2_returnTrue() throws Exception {
        setField(node().withHeight(1).withKey(2).withTree(3), "right");
        Assert.assertEquals(true, test.find(3));
    }

    @Test
    public void insert_givenKey1_givenInput2_returnOldNode() throws Exception {
        Assert.assertEquals(test, test.insert(2));
    }

    @Test
    public void insert_givenHeight0_returnWithHeight1() throws Exception {
        Assert.assertEquals(1, test.insert(2).height());
    }

    @Test
    public void insert_givenKeyIs2_returnOldNodeWithNewRight() throws Exception {
        test.insert(2);
        Assert.assertNotNull(getField("right"));
        Assert.assertNull(getField("left"));
    }

    @Test
    public void insert_givenKeyIs0_returnOldNodeWithNewLeft() throws Exception {
        test.insert(0);
        Assert.assertNotNull(getField("left"));
        Assert.assertNull(getField("right"));
    }

    @Test
    public void insert_inputEqualToKey_thenInsertLeft() throws Exception {
        test.insert(1);
        Assert.assertNotNull(getField("left"));
        Assert.assertNull(getField("right"));
    }


    private void assertTree(AVLTreeNodeBuilder expected) {
        Assert.assertEquals(getString(expected), getString(test));
    }

    private String getString(AVLTreeNode node) {
        StringBuilder actual = new StringBuilder();
        node.dot(actual);
        return actual.toString();
    }

    private void insert(int... keys) {
        for (int key : keys) test = test.insert(key);
    }

    @Test
    public void insert_input2_thenUpdateBalanceTo1() throws Exception {
        test.insert(2);
        Assert.assertEquals(1, getField("balance"));
    }

    @Test
    public void insert_input0_thenUpdateBalanceToNeg1() throws Exception {
        test.insert(0);
        Assert.assertEquals(-1, getField("balance"));
    }

    @Test
    public void insert_input2KeysLeftAndRight_thenBalanceIs0() throws Exception {
        insert(-1, 2, 0, 3);
        Assert.assertEquals(0, getField("balance"));
    }

    @Test
    public void insert_input2KeysLeft_thenBalanceIsNeg2() throws Exception {
        insert(0, -1);
        Assert.assertEquals(0, getField("balance"));
    }

    @Test
    public void insert_input2KeysRight_thenBalanceIsNeg2() throws Exception {
        insert(2, 3);
        Assert.assertEquals(0, getField("balance"));
    }

    @Test
    public void insert_input_3_2_1_thenRotateRight() throws Exception {
        test = new AVLTreeNode(3);
        insert(2, 1);

        assertTree(node().withKey(2).insert(1, 3));
    }

    @Test
    public void insert_input_1_2_3_thenRotateLeft() throws Exception {
        insert(2, 3);

        assertTree(node().withKey(2).insert(1, 3));
    }

    @Test
    public void insert_input_5_3_10_4_17_thenHeightIs2() throws Exception {
        test = new AVLTreeNode(5);
        insert(3, 10, 1, 4, 17);
        Assert.assertEquals(getString(test), 2, test.height());
    }

    @Test
    public void insert_input_10_3_17_1_5_4_thenRotateLeftRight() throws Exception {
        test = new AVLTreeNode(10);
        insert(3, 17, 1, 5, 4);

        assertTree(node().withKey(5).insert(3, 10, 1, 4, 17));
    }

    @Test
    public void insert_inputNegative_10_3_17_1_5_4_thenRotateRightLeft() throws Exception {
        test = new AVLTreeNode(-10);
        insert(-3, -17, -1, -5, -4);

        assertTree(node().withKey(-5).insert(-3, -10, -1, -4, -17));
    }
}