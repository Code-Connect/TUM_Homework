package pgdp16.blatt09.fight;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class AnimalTest {
    private final Animal testW;
    private final String[] targetsE3;
    private final String[] targetsA1;
    private final String[] targetsH8;
    private final String toTest;
    private Animal test;

    public AnimalTest(String toTest, Animal test, Animal testW, String[] targetsE3, String[] targetsA1, String[] targetsH8) {
        this.toTest = toTest;
        this.test = test;
        this.testW = testW;
        this.targetsE3 = targetsE3;
        this.targetsA1 = targetsA1;
        this.targetsH8 = targetsH8;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Elephant", new Elephant(false), new Elephant(true),
                        new String[]{"e4,", "e5", "e6", "e7", "e8", "e2", "e1", "f3", "g3", "h3", "d3", "c3", "b3", "a3"},
                        new String[]{"a2", "a3", "a4", "a5", "a6", "a7", "a8", "b1", "c1", "d1", "e1", "f1", "g1", "h1"},
                        new String[]{"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h1", "h2", "h3", "h4", "h5", "h6", "h7"},
                },
                {"Horse", new Horse(false), new Horse(true),
                        new String[]{"d3", "f3", "e2", "e4", "c1", "g1", "c5", "b6", "g5", "h6"},
                        new String[]{"c3", "d4", "a2", "b1"},
                        new String[]{"f6", "e5", "h7", "g8"},
                },
                {"Leopard", new Leopard(false), new Leopard(true),
                        new String[]{"e4,", "e5", "e6", "e7", "e8", "e2", "e8", "f3", "g3", "h3", "d3", "c3", "b3", "a3",
                                "d4", "c5", "b6", "a7", "f4", "g5", "h6", "d2", "c1", "f2", "g1"},
                        new String[]{"a2", "a3", "a4", "a5", "a6", "a7", "a8", "b1", "c1", "d1", "e1", "f1", "g1", "h1",
                                "b2", "c3", "d4", "e5", "f6", "g7", "h8"},
                        new String[]{"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h1", "h2", "h3", "h4", "h5", "h6", "h7",
                                "b2", "c3", "d4", "e5", "f6", "g7", "a1"},
                },
                {"Penguin", new Penguin(false), new Penguin(true),
                        new String[]{"d4", "e4", "f4", "d3", "f3", "d2", "e2", "f2"},
                        new String[]{"a2", "b2", "b1"},
                        new String[]{"g8", "g7", "h7"},
                },
                {"Rabbit", new Rabbit(false), new Rabbit(true),
                        new String[]{"d4", "e4", "f4", "d3", "f3", "d2", "e2", "f2"},
                        new String[]{"a2", "b2", "b1"},
                        new String[]{"g8", "g7", "h7"},
                },
                {"Snake", new Snake(false), new Snake(true),
                        new String[]{"d2", "d4", "e5", "d6", "e7", "d8",
                                "f2", "f4", "g3", "h4", "e1", "c3", "b2", "a3"},
                        new String[]{"b2", "c1", "d2", "e1", "f2", "g1", "h2"},
                        new String[]{"b8", "d8", "f8", "a7", "c7", "e7", "g7"},
                },
        });
    }

    @Before
    public void setUp() throws Exception {
        test.position = new Position();
        test.position.myAnimals = new Animal[]{test};

    }


    @Test
    public void constructor_givenFalse_AnimalIsM() throws Exception {
        Assert.assertEquals(toTest + " shall be Male", false, test.female);
        Assert.assertEquals(toTest + " shall be Female", true, testW.female);
    }

    @Test
    public void constructor_AnimalIsAlive() throws Exception {
        Assert.assertEquals(toTest + " Male shall be Alive", true, test.alive);
        Assert.assertEquals(toTest + " Female shall be Alive", true, testW.alive);
    }

    @Test
    public void possibleMoves_givenEmptyBoard_givenE3() throws Exception {
        Move[] exp = buildMoves("e3", targetsE3);

        Move[] actual = test.possibleMoves();

        assertMoves(exp, actual);
    }

    @Test
    public void possibleMoves_givenEmptyBoard_givenA1() throws Exception {
        Move[] exp = buildMoves("a1", targetsA1);

        Move[] actual = test.possibleMoves();

        assertMoves(exp, actual);
    }

    @Test
    public void possibleMoves_givenEmptyBoard_givenH8() throws Exception {
        Move[] exp = buildMoves("h8", targetsH8);

        Move[] actual = test.possibleMoves();

        assertMoves(exp, actual);
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

    private Move[] buildMoves(String start, String... targets) {
        test.square = start;
        return Arrays.stream(targets).map(to -> new Move(start + to))
                .toArray(Move[]::new);
    }

}
