package pgdp16.blatt09.pgdp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class FruitBasketTest {

    private FruitBasket test;

    @Before
    public void setUp() throws Exception {
        test = new FruitBasket();
    }

    @Test
    public void addFruit_givenNull_thenAddsNullToList() throws Exception {
        Fruit expected = null;
        arrangeFruits(expected);
        assertFruitInBasket(expected);
    }

    @Test
    public void addFruit_givenEmpty_thenAddsFruitToList() throws Exception {
        Fruit expected = new Fruit();
        arrangeFruits(expected);
        assertFruitInBasket(expected);
    }

    private void assertFruitInBasket(Fruit expected) {
        Assert.assertNotNull("FruitBasket.fruits shall not be null!", test.fruits);
        Assert.assertTrue(expected + " is not in FruitBasket.fruits", test.fruits.contains(expected));
    }

    @Test
    public void getApples_givenNoFruits_returnsEmptyList() throws Exception {
        assertGetApples(new Apple[0]);
    }

    private void assertGetApples(Apple[] expected) {
        LinkedList<Apple> output = test.getApples();
        assertArray(expected, output, "FruitBasket.getApples()");
    }

    private void assertArray(Fruit[] expected, LinkedList actual, String method) {
        Assert.assertNotNull(method + " shall not return null!", actual);
        Assert.assertArrayEquals("The List returned by " + method + " is wrong: \n" + actual.toString() + "\n", expected, actual.toArray());
    }

    @Test
    public void getApples_givenElementNull_returnsEmptyList() throws Exception {
        arrangeFruits(new Fruit[]{null});
        assertGetApples(new Apple[0]);
    }

    @Test
    public void getApples_givenNoApple_returnsEmptyList() throws Exception {
        arrangeFruits(new Fruit());
        assertGetApples(new Apple[0]);
    }

    private ArrayList<Fruit> arrangeFruits(Fruit... fruits) {
        ArrayList<Fruit> out = new ArrayList<>();
        for (Fruit f : fruits) {
            test.addFruit(f);
            out.add(f);
        }
        return out;
    }

    @Test
    public void getApples_givenOneApple_returnsListWith1Element() throws Exception {
        ArrayList<Fruit> fruits = arrangeFruits(new Apple());
        assertGetApples(fruits, 0);
    }

    private void assertGetApples(ArrayList<Fruit> fruits, int... indexes) {
        Apple[] expected = (Apple[]) fillArray(new Apple[indexes.length], fruits, indexes);

        assertGetApples(expected);
    }

    private Object[] fillArray(Object[] array, ArrayList<Fruit> fruits, int... indexes) {
        int count = 0;
        for (int index : indexes) array[count++] = fruits.get(index);
        return array;
    }

    @Test
    public void getApples_given3Apple_returnsListWith3Element() throws Exception {
        ArrayList<Fruit> fruits = arrangeFruits(new Apple(), new Apple(), new Apple());
        assertGetApples(fruits, 0, 1, 2);
    }

    @Test
    public void getApples_given4AppleAndOthers_returnsListWith3Element() throws Exception {
        ArrayList<Fruit> fruits = arrangeFruits();
        assertGetApples(fruits, 1, 3, 4);
    }

    @Test
    public void getEqualOrLongerShelfLife_givenListIsNull_returnEmptyList() throws Exception {
        int input = -1;

        assertGetEqualOrLongerShelfLife(input, new Fruit[0]);
    }

    private void assertGetEqualOrLongerShelfLife(int input, Fruit[] expected) {
        LinkedList<Fruit> output = test.getEqualOrLongerShelfLife(input);

        String method = "FruitBasket.getEqualOrLongerShelfLife(" + input + ")";
        assertArray(expected, output, method);
        Assert.assertNotNull(method + " shall not return null!", output);
        Assert.assertArrayEquals(expected, output.toArray());
    }

    @Test
    public void getEqualOrLongerShelfLife_givenN1_returnCompleteList() throws Exception {
        ArrayList<Fruit> fruits = arrangeFruits();
        int input = -1;

        assertGetEqualOrLongerShelfLife(input, fruits, 0, 1, 2, 3, 4, 5);
    }

    private ArrayList<Fruit> arrangeFruits() {
        return arrangeFruits(new Fruit(), new GrannySmith(), new Banana(), new Apple(), new PinkLady(), new Pineapple(), null);
    }

    private void assertGetEqualOrLongerShelfLife(int input, ArrayList<Fruit> fruits, int... indexes) {
        Fruit[] expected = (Fruit[]) fillArray(new Fruit[indexes.length], fruits, indexes);

        assertGetEqualOrLongerShelfLife(input, expected);
    }

    @Test
    public void getEqualOrLongerShelfLife_given30_returnListEL30() throws Exception {
        ArrayList<Fruit> fruits = arrangeFruits();
        int input = 30;

        assertGetEqualOrLongerShelfLife(input, fruits, 1, 3);
    }

    @Test
    public void getEqualOrLongerShelfLife_given50_returnEL50() throws Exception {
        ArrayList<Fruit> fruits = arrangeFruits();
        int input = 50;

        assertGetEqualOrLongerShelfLife(input, fruits, 1);
    }

    @Test
    public void getEqualOrLongerShelfLife_given20_returnEL20() throws Exception {
        ArrayList<Fruit> fruits = arrangeFruits();
        int input = 20;

        assertGetEqualOrLongerShelfLife(input, fruits, 1, 3, 4, 5);
    }

     @Test
    public void getEqualOrLongerShelfLife_given7_returnEL7() throws Exception {
        ArrayList<Fruit> fruits = arrangeFruits();
        int input = 7;

        assertGetEqualOrLongerShelfLife(input, fruits, 1, 2, 3, 4, 5);
    }

}
