package pgdp16.blatt09.fight;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class PredatorTest {
    private final String toTest;
    private final int withoutFood;
    private Predator test;

    public PredatorTest(String toTest, Predator test, int withoutFood) {
        this.toTest = toTest;
        this.test = test;
        this.withoutFood = withoutFood;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Leopard", new Leopard(false), 5},
                {"Penguin", new Penguin(false), 12},
                {"Snake", new Snake(false), 9},
        });
    }

    @Before
    public void setUp() throws Exception {
        test.position = new Position();
        test.position.myAnimals = new Animal[]{test};
    }

    @Test
    public void constructor() throws Exception {
        Assert.assertEquals("A " + toTest + " shall have a withoutFood of " + withoutFood, withoutFood, test.withoutFood);
    }


    @Test
    public void sunset_givenWithoutFoodIs5_thenBecomes4() throws Exception {
        test.withoutFood = 5;
        test.sunset();
        Assert.assertEquals("On sunset() Predator.withoutFood shall be decreased", 4, test.withoutFood);
        assertAlive(true, "non starved Predators shall");
    }

    @Test
    public void sunset_givenWithoutFoodIs0_thenAliveIsFalse() throws Exception {
        test.withoutFood = 0;
        test.sunset();
        assertAlive(false, "starved Predators shall no longer");
    }

    private void assertAlive(boolean expected, final String messagePart) {
        Assert.assertEquals("On sunset() " + messagePart + " be alive", expected, test.alive);
    }

    @Test
    public void possibleMoves_givenEnemyVegetarianG7_givenH8_ReturnG7() throws Exception {
        Move[] exp = buildKillerMove();

        Move[] actual = test.possibleMoves();

        assertMoves(exp, actual);
    }

    private Move[] buildKillerMove() {
        Predator predatorW_G8 = new Penguin(true);
        Vegetarian vegetarianW_G7 = new Vegetarian(true);
        Vegetarian vegetarianM_H7 = new Vegetarian(false);
        predatorW_G8.square = "g8";
        vegetarianW_G7.square = "g7";
        vegetarianM_H7.square = "h7";

        test.position.myAnimals = new Animal[]{test, predatorW_G8, vegetarianW_G7, vegetarianM_H7};
        return buildMoves("h8", "g7");
    }

    private Move[] buildMoves(String start, String... targets) {
        test.square = start;
        Move[] out = new Move[targets.length];
        for (int i = 0; i < out.length; i++) out[i] = new Move(start + targets[i]);
        return out;
    }


    private void assertMoves(Move[] exp, Move[] actual) {
        Assert.assertNotNull("possibleMoves() shall not return null!", actual);
        Assert.assertEquals("possibleMoves() shall have another length\n"
                + Arrays.toString(actual), exp.length, actual.length);
        for (Move move : exp)
            Assert.assertTrue("possibleMoves() does not contain: "
                            + move + "\n" + Arrays.toString(actual),
                    Arrays.asList(actual).contains(move));
    }

    @Test
    public void applyMoves_givenKillerMove_increasesWithoutFood() throws Exception {
        test.withoutFood = 0;

        test.position.applyMoves(buildKillerMove());

        Assert.assertEquals(withoutFood, test.withoutFood);
    }
}