package eti18.ha3;

import java.util.Scanner;

public class AcceptDFA {

    public static boolean accept(DFA d, String w) {
        //TODO
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EpsilonNFA e = Parser.parse(scanner);
        DFA d = null;
        if (e instanceof DFA) {
            d = (DFA) e;
        } else {
            System.out.println("No DFA provided, aborting");
            System.exit(3);
        }
        String word = scanner.nextLine();
        while (!word.equals("DONE")) {
            System.out.println(word + ": " + accept(d, word));
            word = scanner.nextLine();
        }
        scanner.close();
    }

}