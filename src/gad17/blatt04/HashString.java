package gad17.blatt04;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Die Klasse {@link HashString} kann dazu verwendet werden,
 * Strings zu hashen.
 */
public class HashString {
    /*
     * Todo
     */
    public int size; //TODO rename after Testing!
    public ArrayList<Integer> vectors; //TODO rename after Testing!

    /**
     * Dieser Konstruktor initialisiert ein {@link HashString}
     * Objekt für einen gegebenen Maximalwert (size - 1) der gehashten
     * Werte.
     *
     * @param size die Größe der Hashtabelle
     */
    public HashString(int size) {
    /*
     * Todo
     */
    }

    private boolean isInvalidSize(int size) {
        return size <= 1 || !new BigInteger("" + size).isProbablePrime(666);
    }

    /**
     * Diese Methode berechnet den Hashwert für einen String.
     *
     * @param key der Schlüssel, der gehasht werden sollen
     * @return der Hashwert des Schlüssels
     */
    public int hash(String key) {
    /*
     * Todo
     */
        return -1;
    }

}
