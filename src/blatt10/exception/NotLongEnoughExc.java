package blatt10.exception;

public class NotLongEnoughExc extends Exception {
    final private int should, is;

    public NotLongEnoughExc(int should, int is) {
        //TODO
        this.should = -1;
        this.is = -1;
    }

    @Override
    public String toString() {
        //TODO
        return this.getClass().getSimpleName();
    }

}
