package pgdp16.blatt09.fight;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class GameTest extends Game {
    private int printBoardCount;
    private int printMovesCount;
    private int printPredatorWithoutFoodCount;

    private ArrayList<String> readStringOutput;
    private ArrayList<Integer> readIntOutput;
    private ArrayList<Character> theWinnerOutput;
    private int sunsetCount;
    private ArrayList<Move[]> applyMovesInput;

    @Before
    public void setUp() throws Exception {
        printBoardCount = 0;
        printMovesCount = 0;
        printPredatorWithoutFoodCount = 0;
        sunsetCount = 0;

        readIntOutput = new ArrayList<>();
        applyMovesInput = new ArrayList<>();
        theWinnerOutput = new ArrayList<>();
        theWinnerOutput.add('N');
        theWinnerOutput.add('N');
        readStringOutput = new ArrayList<>();
        setReadStringOutput("", "");

        io = new IO() {
            @Override
            public String readString(String msg) {
                return readStringOutput.remove(0);
            }

            @Override
            public int readInt(String msg, int low, int high) {
                return readIntOutput.remove(0);
            }

            @Override
            public int readInt(String msg) {
                return readIntOutput.remove(0);
            }

            @Override
            public int readInt() {
                return readIntOutput.remove(0);
            }
        };
        pos = new Position() {
            @Override
            public char theWinner() {
                return theWinnerOutput.remove(0);
            }

            @Override
            public void sunset() {
                sunsetCount++;
            }

            @Override
            public void applyMoves(Move[] move) {
                applyMovesInput.add(move);
            }
        };
        pos.reset('W');
    }

    @Test
    public void loop_givenGameOver_thenPrintsBoardOnce() throws Exception {
        loop();
        Assert.assertEquals("printBoard() shall be called",
                1, printBoardCount);
    }

    @Test
    public void loop_givenGameOver_thenPrintsPredatorWithoutFoodOnce() throws Exception {
        loop();
        Assert.assertEquals("printMoves() shall be called",
                1, printPredatorWithoutFoodCount);
    }

    @Test
    public void loop_givenM_givenGameOver_thenPrintsMovesOnce() throws Exception {
        setReadStringOutput("m");
        loop();
        Assert.assertEquals("printMoves() shall be called",
                1, printMovesCount);
    }

    @Test
    public void loop_given1Round_thenPrintsBoardTwice() throws Exception {
        arrange1TurnBothPlay();
        loop();
        Assert.assertEquals("printBoard() shall be called",
                2, printBoardCount);
    }

    @Test
    public void loop_given1Round_thenPrintsPredatorWithoutFoodTwice() throws Exception {
        arrange1TurnBothPlay();
        loop();
        Assert.assertEquals("printPredatorWithoutFood() shall be called",
                2, printPredatorWithoutFoodCount);
    }

    @Test
    public void loop_given1Round_thenCallsSunset() throws Exception {
        arrange1TurnBothPlay();
        loop();
        Assert.assertEquals("sunset() shall be called after every round",
                1, sunsetCount);
    }

    @Test
    public void loop_givenInvalidMove_thenAskAgain() throws Exception {
        setReadStringOutput("a1a1", "a2a3", "b2b3", "c2c3", "d2d3", "");

        loop();

        assertApplyMoves("a2a3", "b2b3", "c2c3", "d2d3");
    }

    @Test
    public void loop_given2PredatorMoves_thenAccepts1() throws Exception {
        setReadStringOutput("a2a3", "h2h3", "");

        loop();

        assertApplyMoves("a2a3");
    }

    @Test
    public void loop_given4VegetariansMoves_given1Round_thenAccepts1() throws Exception {
        setReadStringOutput("b2b3", "c2c3", "d2d3", "e2e3", "");

        loop();

        assertApplyMoves("b2b3", "c2c3", "d2d3");
    }

    @Test
    public void loop_given2MovesHaveSameStart_given1Round_thenAccepts1() throws Exception {
        setReadStringOutput("b2b3", "b2c3", "");

        loop();

        assertApplyMoves("b2b3");
    }

    @Test
    public void loop_given2MovesHaveSameTarget_given1Round_thenAccepts1() throws Exception {
        setReadStringOutput("b2b3", "c2b3", "");

        loop();

        assertApplyMoves("b2b3");
    }

    private void assertApplyMoves(String... expected) {
        Assert.assertFalse("applyMoves() shall be called!", applyMovesInput.isEmpty());
        String actual = Arrays.toString(applyMovesInput.get(0));

        Assert.assertEquals("applyMoves() shall only be called once per Turn",
                1, applyMovesInput.size());
        Assert.assertEquals("applyMoves() was called with:" + actual,
                Arrays.toString(expected), actual);
    }

    private void arrange1TurnBothPlay() {
        theWinnerOutput.add(0, 'X');
        theWinnerOutput.add(1, 'N');
    }

    private void setReadStringOutput(String... output) {
        for (int i = 0; i < output.length; i++) readStringOutput.add(i, output[i]);
    }

    @Override
    public void printBoard() {
        printBoardCount++;
    }

    @Override
    public void printPredatorWithoutFood() {
        printPredatorWithoutFoodCount++;
    }

    @Override
    public void printMoves() {
        printMovesCount++;
    }
}