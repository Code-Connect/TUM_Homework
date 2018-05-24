package eti18.ha5;

import java.util.Scanner;

public class Intersection {

    public static DFA intersection(DFA d1, DFA d2) {
        //TODO
        return null;
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();//remove the number of lines in the beginning
        EpsilonNFA e = Parser.parse(scanner);
        DFA d1 = null;
        if(e instanceof DFA){
            d1 = (DFA) e;
        }
        else{
            System.out.println("First automaton is no DFA, aborting");
            System.exit(3);
        }

        e = Parser.parse(scanner);
        DFA d2 = null;
        if(e instanceof DFA){
            d2 = (DFA) e;
        }
        else{
            System.out.println("Second automaton is no DFA, aborting");
            System.exit(3);
        }

        System.out.println("Case #1:\n" + intersection(d1,d2));
        scanner.close();
    }


}
