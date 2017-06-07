package gad17.blatt05;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

@RunWith(Parameterized.class)
public class DoubleHashTableTest {

    private final HashableFactory factory;
    private final Object[] keys;
    private DoubleHashTable<Object, Integer> test;

    public DoubleHashTableTest(String name, HashableFactory factory, Object[] keys) {
        this.factory = factory;
        this.keys = keys;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Int", new IntHashableFactory(),
                        new Integer[]{0, 1, 2, 3, 4, 5}},
                {"String", new StringHashableFactory(),
                        new String[]{((char) 0) + "", "1", "2", "3", "4", "5"}},
        });
    }

    private Pair<String, Integer>[] getTable() {
        try {
            Field field = test.getClass().getDeclaredField("hashTable");
            field.setAccessible(true);
            return (Pair<String, Integer>[]) field.get(test);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
        throw new RuntimeException();
    }

    private int hash(Object key, int i) {
        try {
            Method method = test.getClass().getDeclaredMethod("hash",
                    Object.class, int.class);
            method.setAccessible(true);
            return (int) method.invoke(test, key, i);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
        throw new RuntimeException();
    }

    @Before
    public void setup() {
        test = new DoubleHashTable<>(5, factory);
    }

    @Test(expected = Exception.class)
    public void constructor_inputNegative_throwsException() {
        new DoubleHashTable(-2, factory);
    }

    @Test(expected = Exception.class)
    @Ignore // Optional
    public void constructor_input0_throwsException() {
        new DoubleHashTable(0, factory);
    }

    @Test(expected = Exception.class)
    @Ignore // Optional
    public void constructor_inputNotPrime_throwsException() {
        new DoubleHashTable(12, factory);
    }

    @Test
    public void constructor_input3_thenSizeIs3() {
        test = new DoubleHashTable<>(3, factory);
        Assert.assertEquals(3, getTable().length);
    }

    @Test
    public void constructor_callsFactoryCreate() {
        boolean[] callecCreate = {false};
        HashableFactory mockFactory = size -> {
            callecCreate[0] = true;
            return factory.create(size);
        };
        new DoubleHashTable<>(5, mockFactory);
        Assert.assertTrue(callecCreate[0]);
    }

    @Test
    public void hash_input0_0_returns0() throws Exception {
        Assert.assertEquals(0, hash(keys[0], 0));
    }

    @Test
    public void hash_givenDifferentI_returnsDifferentValues() {
        Assert.assertNotEquals(hash(keys[0], 0), hash(keys[0], 1));
    }

    @Test
    public void hash_givenHighI_usesMod() {
        Assert.assertTrue(hash(keys[1], 1000) < getTable().length);
    }

    @Test
    public void hash_usesHash() {
        boolean[] callecHash = new boolean[2];
        test = new DoubleHashTable<>(5, buildHashFactory(callecHash));
        hash(keys[0], 0);
        Assert.assertTrue(callecHash[0]);
    }

    @Test
    public void hash_usesHashTick() {
        boolean[] calledHash = new boolean[2];
        test = new DoubleHashTable<>(5, buildHashFactory(calledHash));
        hash(keys[0], 0);
        Assert.assertTrue(calledHash[0]);
    }

    private HashableFactory buildHashFactory(final boolean[] calledHash) {
        return buildHashFactory(calledHash, null, null);

    }

    private HashableFactory buildHashFactory(Function<Object, Long> h, Function<Object, Long> hT) {
        return buildHashFactory(null, h, hT);

    }

    private HashableFactory buildHashFactory(final boolean[] calledHash, Function<Object, Long> h, Function<Object, Long> hT) {
        return new HashableFactory() {
            @Override
            public DoubleHashable create(int size) {
                return factory instanceof IntHashableFactory ?
                        new DoubleHashInt(size) {
                            @Override
                            public long hash(Integer key) {
                                if (calledHash != null) calledHash[0] = true;
                                return h == null ? super.hash(key) :
                                        h.apply(key);
                            }

                            @Override
                            public long hashTick(Integer key) {
                                if (calledHash != null) calledHash[1] = true;
                                return hT == null ? super.hashTick(key) :
                                        hT.apply(key);
                            }
                        } :
                        new DoubleHashString(size) {
                            @Override
                            public long hash(String key) {
                                if (calledHash != null) calledHash[0] = true;
                                return h == null ? super.hash(key) :
                                        h.apply(key);
                            }

                            @Override
                            public long hashTick(String key) {
                                if (calledHash != null) calledHash[1] = true;
                                return hT == null ? super.hashTick(key) :
                                        hT.apply(key);
                            }
                        };
            }
        };
    }

    @Test
    public void insert_givenTableIsFull_returnsFalse() {
        test.insert(keys[1], 0);
        test.insert(keys[2], 0);
        test.insert(keys[3], 0);
        test.insert(keys[4], 0);
        test.insert(keys[5], 0);

        Assert.assertFalse(test.insert(keys[0], 0));
    }

    @Test
    public void insert_givenTableIsNotFull_returnsTrue() {
        Assert.assertTrue(test.insert(keys[0], 0));
    }


    @Test
    public void insert_givenTableIsNotFull_thenSavesValue() {
        test.insert(keys[0], 123);
        Assert.assertTrue(tableContains(123));
    }

    private boolean tableContains(int value) {
        return Arrays.toString(getTable()).contains(value + "");
    }

    @Test
    public void insert_given2TimesSameKey_thenOverridesValue() {
        test.insert(keys[0], 123);
        test.insert(keys[0], 124);
        Assert.assertFalse(tableContains(123));
        Assert.assertTrue(tableContains(124));
    }

    @Test
    public void insert_givenTableNotEmpty_given2TimesSameKey_thenOverridesValue() {
        test.insert(keys[0], 123);
        test.insert(keys[1], 125);
        test.insert(keys[0], 124);
        Assert.assertFalse(tableContains(123));
        Assert.assertTrue(tableContains(124));
        Assert.assertTrue(tableContains(125));
    }

    @Test
    public void insert_givenDifferentKeySameHash_thenUsesDifferentI() {
        test = new DoubleHashTable(2, factory);
        test.insert(keys[3], 123);
        test.insert(keys[1], 124);
        Assert.assertTrue(tableContains(123));
        Assert.assertTrue(tableContains(124));
    }

    @Test
    public void insert_callsHash() {
        boolean[] calledHash = new boolean[2];
        new DoubleHashTable<>(5, buildHashFactory(calledHash)).insert(keys[0], 0);
        Assert.assertTrue(calledHash[0]);
        Assert.assertTrue(calledHash[1]);
    }


    @Test
    public void find_givenTableIsEmpty_returnsOptionalEmpty() {
        Assert.assertEquals(Optional.empty(), test.find(keys[0]));
    }

    @Test
    public void find_givenTableContains123_returns123() {
        test.insert(keys[0], 123);
        Assert.assertEquals(Optional.of(123), test.find(keys[0]));
    }

    @Test
    public void find_given2TimesSameKey_returns123() {
        test.insert(keys[0], 0);
        test.insert(keys[0], 123);
        Assert.assertEquals(Optional.of(123), test.find(keys[0]));
    }

    @Test
    public void find_given2TimesSameHash_returnsContainsBothValues() {
        test = new DoubleHashTable<>(5,
                buildHashFactory(x -> 1L, x -> 1L));
        test.insert(keys[1], 1);
        test.insert(keys[3], 3);

        Assert.assertEquals(Optional.of(1), test.find(keys[1]));
        Assert.assertEquals(Optional.of(3), test.find(keys[3]));
    }


    @Test
    public void find_callsHash() {
        boolean[] calledHash = new boolean[2];
        new DoubleHashTable<>(5, buildHashFactory(calledHash))
                .find(keys[0]);
        Assert.assertTrue(calledHash[0]);
        Assert.assertTrue(calledHash[1]);
    }

    @Test
    public void collisions_givenTableIsEmpty_returns0() {
        Assert.assertEquals(0, test.collisions());
    }

    @Test
    public void collisions_givenTableContains1Element_returns0() {
        test.insert(keys[0], 1);
        Assert.assertEquals(0, test.collisions());
    }

    @Test
    public void collisions_given2TimesSameKey_returns0() {
        test.insert(keys[0], 1);
        test.insert(keys[0], 7);
        Assert.assertEquals(0, test.collisions());
    }

    @Test
    public void collisions_givenTableContainsDifferentKeySameHash_returns1() {
        test = new DoubleHashTable(2, factory);
        test.insert(keys[3], 123);
        test.insert(keys[1], 124);
        Assert.assertEquals(1, test.collisions());
    }

    @Test
    public void collisions_callsHash() {
        boolean[] calledHash = new boolean[2];
        test = new DoubleHashTable<>(5, buildHashFactory(calledHash));
        test.insert(keys[0], 0);

        test.collisions();

        Assert.assertTrue(calledHash[0]);
        Assert.assertTrue(calledHash[1]);
    }

    @Test
    public void collisions_integration() {
        test = new DoubleHashTable(5,
                buildHashFactory(x -> 1L, x -> 1L));
        Assert.assertEquals(0, test.collisions());
        test.insert(keys[1], 1);
        Assert.assertEquals(0, test.collisions());
        test.insert(keys[2], 2);
        Assert.assertEquals(1, test.collisions());
        test.insert(keys[3], 3);
        Assert.assertEquals(2, test.collisions());
        test.insert(keys[4], 4);
        Assert.assertEquals(3, test.collisions());
        test.insert(keys[5], 5);
        Assert.assertEquals(4, test.collisions());
    }

    @Test
    public void maxRehashes_given1Key_returns0() {
        test.insert(keys[1], 0);
        Assert.assertEquals(0, test.maxRehashes());
    }

    @Test
    public void maxRehashes_given2KeysWithDifferentHashes_returns0() {
        test = new DoubleHashTable(5,
                buildHashFactory(x -> Long.parseLong(x + ""), x -> 1L));

        test.insert(keys[1], 1);
        test.insert(keys[2], 2);
        Assert.assertEquals(0, test.maxRehashes());
    }

    @Test
    public void maxRehashes_given2KeysWithSameHashes_returns0() {
        test = new DoubleHashTable(2,
                buildHashFactory(x -> 1L, x -> 1L));

        test.insert(keys[1], 1);
        test.insert(keys[3], 3);
        Assert.assertEquals(1, test.maxRehashes());
    }

    @Test
    public void maxRehashes_integration() {
        test = new DoubleHashTable(11,
                buildHashFactory(x -> 1L, x -> 1L));

        Assert.assertEquals(0, test.maxRehashes());
        test.insert(keys[0], 0);
        Assert.assertEquals(0, test.maxRehashes());
        test.insert(keys[1], 1);
        Assert.assertEquals(1, test.maxRehashes());
        test.insert(keys[2], 2);
        Assert.assertEquals(2, test.maxRehashes());
        test.insert(keys[3], 3);
        Assert.assertEquals(3, test.maxRehashes());
        test.insert(keys[4], 4);
        Assert.assertEquals(4, test.maxRehashes());
        test.insert(keys[5], 5);
        Assert.assertEquals(5, test.maxRehashes());
    }

}
