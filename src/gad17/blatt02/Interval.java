package gad17.blatt02;

/**
 * Objekte der Klasse Interval stellen einen Bereich von Zahlen dar.
 */
abstract class Interval {
    /**
     * Abfragen der unteren, inklusiven Intervallgrenze
     *
     * @return die untere, inklusive Grenze
     */
    public abstract int getFrom();

    /**
     * Abfragen der oberen, inklusiven Intervallgrenze
     *
     * @return die obere, inklusive Grenze
     */
    public abstract int getTo();

    /**
     * Diese Methode erzeugt ein Intervall aus Array-Indices. Ist die
     * untere Intervallberenzung größer als die obere Intervallbegrenzung
     * oder ist einer der Begrenzungen negativ, so wird ein leeres Intervall
     * zurückgegeben.
     *
     * @param from die untere, inklusive Intervallbegrenzung
     * @param to   die obere, inklusive Intervallbegrenzung
     * @return ein Intervallobjekt, das den Indexbereich repräsentiert
     */
    static Interval fromArrayIndices(int from, int to) {
        if (to < from)
            return new EmptyInterval();
        else if (to < 0 || from < 0)
            return new EmptyInterval();
        else
            return new NonEmptyInterval(from, to);
    }
}

/**
 * Objekte der Klasse NonEmptyInterval repräsentieren ein nicht-leeres
 * Intervall.
 */
class NonEmptyInterval extends Interval {
    private int from;
    private int to;

    @Override
    public int getFrom() {
        return from;
    }

    @Override
    public int getTo() {
        return to;
    }

    public NonEmptyInterval(int from, int to) {
        if (to >= from) {
            this.from = from;
            this.to = to;
        } else
            throw new RuntimeException("Invalid interval boundary");
    }

    @Override
    public String toString() {
        return "[" + from + ";" + to + "]";
    }
}

/**
 * Objekte der Klasse EmptyInterval repräsentieren ein leeres Intervall.
 */
class EmptyInterval extends Interval {
    @Override
    public int getFrom() {
        throw new RuntimeException("No lower boundary in empty interval");
    }

    @Override
    public int getTo() {
        throw new RuntimeException("No upper boundary in empty interval");
    }

    public EmptyInterval() {
    }

    @Override
    public String toString() {
        return "[]";
    }
}