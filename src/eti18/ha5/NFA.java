package eti18.ha5;

import java.util.Set;


public class NFA extends EpsilonNFA {
    public NFA(Set<State> states, Set<Transition> transitions, Set<Character> alphabet, State startState, Set<State> finalStates) {
        super(states, transitions, alphabet, startState, finalStates);
        checkValidNFA();
    }

    //No epsilon transitions, rest is done by the checkValidEpsilonNFA method
    private void checkValidNFA() throws IllegalArgumentException{
        for (State s : states){
            for (Transition t : getTransitionsFromState(s)) {
                if (t.getLabel() == Transition.EPSILON) {
                    throw new IllegalArgumentException("State " + s + " has an epsilon transition");
                }
            }
        }
    }

    @Override
    public String toString(){
        return "NFA\n" + toStringHelper();
    }
}
