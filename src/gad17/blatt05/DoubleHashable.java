package gad17.blatt05;

public interface DoubleHashable<K> {
    /**
     * Diese Methode implementiert h(key).
     *
     * @param key der Schlüssel, der gehasht werden soll
     * @return der generierte Hash
     */
    long hash(K key);

    /**
     * Diese Methode implementiert h'(key).
     *
     * @param key der Schlüssel, der gehasht werden soll
     * @return der generierte Hash
     */
    long hashTick(K key);
}
