package blatt09.fight;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class PositionTest {
    private Position test;
    private char next;

    @Before
    public void setUp() throws Exception {
        test = new Position();
        next = 'W';
        test.reset(next);
    }

    @Test
    public void reset_givenW_thenWStares() throws Exception {
        next = 'W';
        test.reset(next);

        assertNext(next);
    }

    @Test
    public void reset_givenM_thenMStares() throws Exception {
        next = 'M';
        test.reset(next);

        assertNext(next);
    }

    @Test
    public void reset_thenBuildsStartBoard() throws Exception {
        test.reset(next);

        assertBoardReset(next);
    }

    private void assertNext(char expected) {
        Assert.assertEquals("Position.next is wrong:\n"
                + test.next, expected, test.next);
    }

    private void assertBoardReset(char next) {
        Animal[] expected = {
                new Snake(false), new Elephant(false), new Horse(false), new Leopard(false), new Leopard(false), new Horse(false), new Elephant(false), new Snake(false),
                new Penguin(false), new Rabbit(false), new Rabbit(false), new Rabbit(false), new Rabbit(false), new Rabbit(false), new Rabbit(false), new Penguin(false),

                new Penguin(true), new Rabbit(true), new Rabbit(true), new Rabbit(true), new Rabbit(true), new Rabbit(true), new Rabbit(true), new Penguin(true),
                new Snake(true), new Elephant(true), new Horse(true), new Leopard(true), new Leopard(true), new Horse(true), new Elephant(true), new Snake(true)
        };

        Assert.assertEquals("Position.nrAnimals is wrong:\n",
                16, test.nrAnimals);
        Assert.assertEquals("Position.myAnimals is wrong:\n", boardToString(expected, 2, 7, next), test.toString());
    }

    private String boardToString(Animal[] expected, int le, int ge, char next) {
        final int[] I = {8, 7, 6, 5, 4, 3, 2, 1};
        final String[] J = {"a", "b", "c", "d", "e", "f", "g", "h"};

        int index = 0;
        String str = "   a b c d e f g h\n";
        for (int i : I) {
            str += (i + " ");
            for (String j : J)
                if (i <= le || i >= ge) str += expected[index++].toString();
                else
                    str += (i + (j.charAt(0) - 'a')) % 2 == 1 ? Globals.ts_empty_square_dark : Globals.ts_empty_square_light;
            str += " " + i + "\n";
        }
        str += "  a b c d e f g h\nIt is " + next + "'s turn.\n";
        return str;
    }

    @Test
    public void applyMoves_givenWMoved_thenNextIsM() throws Exception {
        test.next = 'W';
        test.applyMoves(null);
        assertNext('M');
    }

    @Test
    public void applyMoves_givenMMoved_thenNextIsW() throws Exception {
        test.next = 'M';
        test.applyMoves(null);
        assertNext('W');
    }

    @Test
    public void applyMoves_givenNull_thenBoardStaysTheSame() throws Exception {
        test.next = 'W';
        test.applyMoves(null);

        assertBoardReset();
    }

    @Test
    public void applyMoves_givenEmptyMove_thenBoardStaysTheSame() throws Exception {
        test.applyMoves(new Move[0]);

        assertBoardReset();
    }

    private void assertBoardReset() {
        assertBoardReset('M');
    }

    @Test
    public void applyMoves_given1Animal_given_a1h8_thenMovesPieceAtA1ToH8() throws Exception {
        test.myAnimals = buildAnimals("a1");

        test.applyMoves(buildMoves("a1h8"));

        assertAnimalPositions("h8");
    }

    @Test
    public void applyMoves_given4Animal_given4Moves_thenAppliesMoves() throws Exception {
        test.myAnimals = buildAnimals("a1", "a2", "a3", "a4");

        test.applyMoves(buildMoves("a1h8", "a2h7", "a3h6", "a4h5"));

        assertAnimalPositions("h8", "h7", "h6", "h5");
    }

    @Test
    public void applyMoves_given4Animal_givenSwitchPositions_thenAppliesMoves() throws Exception {
        test.myAnimals = buildAnimals("a1", "h8", "a8", "h1");

        test.applyMoves(buildMoves("a1h8", "h8a1", "a8h1", "h1a8"));

        assertAnimalPositions("h8", "a1", "h1", "a8");
    }

    @Test
    public void applyMoves_given4Animal_givenKillAnimal_thenRemovesDeadFormList() throws Exception {
        test.myAnimals = buildAnimals("a1", "h8", "a8", "h1");
        test.myAnimals[0] = buildPredator("a1");

        test.applyMoves(buildMoves("a1h8"));

        assertAnimalPositions("h8", "a8", "h1");
    }

    private Predator buildPredator(String position) {
        Predator predator = buildPredator(false);
        predator.square = position;
        return predator;
    }

    private Animal[] buildAnimals(String... strings) {
        Animal[] out = new Animal[strings.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = new Animal(true);
            out[i].square = strings[i];
        }

        return out;
    }

    private Move[] buildMoves(String... moves) {
        return Arrays.stream(moves).map(Move::new).toArray(Move[]::new);
    }

    private void assertAnimalPositions(String... expected) {
        for (int i = 0; i < test.myAnimals.length; i++)
            Assert.assertEquals(expected[i], test.myAnimals[i].square);
    }

    @Test
    public void theWinner_givenNullBoard_returnsN() throws Exception {
        test.myAnimals = null;
        assertTheWinner('N');
    }

    @Test
    public void theWinner_givenEmptyBoard_returnsN() throws Exception {
        test.myAnimals = new Animal[0];
        assertTheWinner('N');
    }

    @Test
    public void theWinner_given1Female_returnsF() throws Exception {
        test.myAnimals = new Animal[]{new Animal(true)};
        assertTheWinner('W');
    }

    @Test
    public void theWinner_given1Male_returnsM() throws Exception {
        test.myAnimals = new Animal[]{new Animal(false)};
        assertTheWinner('M');
    }

    @Test
    public void theWinner_givenNoPredators_given1Female_given1Male_returnsN() throws Exception {
        test.myAnimals = new Animal[]{new Vegetarian(true), new Vegetarian(false)};
        assertTheWinner('N');
    }

    @Test
    public void theWinner_givenNoPredators_given2Female_given1Male_returnsW() throws Exception {
        test.myAnimals = new Animal[]{new Vegetarian(true), new Vegetarian(true), new Vegetarian(false)};
        assertTheWinner('W');
    }

    @Test
    public void theWinner_givenNoPredators_given1Female_given2Male_returnsM() throws Exception {
        test.myAnimals = new Animal[]{new Vegetarian(true), new Vegetarian(false), new Vegetarian(false)};
        assertTheWinner('M');
    }

    @Test
    public void theWinner_given1FemalePredator_given1Male_returnsX() throws Exception {
        test.myAnimals = new Animal[]{buildPredator(true), new Vegetarian(false)};
        assertTheWinner('X');
    }

    private Predator buildPredator(boolean female) {
        return new Predator(female) {
            @Override
            public void eat() {
            }
        };
    }

    @Test
    public void theWinner_given1Female_given1MalePredator_returnsX() throws Exception {
        test.myAnimals = new Animal[]{new Vegetarian(true), buildPredator(false)};
        assertTheWinner('X');
    }

    private void assertTheWinner(char expected) {
        Assert.assertEquals("Position.theWinner() is wrong:\n" +
                test.theWinner(), expected, test.theWinner());
    }

    @Test
    public void getMoves_givenEmptyField_returnsEmptyString() throws Exception {
        test.myAnimals = new Animal[0];
        assertGetMoves();
    }

    @Test
    public void getMoves_givenPenguinA1_givenNextW_returnsWMoves() throws Exception {
        arrange2PenguinsA1H8();
        test.next = 'W';

        assertGetMoves("a1a2", "a1b2", "a1b1");
    }

    @Test
    public void getMoves_givenPenguinH8_givenNextM_returnsMMoves() throws Exception {
        arrange2PenguinsA1H8();
        test.next = 'M';

        assertGetMoves("h8g8", "h8g7", "h8h7");
    }

    private void arrange2PenguinsA1H8() {
        test.myAnimals = new Animal[]{new Penguin(true), new Penguin(false)};
        test.myAnimals[0].square = "a1";
        test.myAnimals[1].square = "h8";
    }

    private void assertGetMoves(String... moves) {
        Assert.assertNotNull("getMovesString() shall not return null!", test.getMovesString());
//        Assert.assertEquals("We have length differences at getMovesString()", moves.length, test.getMovesString().length() / 5);
        for (String move : moves)
            Assert.assertTrue("test.getMovesString() shall contain: " + move, test.getMovesString().contains(move));
    }

    @Test
    public void getPredatorWithoutFood_givenPenguinA1_givenNextW_returnsWInfo() throws Exception {
        arrange2PenguinsA1H8();
        test.next = 'W';

        assertGetPredatorWithoutFood("a1", "12");
    }

    @Test
    public void getPredatorWithoutFood_givenPenguinH8_givenNextM_returnsMInfo() throws Exception {
        arrange2PenguinsA1H8();
        test.next = 'M';

        assertGetPredatorWithoutFood("h8", "12");
    }

    private void assertGetPredatorWithoutFood(String... expected) {
        String actual = test.getPredatorWithoutFood();
        Assert.assertNotNull("getPredatorWithoutFood()", actual);

        for (String s : expected)
            Assert.assertTrue("getPredatorWithoutFood() shall contain: " + s +
                    " \nactual: " + actual, actual.contains(s));
    }

    @Test
    public void sunset_givenPredator_givenFoodIs12_thenFoodIs11() throws Exception {
        Predator predator = new Penguin(true);
        test.myAnimals = new Animal[]{predator};

        test.sunset();

        int actual = predator.withoutFood;
        Assert.assertEquals("Predator.getWithoutFood() is wrong:\n" + actual,
                11, actual);
        assertAnimals(new Animal[]{predator});
    }

    @Test
    public void sunset_givenPredator_givenNotAlive_thenPredatorIsRemoved() throws Exception {
        Predator predator = new Penguin(true);
        predator.alive = false;
        test.myAnimals = new Animal[]{predator};

        test.sunset();

        assertAnimals(new Animal[0]);
    }

    private void assertAnimals(Animal[] expected) {
        Animal[] actual = test.myAnimals;
        Assert.assertArrayEquals("Position.myAnimals is wrong: \n"
                + actual, expected, actual);
    }
}