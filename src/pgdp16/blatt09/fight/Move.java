package pgdp16.blatt09.fight;

/**
 * Die Klasse Move repraesentiert einen einzelnen Zug.
 * <p>
 * Es gibt zwei Konstruktoren. Einer bekommt
 * Ausgangsfeld und Zielfeld uebergeben, der andere
 * bekommt nur den eingegebenen Zug in der Form
 * <Ausgangsfeld><Zielfeld> als String uebergeben,
 * also z. B. "a7c5" fuer den Zug von "a7" nach "c5".
 */
public class Move {

    public Move(String from, String to) {
        //TODO
    }

    public Move(String move) {
        //TODO
    }

    @Override
    public String toString() {
        //TODO
        // Rueckgabe exakt in der Form <Ausgangsfeld><Zielfeld> als String,
        // also z. B. "b2b3" fuer den Zug eines Tiers von "b2" nach "b3".
        return null;
    }

    public boolean equals(Object other) {
        //TODO
        return false;
    }

}
