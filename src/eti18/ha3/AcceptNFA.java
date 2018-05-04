package eti18.ha3;

import java.util.Scanner;

public class AcceptNFA {

    public static boolean accept(EpsilonNFA n, String w){
        //TODO
        return false;
    }
    

    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        EpsilonNFA e = Parser.parse(scanner);
        String word = scanner.nextLine();
        while(!word.equals("DONE")) {
            System.out.println(word + ": " + accept(e, word));
            word = scanner.nextLine();
        }
        scanner.close();
    }
}
