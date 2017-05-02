package pgdp16.blatt09.fight;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MainTest {

    private ArrayList<Boolean> startGameInput;
    private ArrayList<String> readStringOutput;
    private ArrayList<Integer> readIntOutput;

    @Before
    public void setUp() throws Exception {
        startGameInput = new ArrayList<>();
        readStringOutput = new ArrayList<>();
        readIntOutput = new ArrayList<>();

        Main.game = new Game() {
            @Override
            public void startGame(boolean ladiesFirst) {
                startGameInput.add(ladiesFirst);
            }
        };
        Main.io = new IO() {
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
    }

    @Test
    public void main_givenTrue_thenLadiesFirst() throws Exception {
        readStringOutput.add("true");
        readIntOutput.add(1);

        Main.main(null);

        Assert.assertEquals(1, startGameInput.size());
        Assert.assertEquals(true, startGameInput.get(0));
    }


    @Test
    public void main_givenFalse_thenNotLadiesFirst() throws Exception {
        readStringOutput.add("false");
        readIntOutput.add(0);

        Main.main(null);

        Assert.assertEquals(1, startGameInput.size());
        Assert.assertEquals(false, startGameInput.get(0));
    }

    @Test
    public void main_givenInvalid_givenFalse_thenAskAgain() throws Exception {
        readStringOutput.add("no");
        readIntOutput.add(-1);
        readStringOutput.add("false");
        readIntOutput.add(0);

        Main.main(null);

        Assert.assertEquals(1, startGameInput.size());
        Assert.assertEquals(false, startGameInput.get(0));
    }

    @Test
    public void main_givenInvalid_givenTrue_thenAskAgain() throws Exception {
        readStringOutput.add("yes");
        readIntOutput.add(2);
        readStringOutput.add("true");
        readIntOutput.add(1);

        Main.main(null);

        Assert.assertEquals(1, startGameInput.size());
        Assert.assertEquals(true, startGameInput.get(0));
    }


}