package eti18.ha5;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

@RunWith(Parameterized.class)
public class IntersectionTest {

    private final DFA dfa1;
    private final DFA dfa2;
    private final DFA expected;

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
                        "END",
                        "DFA\n" +
                                "Alphabet: a;b;c\n" +
                                "States: p;q;r;s\n" +
                                "Init: p\n" +
                                "Final: s\n" +
                                "Transitions:\n" +
                                "p;a;q\n" +
                                "p;b;q\n" +
                                "p;c;q\n" +
                                "q;a;r\n" +
                                "q;b;p\n" +
                                "q;c;q\n" +
                                "r;a;r\n" +
                                "r;b;s\n" +
                                "r;c;r\n" +
                                "s;a;s\n" +
                                "s;b;q\n" +
                                "s;c;s\n" +
                                "END",
                        "DFA\n" +
                                "Alphabet: a;b;c\n" +
                                "States: (p,r);(p,s);(q,p);(q,q);(q,r);(q,s);(r,p);(r,q);(r,r);(r,s);(p,p);(p,q)\n" +
                                "Init: (p,p)\n" +
                                "Final: (r,s)\n" +
                                "Transitions:\n" +
                                "(r,r);a;(r,r)\n" +
                                "(q,r);a;(q,r)\n" +
                                "(p,r);a;(p,r)\n" +
                                "(r,q);b;(r,p)\n" +
                                "(q,q);b;(q,p)\n" +
                                "(r,r);c;(r,r)\n" +
                                "(p,q);b;(p,p)\n" +
                                "(p,r);c;(q,r)\n" +
                                "(q,p);c;(r,q)\n" +
                                "(r,p);a;(r,q)\n" +
                                "(q,p);a;(q,q)\n" +
                                "(p,p);a;(p,q)\n" +
                                "(q,q);c;(r,q)\n" +
                                "(q,s);c;(r,s)\n" +
                                "(r,p);c;(r,q)\n" +
                                "(r,p);b;(r,q)\n" +
                                "(q,p);b;(q,q)\n" +
                                "(r,r);b;(r,s)\n" +
                                "(p,p);b;(p,q)\n" +
                                "(q,r);b;(q,s)\n" +
                                "(r,s);a;(r,s)\n" +
                                "(q,s);a;(q,s)\n" +
                                "(p,r);b;(p,s)\n" +
                                "(p,s);a;(p,s)\n" +
                                "(r,s);b;(r,q)\n" +
                                "(q,s);b;(q,q)\n" +
                                "(p,s);b;(p,q)\n" +
                                "(r,q);c;(r,q)\n" +
                                "(r,s);c;(r,s)\n" +
                                "(p,p);c;(q,q)\n" +
                                "(p,q);c;(q,q)\n" +
                                "(p,s);c;(q,s)\n" +
                                "(r,q);a;(r,r)\n" +
                                "(q,q);a;(q,r)\n" +
                                "(p,q);a;(p,r)\n" +
                                "(q,r);c;(r,r)\n" +
                                "END"
                },
        });
    }

    public IntersectionTest(String dfa1, String dfa2, String expected) {
        this.dfa1 = (DFA) Parser.parse(new Scanner(dfa1));
        this.dfa2 = (DFA) Parser.parse(new Scanner(dfa2));
        this.expected = (DFA) Parser.parse(new Scanner(expected));
    }

    @Test
    public void intersection() {
        Assert.assertEquals("\nd1: " + dfa1 +"\nd2: " + dfa2,
                expected, Intersection.intersection(dfa1,dfa2));
    }
}