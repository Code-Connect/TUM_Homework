package blatt10.exception;

public class NotEnoughNumber extends NotEnoughExc {
    public NotEnoughNumber(int should, int is) {
        //TODO
        super(-1, -1);
    }

    @Override
    public String toString() {
        //TODO
        return this.getClass().getSimpleName();
    }
}
