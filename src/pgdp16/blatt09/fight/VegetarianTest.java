package pgdp16.blatt09.fight;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class VegetarianTest {
    public static String[] moves;
    private Vegetarian test;

    public VegetarianTest(String toTest, Vegetarian test, String[] moves) {
        this.test = test;
        VegetarianTest.moves = moves;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Elephant", new Elephant(false), new String[0]},
                {"Horse", new Horse(false), new String[]{"f6", "e5"}},
                {"Rabbit", new Rabbit(false), new String[0]},
        });
    }

    @Before
    public void setUp() throws Exception {
        test.position = new Position();
        test.position.myAnimals = new Animal[]{test};
    }

    @Test
    public void possibleMoves_givenSurrounded_givenH8() throws Exception {
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
        return buildMoves("h8", moves);
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
}