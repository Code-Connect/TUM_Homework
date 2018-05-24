package eti18.ha5;

import java.util.Scanner;

public class IsEmpty {

    public static boolean isEmpty(DFA n) {
        //TODO
        return false;
    }



    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        EpsilonNFA e = Parser.parse(scanner);
        DFA d = null;
        if(e instanceof DFA){
            d = (DFA) e;
        }
        else{
            System.out.println("No DFA provided, aborting");
            System.exit(3);
        }
        System.out.println(isEmpty(d));
        scanner.close();
    }
}
