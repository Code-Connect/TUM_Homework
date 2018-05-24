package eti18.ha5;

import java.util.Objects;

public class Transition {

    private final State start;
    private final State end;
    private final char label;
    public static final char EPSILON = Character.MIN_VALUE;

    public Transition(State start, State end, char label) {
        this.start = start;
        this.end = end;
        this.label = label;
    }

    public State getStart() {
        return start;
    }

    public State getEnd() {
        return end;
    }

    public char getLabel() {
        return label;
    }

    @Override
    public String toString(){
        String letter = label==EPSILON ? "\u03B5" : label+"";
        return start + ";" + letter + ";" + end;
    }

    public String toStringWithoutEpsilon(){
        return start + ";" + label + ";" + end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transition that = (Transition) o;
        return Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {

        return Objects.hash(start, end, label);
    }
}
