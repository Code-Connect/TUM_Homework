package blatt12.rock;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static blatt12.rock.RockPaperScissors.determineWinner;


public class RockPaperScissorsTest {
    @Test
    public void determineWinner_givenSame_return0() throws Exception {
        Assert.assertEquals(0, determineWinner(0, 0));
        Assert.assertEquals(0, determineWinner(1, 1));
        Assert.assertEquals(0, determineWinner(2, 2));
    }

    @Test
    public void determineWinner_given1Wins_return1() throws Exception {
        Assert.assertEquals(1, determineWinner(1, 0));
        Assert.assertEquals(1, determineWinner(2, 1));
        Assert.assertEquals(1, determineWinner(0, 2));
    }

    @Test
    public void determineWinner_given2Wins_return2() throws Exception {
        Assert.assertEquals(2, determineWinner(0, 1));
        Assert.assertEquals(2, determineWinner(1, 2));
        Assert.assertEquals(2, determineWinner(2, 0));
    }

    @Test(timeout = 1500)
    public void run_1Game() throws Exception {
        Thread game = new Thread(new RockPaperScissors());
        game.start();
        game.join();
        Assert.assertFalse(game.isAlive());
    }

    @Test(timeout = 15000)
    @Ignore
    public void run_10Games() throws Exception {
        for (int i = 0; i <= 10; i++) run_1Game();
    }


}