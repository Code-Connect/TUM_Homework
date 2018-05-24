package eti18.ha5;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;


@RunWith(Parameterized.class)
public class IsFiniteTest {

    private final DFA dfa;
    private final boolean expected;

    @Parameterized.Parameters(name = "{index}: {0} -> {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"DFA\n" +
                        "Alphabet: a;b;c\n" +
                        "States: p;q;r\n" +
                        "Init: p\n" +
                        "Final: r\n" +
                        "Transitions:\n" +
                        "p;a;p\n" +
                        "p;b;p\n" +
                        "p;c;q\n" +
                        "q;a;q\n" +
                        "q;b;q\n" +
                        "q;c;r\n" +
                        "r;a;r\n" +
                        "r;b;r\n" +
                        "r;c;r\n" +
                        "END", false},
                {"DFA\n" +
                        "Alphabet: a\n" +
                        "States: p;q\n" +
                        "Init: p\n" +
                        "Final: q\n" +
                        "Transitions:\n" +
                        "p;a;q\n" +
                        "q;a;q\n" +
                        "END", false},
                {"DFA\n" +
                        "Alphabet: a\n" +
                        "States: p;q;f\n" +
                        "Init: p\n" +
                        "Final: q\n" +
                        "Transitions:\n" +
                        "p;a;q\n" +
                        "q;a;f\n" +
                        "f;a;f\n" +
                        "END", true},
        });
    }

    public IsFiniteTest(String dfa, boolean expected) {
        this.dfa = (DFA) Parser.parse(new Scanner(dfa));
        this.expected = expected;
    }

    @Test
    public void isFinite() {
        Assert.assertEquals("\nd: " + dfa,
                expected, IsFinite.isFinite(dfa));
    }
}