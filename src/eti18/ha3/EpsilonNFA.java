package eti18.ha3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EpsilonNFA {

    protected Set<State> states;
    protected Set<Transition> transitions;
    protected Set<Character> alphabet;
    protected State startState;
    protected Set<State> finalStates;

    public EpsilonNFA(Set<State> states, Set<Transition> transitions, Set<Character> alphabet, State startState, Set<State> finalStates) {
        this.states = states;
        this.transitions = transitions;
        this.alphabet = alphabet;
        this.startState = startState;
        this.finalStates = finalStates;
        checkValidEpsilonNFA();
    }

    /**
     * Returns all transitions starting in s
     * @param s The state where the transitions should start
     * @return All transitions starting in s
     */
    public Set<Transition> getTransitionsFromState(State s){
        Set<Transition> result = new HashSet<>();
        for (Transition t : transitions){
            if(t.getStart().equals(s)){result.add(t);}
        }
        return result;
    }

    /**
     * This gives the successor set; for DFA, there is an additional method returning a single state
     * The EPSILON thing does not hurt for NFA, because checkValidNFA ensures that there is no EPSILON transition
     * @param s
     * @param a
     * @return
     */
    public Set<State> getAllPossibleSuccessors(State s){
        Set<State> result = new HashSet<>();
        for (char a : alphabet) {
            for (Transition t : transitions){
                if(t.getStart().equals(s) && (t.getLabel()==a || t.getLabel()==Transition.EPSILON)){
                    result.add(t.getEnd());
                }
            }
        }
        return result;
    }

    /** returns all successors of a state s for a certain letter a */
    public Set<State> getSuccessors(State s, char a){
        Set<State> result = new HashSet<>();
        for (Transition t : transitions){
            if(t.getStart().equals(s) && (t.getLabel()==a)){
                result.add(t.getEnd());
            }
        }
        return result;
    }

    public Set<State> computeReachableStates(){
        return computeReachableStates(startState);
    }

    public Set<State> computeReachableStates(State s){
        Set<State> result = new HashSet<>();
        List<State> worklist = new ArrayList<State>();
        worklist.add(s);
        while(worklist.size()>0){
            State p = worklist.get(0);
            worklist.remove(0);
            if(!result.contains(p)){
                result.add(p);
                for (State q : getAllPossibleSuccessors(p)) {
                    worklist.add(q);//having the list makes me add stuff multiple times here, but it makes iterating much easier
                }
            }
        }
        return result;
    }

    public Set<State> computeReachableStates(Set<State> set){
        Set<State> result = new HashSet<State>();
        for (State s : set){
            result.addAll(computeReachableStates(s));
        }
        return result;
    }

    /**
     * Checks whether all parameters given to the constructor are valid.
     * Throw an exception describing the problem if some parameter is invalid.
     */
    private void checkValidEpsilonNFA()throws IllegalArgumentException{
        //No field is null
        if (states==null){throw new IllegalArgumentException("EpsilonNFA constructor: states cannot be null.");}
        if (transitions==null){throw new IllegalArgumentException("EpsilonNFA constructor: transitions cannot be null.");}
        if (alphabet==null){throw new IllegalArgumentException("EpsilonNFA constructor: alphabet cannot be null.");}
        if (startState==null){throw new IllegalArgumentException("EpsilonNFA constructor: startState cannot be null.");}
        if (finalStates==null){throw new IllegalArgumentException("EpsilonNFA constructor: finalStates cannot be null.");}
        if(alphabet.contains(Transition.EPSILON)){
            throw new IllegalArgumentException("The empty char is reserved and may not be part of the alphabet.");
        }
        //All special states are in the state set
        if (!states.contains(startState)){throw new IllegalArgumentException("EpsilonNFA constructor: startState must be element of states.");}
        if(!states.containsAll(finalStates)){throw new IllegalArgumentException("EpsilonNFA constructor: finalStates must be subset of states.");}
        //All states have different names //TODO: Do we want to check this?
        for (State s1 : states){
            for (State s2 : states){
                //If name=="", ignore, since name was not set;
                //Else, check that if the states are different, they also have different names
                if (!s1.getName().equals("") && !s1.equals(s2) && s1.getName().equals(s2.getName())){
                    throw new IllegalArgumentException("The states " + s1 + " and " + s2 + " must have different names or no name at all.");
                }
            }
        }
        //Transitions only use states from the state set and letters from the alphabet
        for (Transition t : transitions){
            if (!states.contains(t.getStart())){throw new IllegalArgumentException("EpsilonNFA constructor: Transition " + t + " starts in a state that is not an element of states");}
            if (!states.contains(t.getEnd())){throw new IllegalArgumentException("EpsilonNFA constructor: Transition " + t + " ends in a state that is not an element of states");}
            if (!alphabet.contains(t.getLabel()) && t.getLabel()!=Transition.EPSILON){throw new IllegalArgumentException("EpsilonNFA constructor: Transition " + t + " uses a letter that is not an element of the alphabet");}
        }
    }

    public Set<State> getStates() {
        return states;
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }

    public Set<Character> getAlphabet() {
        return alphabet;
    }

    public State getStartState() {
        return startState;
    }

    public Set<State> getFinalStates() {
        return finalStates;
    }

    @Override
    public String toString(){
        return "EpsilonNFA\n" + toStringHelper();
    }
    public String toStringHelper(){
        String alph = "Alphabet: ";
        for (char c : alphabet){
            alph += c+";";
        }
        alph=alph.substring(0,alph.length()-1);

        String sta = "States: ";
        for (State s : states){
            sta += s+";";
        }
        sta=sta.substring(0,sta.length()-1);

        String init = "Init: " + startState;

        String fin = "Final: ";
        for (State s : finalStates){
            fin += s+";";
        }
        fin=fin.substring(0,fin.length()-1);

        String trans = "Transitions:\n";
        for (Transition t : transitions){
            trans += t.toStringWithoutEpsilon()+"\n";
        }
        return alph + "\n" + sta + "\n" + init + "\n" + fin + "\n" + trans + "END";
    }

}
