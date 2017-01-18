package blatt11.exception;

public class NotEnoughSpecial extends NotEnoughExc {
    public NotEnoughSpecial(int should, int is) {
        //TODO
        super(-1, -1);
    }


    public String toString() {
        //TODO
        return this.getClass().getSimpleName();
    }
}
