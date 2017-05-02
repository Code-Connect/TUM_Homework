package pgdp16.blatt09.fight;

import org.junit.Assert;
import org.junit.Test;

public class MoveTest {
    private Move test;

    @Test
    public void constructor_given_a1a2() throws Exception {
        String input = "a1a2";
        test = new Move(input);
        assertToString(input);
    }

    @Test
    public void constructor_given_a7c5() throws Exception {
        String input = "a7c5";
        test = new Move(input);
        assertToString(input);
    }

    @Test
    public void constructor_given_a1_given_a2() throws Exception {
        test = new Move("a1", "a2");
        assertToString("a1a2");
    }

    @Test
    public void constructor_given_a7_given_c5() throws Exception {
        test = new Move("a7", "c5");
        assertToString("a7c5");
    }

    private void assertToString(String input) {
        Assert.assertEquals("ToString() is wrong:\n", input, test.toString());
    }

    @Test
    public void equals_given_a1a2_givenNull_returnsFalse() throws Exception {
        test = new Move("a1a2");

        assertEquals(false, null);
    }

    @Test
    public void equals_given_a1a2_given_a1a2_returnsTrue() throws Exception {
        test = new Move("a1a2");

        assertEquals(true, new Move("a1a2"));
    }

    @Test
    public void equals_given_a1a2_given_a7c5_returnsFalse() throws Exception {
        test = new Move("a1a2");

        assertEquals(false, new Move("a7c5"));
    }

    private void assertEquals(boolean expected, Move other) {
        Assert.assertEquals("equals() is wrong:\n" +
                "this: " + test + "\n" +
                "other: " + other, expected, test.equals(other));
    }
}