package eti18.ha5;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Parser {
    /**
     * eNFA/NFA/DFA
     * Alphabet: a;b;c
     * States: name;name2;name3
     * Init: name
     * Final: name;name2
     * Transitions:
     * q;a;p
     * q;;r
     * END
     */


    static EpsilonNFA parse(Scanner scanner) {
        //First line
        String first = scanner.nextLine();
        if (!first.equals("NFA")&&!first.equals("DFA")&&!first.equals("EpsilonNFA")) {
            throw new IllegalArgumentException("Parsed automaton does not start with NFA, DFA or EpsilonNFA.");
        }

        //Second line; Alphabet
        String second = scanner.nextLine();
        if (!second.startsWith("Alphabet: ")) {
            throw new IllegalArgumentException("Parsed automaton does not declare alphabet first.");
        }
        Set<Character> alphabet = new HashSet<>();
        second = second.substring("Alphabet: ".length());
        for (String letter : second.split(";")) {
            if (letter.length() == 1) {
                alphabet.add(letter.charAt(0));
            } else {
                throw new IllegalArgumentException(
                        "Letters have to be input as a semicolon separated list without spaces. Letters may only be chars.");
            }
        }

        //Third line; States
        String third = scanner.nextLine();
        if (!third.startsWith("States: ")) {
            throw new IllegalArgumentException("Parsed automaton does not declare states second.\"");
        }
        Set<State> states = new HashSet<>();
        third = third.substring("States: ".length());
        for (String stateStr : third.split(";")) {
            states.add(new State(stateStr));
        }

        //Fourth line; initialstate
        String fourth = scanner.nextLine();
        if (!fourth.startsWith("Init: ")) {
            throw new IllegalArgumentException("Parsed automaton does not declare initial state third.");
        }
        fourth = fourth.substring("Init: ".length());

        //fifth line; final states
        String fifth = scanner.nextLine();
        if (!fifth.startsWith("Final: ")) {
            throw new IllegalArgumentException("Parsed automaton does not declare final states fourth.");
        }
        fifth=fifth.substring("Final: ".length());
        Set<State> finalStates = new HashSet<>();
        String[] finals=fifth.split(";");

        //Sixth line; transitions
        String sixth = scanner.nextLine();
        if (!sixth.equals("Transitions:")) {
            throw new IllegalArgumentException("Parsed automaton does not declare transitions fifth.");
        }
        Set<Transition> transitions = new HashSet<>();
        String transition;
        while (!(transition = scanner.nextLine()).equals("END")) {
            String[] split = transition.split(";");
            String left = split[0];
            String right = split[2];
            String letter = split[1];
            char label; //checking label in alphabet is done by checkValidEpsNFA
            if(letter.length()>1){
                throw new IllegalArgumentException("Transition label may only be a char, but it is " + letter);
            }
            else if(letter.length()==0){
                label = Transition.EPSILON;
            }
            else {
                label = letter.charAt(0);
            }
            State l=null;
            State r=null;
            //the following loop brought to you by: Too lazy to change bad modelling decisions
            for (State s : states){
                if(s.getName().equals(left)){
                    l=s;
                }
                if(s.getName().equals(right)){
                    r=s;
                }
                if(r!=null && l!=null){
                    break;
                }
            }
            if(r==null && l==null){
                throw new IllegalArgumentException("The states for a transition are not in the state set ("+right+" or " + left +")");
            }

            transitions.add(new Transition(l,r,label));
        }


        State init = null;

        for (State s : states){
            if(s.getName().equals(fourth)){
                init=s;
            }
            for (String fStr:finals) {
                if (s.getName().equals(fStr)){
                    finalStates.add(s);
                }
            }
        }
        if(init==null){
            throw new IllegalArgumentException("Initial state is not in state set");
        }
        if(finals.length!=finalStates.size() && !finals[0].equals("")){
            throw new IllegalArgumentException("Not all final states are in the state set");
        }

        switch(first) {
            case "DFA":
                return new DFA(states, transitions, alphabet, init, finalStates);
            case "NFA":
                return new NFA(states, transitions, alphabet, init, finalStates);
            default:
                return new EpsilonNFA(states, transitions, alphabet, init, finalStates);
        }
    }

}
