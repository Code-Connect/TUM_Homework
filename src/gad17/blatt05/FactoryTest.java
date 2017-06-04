package gad17.blatt05;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FactoryTest {
    private final HashableFactory factory;
    private final Object[] inputs;
    private DoubleHashable test;

    public FactoryTest(String name, HashableFactory factory, Object[] inputs) {
        this.factory = factory;
        this.inputs = inputs;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"DoubleHashInt", new IntHashableFactory(), new Object[]{123, 124}},
                {"DoubleHashString", new StringHashableFactory(), new Object[]{"123", "124"}}
        });
    }

    private int getSize() {
        try {
            Field field = test.getClass().getDeclaredField("size");
            field.setAccessible(true);
            return (int) field.get(test);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
        throw new RuntimeException();
    }

    @Before
    public void setup() {
        test = factory.create(101);
    }

    @Test(expected = Exception.class)
    @Ignore //Optional
    public void constructor_input0_throwsException() {
        factory.create(0);
    }

    @Test(expected = Exception.class)
    @Ignore //Optional
    public void constructor_inputNegative_throwsException() {
        factory.create(-2);
    }

    @Test(expected = Exception.class)
    @Ignore //Optional
    public void constructor_inputNotPrime_throwsException() {
        factory.create(12);
    }

    @Test
    public void constructor_input3_thenSizeIs3() {
        test = factory.create(3);
        Assert.assertEquals(3, getSize());
    }

    @Test(expected = Exception.class)
    public void hash_inputNull_throwsException() {
        test.hash(null);
    }

    @Test(expected = Exception.class)
    @Ignore //Optional
    public void hash_inputEmptyString_throwsException() {
        test.hash("");
    }

    @Test
    public void hash_calledWithSameKey2Times_returnsSameValue() throws Exception {
        Assert.assertEquals(test.hash(inputs[0]), test.hash(inputs[0]));
    }


    @Test
    public void hash_calledWithDifferentKey2Times_returnsDifferentValues() throws Exception {
        Assert.assertNotEquals(test.hash(inputs[0]), test.hash(inputs[1]));
    }

    @Test(expected = Exception.class)
    public void hashTick_inputNull_throwsException() {
        test.hashTick(null);
    }

    @Test(expected = Exception.class)
    @Ignore //Optional
    public void hashTick_inputEmptyString_throwsException() {
        test.hashTick("");
    }

    @Test
    public void hashTick_calledWithSameKey2Times_returnsSameValue() throws Exception {
        Assert.assertEquals(test.hashTick(inputs[0]), test.hashTick(inputs[0]));
    }

    @Test
    public void hashTick_calledWithDifferentKey2Times_returnsDifferentValues() throws Exception {
        Assert.assertNotEquals(test.hashTick(inputs[0]), test.hashTick(inputs[1]));
    }

    @Test
    public void hash_returnsValuesBetween0AndSize() throws Exception {
        test = factory.create(101);
        for (int i = -10000; i <= 10000; i++) {
            Object key = factory instanceof StringHashableFactory ?
                    i + "" : i;
            String message = "Actual:" + test.hash(key);
            Assert.assertTrue(message, 0 <= test.hash(key));
            Assert.assertTrue(message, test.hash(key) < getSize());
        }
    }

    @Test
    public void hashTick_returnsValuesBetween1AndSize() throws Exception {
        test = factory.create(101);
        for (int i = -10000; i <= 10000; i++) {
            Object key = factory instanceof StringHashableFactory ?
                    i + "" : i;
            String message = "Actual:" + test.hashTick(key);
            Assert.assertTrue(message, 0 < test.hashTick(key));
            Assert.assertTrue(message, test.hashTick(key) < getSize());
        }
    }
}
