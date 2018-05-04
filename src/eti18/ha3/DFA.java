package eti18.ha3;

import java.util.HashSet;
import java.util.Set;

public class DFA extends NFA {

    public DFA(Set<State> states, Set<Transition> transitions, Set<Character> alphabet, State startState, Set<State> finalStates) {
        super(states, transitions, alphabet, startState, finalStates);
        checkValidDFA();
    }

    /**
     * Returns the successor of a state-letter pair.
     * It surely exists, because the transition relation was checked to be total in checkValidDFA.
     * There surely is only one, because that was also checked in checkValidDFA
     * If the given char is not in the alphabet, returns null
     * @param s The state from which to start.
     * @param a The letter to read
     * @return The successor of the transition starting in s reading a.
     */
    public State getSuccessor(State s, char a){
        for (Transition t : transitions){
            if(t.getStart().equals(s) && t.getLabel()==a){
                return t.getEnd();
            }
        }
        return null;
    }


    /**
     * Checks whether the given parameters indeed describe a DFA.
     * Throws an exception describing the problem if some parameter is invalid.
     * Checks firstly that each state has a transition for each label at most once, after that at least once.
     * We do not need to check that the transition labels are a subset of the alphabet, that is done in checkValidEpsilonNFA
     */
    private void checkValidDFA() throws IllegalArgumentException{
        for (State s : states){
            Set<Character> transLabels = new HashSet<>();
            for (Transition t : getTransitionsFromState(s)){
                if(!transLabels.add(t.getLabel())){
                    throw new IllegalArgumentException("State " + s + " has more than one transition for label " + t.getLabel());
                }
            }
            if(!transLabels.containsAll(alphabet)){
                Set<Character> missingSet = new HashSet<>(alphabet);
                missingSet.removeAll(transLabels);
                throw new IllegalArgumentException("State " + s + " is missing a transition for label(s): " + missingSet);
            }
        }
    }

    @Override
    public String toString(){
        return "DFA\n" + toStringHelper();
    }

}