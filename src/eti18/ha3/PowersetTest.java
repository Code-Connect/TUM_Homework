package eti18.ha3;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PowersetTest {
    private final NFA n;
    private final DFA expected;

    @Parameterized.Parameters(name = "{index}: {0} -> {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"NFA\n" +
                        "Alphabet: a\n" +
                        "States: p;q\n" +
                        "Init: p\n" +
                        "Final: q\n" +
                        "Transitions:\n" +
                        "p;a;p\n" +
                        "p;a;q\n" +
                        "q;a;q\n" +
                        "END",
                        "DFA\n" +
                                "Alphabet: a\n" +
                                "States: [p];[q, p]\n" +
                                "Init: [p]\n" +
                                "Final: [q, p]\n" +
                                "Transitions:\n" +
                                "[q, p];a;[q, p]\n" +
                                "[p];a;[q, p]\n" +
                                "END"},
        });
    }

    public PowersetTest(String d, String expected) {
        this.expected = (DFA) Parser.parse(new Scanner(expected));
        this.n = (NFA) Parser.parse(new Scanner(d));
    }

    @Test
    public void test_accept() {
        Assert.assertEquals(expected, Powerset.powersetConstruction(n));
    }
}