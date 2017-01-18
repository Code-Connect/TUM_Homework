package blatt11.exception;

public class NotEnoughNumber extends NotEnoughExc {
    public NotEnoughNumber(int should, int is) {
        //TODO
        super(-1, -1);
    }

    public String toString() {
        //TODO
        return this.getClass().getSimpleName();
    }
}
