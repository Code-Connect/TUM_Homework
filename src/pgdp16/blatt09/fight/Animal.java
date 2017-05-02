package pgdp16.blatt09.fight;

/**
 * Oberklasse fuer Tiere.
 */
public class Animal {

    // Attribute fuer den allen Tieren gemeinen Teil des Tierzustands
    public boolean female; // Weibchen?
    public boolean alive;  // Lebendig?
    public String square;  // Auf welchem Feld? (genau zwei Zeichen, z. B. "e4")
    public Position position; // Auf welchem Spielbrett?


    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     */
    public Animal(boolean female) {
        //TODO
    }


    /**
     * Ermittelt die moeglichen Zuege gemaess den Spielregeln,
     * die das Tier von seinem Feld aus in der aktuellen Position
     * ausfuehren kann.
     * <p>
     * Muss von jeder einzelnen Tierklasse ueberschrieben werden.
     */
    public Move[] possibleMoves() {
        return null;
    }


    /**
     * Wird aufgerufen nach jeder Spielrunde
     * (quasi am Ende vom Tag - jede Spielrunde zaehlt als ein Tag).
     * <p>
     * Muss in jeder einzelnen Tierklasse ueberschrieben sein!
     */
    public void sunset() {
        // Methode (und Klasse Animal) sollten eigentlich als abstract deklariert sein.
        // Kommt spaeter in der Vorlesung noch dran...
        // Zum Verstaendnis reicht es, dass diese Methode ueberschrieben werden muss.
        // (Die folgende Zeile wird dann auch nie ausgefuehrt.)
        throw new RuntimeException("Method sunset should have been overridden");
    }

}
