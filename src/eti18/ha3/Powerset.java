package eti18.ha3;

import java.util.*;

public class Powerset {

    public static DFA powersetConstruction(NFA n) {
        //TODO
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();//remove the number of lines in the beginning
        EpsilonNFA e = Parser.parse(scanner);
        scanner.close();
        NFA n = null;
        if (e instanceof NFA) {
            n = (NFA) e;
        } else {
            System.out.println("No NFA provided, aborting");
            System.exit(3);
        }
        System.out.println("Case #1:\n" + powersetConstruction(n));
    }


}
