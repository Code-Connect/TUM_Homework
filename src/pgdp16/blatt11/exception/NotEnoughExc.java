package pgdp16.blatt11.exception;

public class NotEnoughExc extends Exception {
    final int should, is;

    public NotEnoughExc(int should, int is) {
        //TODO
        this.is = -1;
        this.should = -1;
    }
}
