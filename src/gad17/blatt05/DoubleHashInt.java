package gad17.blatt05;

/**
 * Die Klasse {@link DoubleHashInt} kann dazu verwendet werden,
 * Integer zu hashen.
 */
public class DoubleHashInt implements DoubleHashable<Integer> {
    /*
     * Todo
     */
    private int size, a, b; //TODO Rename after Testing

    /**
     * Dieser Konstruktor initialisiert ein {@link DoubleHashInt}
     * Objekt für einen gegebenen Maximalwert (size - 1) der gehashten
     * Werte.
     *
     * @param size die Größe der Hashtabelle
     */
    public DoubleHashInt(int size) {
    /*
     * Todo
     */
    }

    /**
     * Diese Methode berechnet h(key) für einen Integer.
     *
     * @param key der Schlüssel, der gehasht werden soll
     * @return der Hashwert des Schlüssels
     */
    @Override
    public long hash(Integer key) {
    /*
     * Todo
     */
        return -2;
    }

    /**
     * Diese Methode berechnet h'(key) für einen Integer.
     *
     * @param key der Schlüssel, der gehasht werden soll
     * @return der Hashwert des Schlüssels
     */
    @Override
    public long hashTick(Integer key) {
    /*
     * Todo
     */
        return -2;
    }

}
