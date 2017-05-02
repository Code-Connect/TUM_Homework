package pgdp16.blatt07;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class DameSpielTest extends DameSpiel {
    private static final boolean LOG_BOARD = true;


    private static final String PLAYER1 = "player1";
    private static final String PLAYER2 = "player2";
    private ArrayList<Integer> readIntOutput;
    private ArrayList<String> readStringOutput;
    private String writeInput;
    private int readIntCounter;

    public DameSpielTest() {
        super(PLAYER1, PLAYER2);
    }

    @Before
    public void setUp() throws Exception {
        readIntOutput = new ArrayList<>();
        readStringOutput = new ArrayList<>();
        writeInput = "";
        readIntCounter = 0;
    }

    @Test
    public void constructor_given2Names_regesterNames() throws Exception {
        Assert.assertEquals(PLAYER1, white);
        Assert.assertEquals(PLAYER2, black);
    }

    @Test
    public void determineBoardSize_given56_thenAIs5BIs6() throws Exception {
        addReadIntOutput(5, 6);
        determineBoardSize();

        assertBoardSize(5, 6);
    }

    private void addReadIntOutput(int... ins) {
        for (int in : ins) readIntOutput.add(in);
    }

    private void assertBoardSize(int a, int b) {
        Assert.assertEquals(a, nrColumns);
        Assert.assertEquals(b, nrRows);
    }

    @Test
    public void determineBoardSize_givenInvalidInput_thenAskAgain() throws Exception {
        addReadIntOutput(4, 9, 5, 3, 7, 6);
        determineBoardSize();

        assertBoardSize(5, 6);
    }

    @Override
    public int readInt(String text) {
        readIntCounter++;
        return readIntOutput.remove(0);
    }

    @Test
    public void initBoard_given56_buildBord5x6() throws Exception {
        nrColumns = 5;
        nrRows = 6;
        initBoard();
        assertBoard(new boolean[5][6]);
    }

    @Test
    public void determineFirstPlayer_given1_thenWhiteToMoveIsTrue() throws Exception {
        addReadIntOutput(1);
        addReadStringOutput("true");
        determineFirstPlayer();

        Assert.assertEquals(true, whiteToMove);
    }

    private void addReadStringOutput(String... ins) {
        Collections.addAll(readStringOutput, ins);
    }

    @Test
    public void determineFirstPlayer_given0_thenWhiteToMoveIsFalse() throws Exception {
        addReadIntOutput(0);
        addReadStringOutput("false");
        determineFirstPlayer();

        Assert.assertEquals(false, whiteToMove);
    }

    @Test
    public void determineFirstPlayer_givenInvaledInput_thenAskAgain() throws Exception {
        addReadIntOutput(2, 1);
        addReadStringOutput("yes", "true");
        determineFirstPlayer();

        assertFirstPlayer(true, 1);
    }

    private void assertFirstPlayer(boolean whiteMove, int outSize) {
        Assert.assertEquals(whiteMove, whiteToMove);
        Assert.assertNotEquals(outSize, readIntOutput.size());
        Assert.assertNotEquals(outSize, readStringOutput.size());
    }

    @Test
    public void determineFirstPlayer_givenUpperCaseTrue_thenWhiteToMoveIsTrue() throws Exception {
        readIntOutput.add(1);
        readStringOutput.add("True");
        determineFirstPlayer();

        assertFirstPlayer(true, -1);
    }

    @Override
    public String readString(String input) {
        return readStringOutput.remove(0);
    }

    @Test
    public void reportWinner_givenWhiteHadLastMove_thenWhiteWon() throws Exception {
        whiteToMove = false;
        reportWinner();
        assertReportWinner(PLAYER1);
    }

    private void assertReportWinner(String player) {
        Assert.assertTrue(writeInput.contains(player));
    }

    @Test
    public void reportWinner_givenBlackHadLastMove_thenBlackWon() throws Exception {
        whiteToMove = true;
        reportWinner();
        assertReportWinner(PLAYER2);
    }

    @Override
    public void write(String output) {
        writeInput = output;
    }

    @Test
    public void applyMove_given11_thenXAt00() throws Exception {
        nrColumns = 1;
        nrRows = 1;
        initBoard();
        boolean[][] expected = {{true}};

        applyMove(11);
        assertBoard(expected);
    }

    private void assertBoard(boolean[][] expected) {
        Assert.assertArrayEquals(expected, board);
    }

    @Test
    public void applyMove_given23_thenXAt12() throws Exception {
        nrColumns = 2;
        nrRows = 3;
        initBoard();
        boolean[][] expected = new boolean[2][3];
        expected[1][2] = true;

        applyMove(23);
        assertBoard(expected);
    }

    @Test
    public void mainLoop_givenN1_thenEnd() throws Exception {
        nrColumns = 1;
        nrColumns = 1;
        initBoard();
        addReadIntOutput(-1);
        mainLoop();
        assertReadIntCounter(1);
    }

    private void assertReadIntCounter(int expected) {
        Assert.assertEquals(expected, readIntCounter);
    }

    @Test
    public void mainLoop_given2x2Board_thenEnd() throws Exception {
        nrColumns = 2;
        nrRows = 2;
        initBoard();
        addReadIntOutput(11);

        mainLoop();
        assertReadIntCounter(1);
    }

    @Test
    public void mainLoop_given3x3Board_thenEnd() throws Exception {
        nrColumns = 3;
        nrRows = 3;
        initBoard();
        addReadIntOutput(11, 23);

        mainLoop();
        assertReadIntCounter(2);
    }

    @Test
    public void mainLoop_givenInvalidInput_given3x3Board_thenAskAgain() throws Exception {
        nrColumns = 3;
        nrRows = 3;
        initBoard();
        addReadIntOutput(11, 22, 23);

        mainLoop();
        assertReadIntCounter(3);
    }

    @Test
    public void integration_givenWhiteStarts_thenWhiteWins() throws Exception {
        addReadIntOutput(5, 5, 11, 23, 42, 35, 54);
        addReadStringOutput("true");

        startGame();
        assertReadIntCounter(7);
        assertReportWinner(PLAYER1);
    }

    @Override
    void printBoard() {
        if (LOG_BOARD) super.printBoard();
    }
}
