package blatt10.exception;

public class IllegalCharExc extends Exception {
    final private char used;

    public IllegalCharExc(char used) {
        //TODO
        this.used = 0;
    }

    @Override
    public String toString() {
        //TODO
        return this.getClass().getSimpleName();
    }

}
