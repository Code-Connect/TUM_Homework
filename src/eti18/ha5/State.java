package eti18.ha5;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class State {

    private static int id_counter = 0;
    private final int id;
    private final String name; //may be useful for thinking or debugging

    public State(){
        id = id_counter++;
        this.name = "";
    }

    public State(String name){
        id = id_counter++;
        this.name = name;
    }

    /**
     * Returns a set of states with size given by the parameter size.
     * The name of each state is the empty String.
     * @param size Gives the size of the set
     * @return A set of states with size given by the parameter size.
     */
    public static Set<State> getStateSet(int size){
        Set<State> result = new HashSet<State>();
        for (; size>0; size--){
            result.add(new State());
        }
        return result;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        if (name!=""){
            return name;
        }
        else{
            return "s"+id;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return id == state.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
