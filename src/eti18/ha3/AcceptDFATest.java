package eti18.ha3;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

@RunWith(Parameterized.class)
public class AcceptDFATest {
    private final DFA d;
    private final String w;
    private final boolean expected;

    @Parameterized.Parameters(name = "{index}: {1} -> {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"abababc", false},
                {"cc", true},
                {"ccc", true},
                {"caaabbcabaabc", true},
                {"x", false},
                {"ccx", false},
        });
    }


    public AcceptDFATest(String w, boolean expected) {
        String d = "DFA\n" +
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
                "END";
        this.d = (DFA) Parser.parse(new Scanner(d));
        this.w = w;
        this.expected = expected;
    }


    @Test
    public void test_accept() {
        Assert.assertEquals("\nd: " + d + "\nw: " + w + expected, expected, AcceptDFA.accept(d, w));
    }
}