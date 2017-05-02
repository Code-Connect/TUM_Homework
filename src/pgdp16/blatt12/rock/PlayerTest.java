package pgdp16.blatt12.rock;

import org.junit.*;
import pgdp16.blatt12.map.MapTest;

import java.util.ArrayList;

public class PlayerTest {

    private static final long WAIT_MS = 1000;
    ArrayList<Integer> choices;
    private Player test;

    @Before
    public void setUp() throws Exception {
        choices = new ArrayList<>();
        test = new Player() {
            @Override
            public void run() {
                MapTest.sleepFor(WAIT_MS);
                super.run();
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        choices.clear();
    }

    @Test
    public void run_generatesRandom_between0And2() throws Exception {
        new Thread(test).start();
        assertChoiceRange(test.getChoice());
    }

    private void assertChoiceRange(int choice) {
        Assert.assertTrue(choice <= 2 && choice >= 0);

        choices.add(choice);
        System.err.println("\nchoices: " + choices);
    }


    @Test(timeout = 10000)
    public void getChoice_called2Times() throws Exception {
        new Thread(test).start();
        assertChoiceRange(test.getChoice());
        assertChoiceRange(test.getChoice());
    }


    @Test(timeout = 10000)
    @Ignore //optional
    public void interrupt_withoutGetChoice_givenRunning() throws Exception {
        Thread thread = arrangeNewGame();

        assertInterrupt(thread);
    }

    private Thread arrangeNewGame() {
        Thread thread = new Thread(test);
        thread.start();
        return thread;
    }

    private void assertInterrupt(Thread thread) throws InterruptedException {
        thread.interrupt();
        thread.join();

        Assert.assertFalse(thread.isAlive());
    }

    @Test(timeout = 10000)
    public void interrupt_givenRunning() throws Exception {
        Thread thread = arrangeNewGame();

        test.getChoice();
        assertInterrupt(thread);
    }

    @Test(timeout = 10000)
    public void getChoice_beforeStart() throws Exception {
        new Thread(() -> {
            try {
                assertChoiceRange(test.getChoice());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(test).start();
    }
}