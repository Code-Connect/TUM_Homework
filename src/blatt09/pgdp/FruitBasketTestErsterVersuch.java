package pgdp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class FruitBasketTestErsterVersuch{
    private Fruit test;
    private Apple testApple;
    private Banana testBanana;
    private Pineapple testPineapple;
    private GrannySmith testGrannySmith;
    private PinkLady testPinkLady;
    private FruitBasket testBasket;

    @Before
    public void setUp() throws Exception {
        test = new Fruit();
        testApple = new Apple();
        testBanana = new Banana();
        testPineapple = new Pineapple();
        testGrannySmith = new GrannySmith();
        testPinkLady = new PinkLady();
        testBasket = new FruitBasket();
    }

    @Test
    public void constructor_givenEmpty_returnIsApple_false() throws Exception {
        boolean expected = false;
        assertIsApple(expected, test);
    }

    private void assertIsApple(boolean expected, Fruit test){
        Assert.assertEquals("Check if isApple returns false", expected, test.isApple());
    }

    @Test
    public void constructor_givenEmpty_returnIsBanana_false() throws Exception {
        boolean expected = false;
        assertIsBanana(expected, test);
    }

    private void assertIsBanana(boolean expected, Fruit test){
        Assert.assertEquals("Check if isBanana returns false", expected, test.isBanana());
    }

    @Test
    public void constructor_givenEmpty_returnIsPineapple_false() throws Exception {
        boolean expected = false;
        assertIsPineapple(expected, test);
    }

    private void assertIsPineapple(boolean expected, Fruit test){
        Assert.assertEquals("Check if isPineapple returns false", expected, test.isPineapple());
    }

    @Test
    public void constructor_givenEmpty_returnShelfLife() throws Exception {
        int expected = -1;
        assertShelfLife(expected, test);
    }

    private void assertShelfLife(int expected, Fruit test){
        Assert.assertEquals("Check if isPineapple returns false", expected, test.shelfLife());
    }

    @Test
    public void constructorApple_givenEmpty_returnIsApple() throws Exception {
        boolean expected = true;
        assertIsApple(expected, testApple);
    }

    @Test
    public void constructorApple_givenEmpty_returnIsBanana() throws Exception {
        boolean expected = false;
        assertIsBanana(expected, testApple);
    }

    @Test
    public void constructorApple_givenEmpty_returnIsPineapple() throws Exception {
        boolean expected = false;
        assertIsPineapple(expected, testApple);
    }

    @Test
    public void constructorApple_givenEmpty_returnShelfLife() throws Exception {
        int expected = 30;
        assertShelfLife(expected, testApple);
    }

    @Test
    public void constructorBanana_givenEmpty_returnIsApple() throws Exception {
        boolean expected = false;
        assertIsApple(expected, testBanana);
    }

    @Test
    public void constructorBanana_givenEmpty_returnIsBanana() throws Exception {
        boolean expected = true;
        assertIsBanana(expected, testBanana);
    }

    @Test
    public void constructorBanana_givenEmpty_returnIsPineapple() throws Exception {
        boolean expected = false;
        assertIsPineapple(expected, testBanana);
    }

    @Test
    public void constructorBanana_givenEmpty_returnShelfLife() throws Exception {
        int expected = 7;
        assertShelfLife(expected, testBanana);
    }

    @Test
    public void constructorPineapple_givenEmpty_returnIsApple() throws Exception {
        boolean expected = false;
        test = new Pineapple();
        assertIsApple(expected, testPineapple);
    }

    @Test
    public void constructorPineapple_givenEmpty_returnIsBanana() throws Exception {
        boolean expected = false;
        assertIsBanana(expected, testPineapple);
    }

    @Test
    public void constructorPineapple_givenEmpty_returnIsPineapple() throws Exception {
        boolean expected = true;
        assertIsPineapple(expected, testPineapple);
    }

    @Test
    public void constructorPineapple_givenEmpty_returnShelfLife() throws Exception {
        int expected = 20;
        assertShelfLife(expected, testPineapple);
    }

    @Test
    public void constructorGrannySmith_givenEmpty_returnIsApple() throws Exception {
        boolean expected = true;
        assertIsApple(expected, testGrannySmith);
    }

    @Test
    public void constructorGrannySmith_givenEmpty_returnIsBanana() throws Exception {
        boolean expected = false;
        assertIsBanana(expected, testGrannySmith);
    }

    @Test
    public void constructorGrannySmith_givenEmpty_returnIsPineapple() throws Exception {
        boolean expected = false;
        assertIsPineapple(expected, testGrannySmith);
    }

    @Test
    public void constructorGrannySmith_givenEmpty_returnShelfLife() throws Exception {
        int expected = 50;
        assertShelfLife(expected, testGrannySmith);
    }

    @Test
    public void constructorPinkLady_givenEmpty_returnIsApple() throws Exception {
        boolean expected = true;
        assertIsApple(expected, testPinkLady);
    }

    @Test
    public void constructorPinkLady_givenEmpty_returnIsBanana() throws Exception {
        boolean expected = false;
        assertIsBanana(expected, testPinkLady);
    }

    @Test
    public void constructorPinkLady_givenEmpty_returnIsPineapple() throws Exception {
        boolean expected = false;
        assertIsPineapple(expected, testPinkLady);
    }

    @Test
    public void constructorPinkLady_givenEmpty_returnShelfLife() throws Exception {
        int expected = 20;
        assertShelfLife(expected, testPinkLady);
    }

    @Test
    public void test_FruitBasked_AddFruit() throws Exception {
        assertList(testBasket, "[]");
        testBasket.addFruit(new Apple());
        assertList(testBasket, "[Apple]");
        testBasket.addFruit(new Banana());
        assertList(testBasket, "[Apple, Banana]");
        testBasket.addFruit(new Apple());
        assertList(testBasket, "[Apple, Banana, Apple]");
        testBasket.addFruit(new Pineapple());
        assertList(testBasket, "[Apple, Banana, Apple, Pineapple]");
        testBasket.addFruit(new GrannySmith());
        assertList(testBasket, "[Apple, Banana, Apple, Pineapple, GrannySmith]");
        testBasket.addFruit(new PinkLady());
        assertList(testBasket, "[Apple, Banana, Apple, Pineapple, GrannySmith, PinkLady]");
        testBasket.addFruit(new GrannySmith());
        assertList(testBasket, "[Apple, Banana, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith]");
        testBasket.addFruit(new Apple());
        assertList(testBasket, "[Apple, Banana, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith, Apple]");
        testBasket.addFruit(new Pineapple());
        assertList(testBasket, "[Apple, Banana, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith, Apple, Pineapple]");
        testBasket.addFruit(new Banana());
        assertList(testBasket, "[Apple, Banana, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith, Apple, Pineapple, Banana]");
    }

    @Test
    public void test_FruitBasked_getApples() throws Exception {
        assertListApples(testBasket, "[]");
        testBasket.addFruit(new Apple());
        assertListApples(testBasket, "[Apple]");
        testBasket.addFruit(new Banana());
        assertListApples(testBasket, "[Apple]");
        testBasket.addFruit(new Apple());
        assertListApples(testBasket, "[Apple, Apple]");
        testBasket.addFruit(new Pineapple());
        assertListApples(testBasket, "[Apple, Apple]");
        testBasket.addFruit(new GrannySmith());
        assertListApples(testBasket, "[Apple, Apple, GrannySmith]");
        testBasket.addFruit(new PinkLady());
        assertListApples(testBasket, "[Apple, Apple, GrannySmith, PinkLady]");
        testBasket.addFruit(new GrannySmith());
        assertListApples(testBasket, "[Apple, Apple, GrannySmith, PinkLady, GrannySmith]");
        testBasket.addFruit(new Apple());
        assertListApples(testBasket, "[Apple, Apple, GrannySmith, PinkLady, GrannySmith, Apple]");
        testBasket.addFruit(new Pineapple());
        assertListApples(testBasket, "[Apple, Apple, GrannySmith, PinkLady, GrannySmith, Apple]");
        testBasket.addFruit(new Banana());
        assertListApples(testBasket, "[Apple, Apple, GrannySmith, PinkLady, GrannySmith, Apple]");

    }

    @Test
    public void test_FruitBasked_ShelfLifeEqualsHigher() throws Exception {
        assertListEqualsHigherApple(testBasket, "[]");
        assertListEqualsHigherBanana(testBasket, "[]");
        assertListEqualsHigherPineapple(testBasket, "[]");
        assertListEqualsHigherGrannySmith(testBasket, "[]");
        assertListEqualsHigherPinkLady(testBasket, "[]");
        testBasket.addFruit(new Apple());
        assertListEqualsHigherApple(testBasket, "[Apple]");
        assertListEqualsHigherBanana(testBasket, "[Apple]");
        assertListEqualsHigherPineapple(testBasket, "[Apple]");
        assertListEqualsHigherGrannySmith(testBasket, "[]");
        assertListEqualsHigherPinkLady(testBasket, "[Apple]");
        testBasket.addFruit(new Banana());
        assertListEqualsHigherApple(testBasket, "[Apple]");
        assertListEqualsHigherBanana(testBasket, "[Apple, Banana]");
        assertListEqualsHigherPineapple(testBasket, "[Apple]");
        assertListEqualsHigherGrannySmith(testBasket, "[]");
        assertListEqualsHigherPinkLady(testBasket, "[Apple]");
        testBasket.addFruit(new Apple());
        assertListEqualsHigherApple(testBasket, "[Apple, Apple]");
        assertListEqualsHigherBanana(testBasket, "[Apple, Banana, Apple]");
        assertListEqualsHigherPineapple(testBasket, "[Apple, Apple]");
        assertListEqualsHigherGrannySmith(testBasket, "[]");
        assertListEqualsHigherPinkLady(testBasket, "[Apple, Apple]");
        testBasket.addFruit(new Pineapple());
        assertListEqualsHigherApple(testBasket, "[Apple, Apple]");
        assertListEqualsHigherBanana(testBasket, "[Apple, Banana, Apple, Pineapple]");
        assertListEqualsHigherPineapple(testBasket, "[Apple, Apple, Pineapple]");
        assertListEqualsHigherGrannySmith(testBasket, "[]");
        assertListEqualsHigherPinkLady(testBasket, "[Apple, Apple, Pineapple]");
        testBasket.addFruit(new GrannySmith());
        assertListEqualsHigherApple(testBasket, "[Apple, Apple, GrannySmith]");
        assertListEqualsHigherBanana(testBasket, "[Apple, Banana, Apple, Pineapple, GrannySmith]");
        assertListEqualsHigherPineapple(testBasket, "[Apple, Apple, Pineapple, GrannySmith]");
        assertListEqualsHigherGrannySmith(testBasket, "[GrannySmith]");
        assertListEqualsHigherPinkLady(testBasket, "[Apple, Apple, Pineapple, GrannySmith]");
        testBasket.addFruit(new PinkLady());
        assertListEqualsHigherApple(testBasket, "[Apple, Apple, GrannySmith]");
        assertListEqualsHigherBanana(testBasket, "[Apple, Banana, Apple, Pineapple, GrannySmith, PinkLady]");
        assertListEqualsHigherPineapple(testBasket, "[Apple, Apple, Pineapple, GrannySmith, PinkLady]");
        assertListEqualsHigherGrannySmith(testBasket, "[GrannySmith]");
        assertListEqualsHigherPinkLady(testBasket, "[Apple, Apple, Pineapple, GrannySmith, PinkLady]");
        testBasket.addFruit(new GrannySmith());
        assertListEqualsHigherApple(testBasket, "[Apple, Apple, GrannySmith, GrannySmith]");
        assertListEqualsHigherBanana(testBasket, "[Apple, Banana, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith]");
        assertListEqualsHigherPineapple(testBasket, "[Apple, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith]");
        assertListEqualsHigherGrannySmith(testBasket, "[GrannySmith, GrannySmith]");
        assertListEqualsHigherPinkLady(testBasket, "[Apple, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith]");
        testBasket.addFruit(new Apple());
        assertListEqualsHigherApple(testBasket, "[Apple, Apple, GrannySmith, GrannySmith, Apple]");
        assertListEqualsHigherBanana(testBasket, "[Apple, Banana, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith, Apple]");
        assertListEqualsHigherPineapple(testBasket, "[Apple, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith, Apple]");
        assertListEqualsHigherGrannySmith(testBasket, "[GrannySmith, GrannySmith]");
        assertListEqualsHigherPinkLady(testBasket, "[Apple, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith, Apple]");
        testBasket.addFruit(new Pineapple());
        assertListEqualsHigherApple(testBasket, "[Apple, Apple, GrannySmith, GrannySmith, Apple]");
        assertListEqualsHigherBanana(testBasket, "[Apple, Banana, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith, Apple, Pineapple]");
        assertListEqualsHigherPineapple(testBasket, "[Apple, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith, Apple, Pineapple]");
        assertListEqualsHigherGrannySmith(testBasket, "[GrannySmith, GrannySmith]");
        assertListEqualsHigherPinkLady(testBasket, "[Apple, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith, Apple, Pineapple]");
        testBasket.addFruit(new Banana());
        assertListEqualsHigherApple(testBasket, "[Apple, Apple, GrannySmith, GrannySmith, Apple]");
        assertListEqualsHigherBanana(testBasket, "[Apple, Banana, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith, Apple, Pineapple, Banana]");
        assertListEqualsHigherPineapple(testBasket, "[Apple, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith, Apple, Pineapple]");
        assertListEqualsHigherGrannySmith(testBasket, "[GrannySmith, GrannySmith]");
        assertListEqualsHigherPinkLady(testBasket, "[Apple, Apple, Pineapple, GrannySmith, PinkLady, GrannySmith, Apple, Pineapple]");
    }

    private void assertList(FruitBasket Basket, String expected) {
        Assert.assertEquals("Check if Fruitbasket contains right elements ", expected, makeStringOfList(Basket.fruits));
    }

    private void assertListApples(FruitBasket Basket, String expected) {
        Assert.assertEquals("Check if returned list from getApples() contains right elements ",expected, makeStringOfListApple(Basket.getApples()));
    }

    private void assertListEqualsHigherApple(FruitBasket Basket, String expected) {
        Assert.assertEquals("Check if list returned from shelfLifeEQH contains right elements ",expected, makeStringOfList(Basket.getEqualOrLongerShelfLife(new Apple().shelfLife())));
    }
    private void assertListEqualsHigherBanana(FruitBasket Basket, String expected) {
        Assert.assertEquals("Check if list returned from shelfLifeEQH contains right elements ",expected, makeStringOfList(Basket.getEqualOrLongerShelfLife(new Banana().shelfLife())));
    }
    private void assertListEqualsHigherPineapple(FruitBasket Basket, String expected) {
        Assert.assertEquals("Check if list returned from shelfLifeEQH contains right elements ",expected, makeStringOfList(Basket.getEqualOrLongerShelfLife(new Pineapple().shelfLife())));
    }
    private void assertListEqualsHigherGrannySmith(FruitBasket Basket, String expected) {
        Assert.assertEquals("Check if list returned from shelfLifeEQH contains right elements ",expected, makeStringOfList(Basket.getEqualOrLongerShelfLife(new GrannySmith().shelfLife())));
    }
    private void assertListEqualsHigherPinkLady(FruitBasket Basket, String expected) {
        Assert.assertEquals("Check if list returned from shelfLifeEQH contains right elements ",expected, makeStringOfList(Basket.getEqualOrLongerShelfLife(new PinkLady().shelfLife())));
    }

    private String makeStringOfList(LinkedList<Fruit> list) {
        if (list.isEmpty()) return "[]";
        String text[] = new String[list.size()];
        int i = 0;
        for(Fruit f : list){
            if(f.isApple()){
                if(f.shelfLife()==new Apple().shelfLife()) text[i] = "Apple";
                if(f.shelfLife()==new GrannySmith().shelfLife()) text[i] = "GrannySmith";
                if(f.shelfLife()== new PinkLady().shelfLife()) text[i] = "PinkLady";
            }
            if(f.isBanana()) text[i] = "Banana";
            if(f.isPineapple()) text[i] = "Pineapple";
            i++;
        }
        String toOutUglyAsFuckAbaKoaLustMeaSolangSFunzt = "[";
        for (int af=0; af<text.length-1; af++){
            toOutUglyAsFuckAbaKoaLustMeaSolangSFunzt += text[af]+", ";
        }
        toOutUglyAsFuckAbaKoaLustMeaSolangSFunzt += text[text.length-1]+ "]";

        return toOutUglyAsFuckAbaKoaLustMeaSolangSFunzt;
    }

    private String makeStringOfListApple(LinkedList<Apple> list) {
        if (list.isEmpty()) return "[]";
        String text[] = new String[list.size()];
        int i = 0;
        for(Fruit f : list){
            if(f.isApple()){
                if(f.shelfLife()==new Apple().shelfLife()) text[i] = "Apple";
                if(f.shelfLife()==new GrannySmith().shelfLife()) text[i] = "GrannySmith";
                if(f.shelfLife()== new PinkLady().shelfLife()) text[i] = "PinkLady";
            }
            if(f.isBanana()) text[i] = "Banana";
            if(f.isPineapple()) text[i] = "Pineapple";
            i++;
        }
        String toOutUglyAsFuckAbaKoaLustMeaSolangSFunzt = "[";
        for (int af=0; af<text.length-1; af++){
            toOutUglyAsFuckAbaKoaLustMeaSolangSFunzt += text[af]+", ";
        }
        toOutUglyAsFuckAbaKoaLustMeaSolangSFunzt += text[text.length-1]+ "]";

        return toOutUglyAsFuckAbaKoaLustMeaSolangSFunzt;
    }
}

