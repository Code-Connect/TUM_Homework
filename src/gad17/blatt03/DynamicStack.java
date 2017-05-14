package gad17.blatt03;

/**
 * Die Klasse DynamicStack soll einen Stapel auf
 * Basis der Klasse {@link DynamicArray} implementieren.
 */
public class DynamicStack {
    private DynamicArray dynArr;

    /**
     * Dieses Feld speichert die Anzahl der Elemente auf dem Stapel.
     */
    private int length;

    public int getLength() {
        return length;
    }

    public DynamicStack(int growthFactor, int maxOverhead) {
        dynArr = new DynamicArray(growthFactor, maxOverhead);
        length = 0;
    }

    /**
     * Diese Methode legt ein Element auf den Stapel.
     *
     * @param value das Element, das auf den Stapel gelegt werden soll
     */
    public void pushBack(int value) {
    /*
     * Todo
     */
    }

    /**
     * Diese Methode nimmt ein Element vom Stapel.
     *
     * @return das entfernte Element
     */
    public int popBack() {
    /*
     * Todo
     */
        return -2;
    }
}
