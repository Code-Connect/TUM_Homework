package blatt11.exception;

public class NotEnoughExc extends Exception {
    final int should, is;

    public NotEnoughExc(int should, int is) {
        //TODO
        this.is = -1;
        this.should = -1;
    }

    @Override
    public String toString() {
        //TODO
        return this.getClass().getSimpleName();
    }
}
