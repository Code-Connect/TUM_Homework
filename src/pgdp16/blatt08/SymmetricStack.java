package pgdp16.blatt08;

public class SymmetricStack {

    private int[] data;
    private int first;
    private int last;

    public SymmetricStack() {
        // TODO
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public int getNumberOfElements() {
        // TODO
        return -1;
    }

    public void increase() {
        // TODO
    }

    public void decrease() {
        // TODO
    }

    public boolean isEmpty() {
        // TODO
        return false;
    }

    public boolean isFull() {
        // TODO
        return false;
    }

    public void prepend(int x) {
        // TODO
    }

    public void append(int x) {
        // TODO
    }

    public void removeFirst() {
        // TODO
    }

    public void removeLast() {
        // TODO
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < data.length; i++) {
            if (first <= last && (i < first || i > last))
                out += "* ";
            if (first <= last && i > first && i < last)
                out += " " + data[i];
            if (first > last && i > last && i < first)
                out += "* ";
            if (first > last && (i > first || i < last))
                out += " " + data[i];
            if (i == first)
                out = out + "(" + data[i];
            if (i == last)
                out += (first == last ? "" : " " + data[i]) + ")";
        }
        return out;
    }
}
