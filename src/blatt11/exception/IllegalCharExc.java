package blatt11.exception;

public class IllegalCharExc extends Exception {
    final private char used;

    public IllegalCharExc(char used) {
        //TODO
        this.used = 0;
    }

    public String toString() {
        //TODO
        return this.getClass().getSimpleName();
    }

}
