package blatt10.set;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Set<T> implements Iterable<T> {
    final private List<T> list;

    public Set() {
        list = null;
    }

    public Set<T> add(T e) {
        return null;
    }

    public Set<T> remove(T e) {
        return null;
    }

    public boolean contains(T e) {
        return false;
    }

    public int size() {
        return -1;
    }

    public boolean equals(Object o) {
        return false;
    }

    public String toString() {
        return super.toString();
    }

    public Iterator<T> iterator() {
        return null;
    }

    public void forEach(Consumer<? super T> action) {

    }

    public Spliterator<T> spliterator() {
        return null;
    }
}
