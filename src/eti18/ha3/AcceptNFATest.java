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
public class AcceptNFATest {
    private final EpsilonNFA n;
    private final String w;
    private final boolean expected;

    @Parameterized.Parameters(name = "{index}: {1} -> {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"eDFA\n" +
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
                        "END", "abababc", false},
                {"eDFA\n" +
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
                        "END", "cc", true},
                {"eDFA\n" +
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
                        "END", "ccc", true},
                {"eDFA\n" +
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
                        "END", "caaabbcabaabc", true},
                {"eDFA\n" +
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
                        "END", "x", false},
                {"eDFA\n" +
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
                        "END", "ccx", false},
        });
    }


    public AcceptNFATest(String n, String w, boolean expected) {
        this.n = Parser.parse(new Scanner(n));
        this.w = w;
        this.expected = expected;
    }


    @Test
    public void test_accept() {
        Assert.assertEquals("\nn: "+ n + "\nw: "+ w + expected ,expected, AcceptNFA.accept(n,w));
    }
}