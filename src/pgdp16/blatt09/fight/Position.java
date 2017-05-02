package pgdp16.blatt09.fight;

/**
 * Die Klasse Position repraesentiert eine Spielsituation.
 */
public class Position {

    /**
     * Die Tiere werden intern in einem Array gespeichert.
     * nrAnimals gibt an, wie viele Tiere auf dem Brett sind.
     * Diese sind in myAnimals an den Positionen 0 bis nrAnimals-1 enthalten.
     * <p>
     * Es ist empfohlen, aber nicht vorgeschrieben, diese Attribute zu verwenden.
     * <p>
     * Falls die beiden Attribute NICHT verwendet werden, muss die Ausgabe
     * der Spielposition unten entsprechend auf die verwendete Datenstruktur
     * angepasst werden. Die toString-Methode darf dabei nicht veraendert werden,
     * muss jedoch die selbe Rueckgabe liefern. D.h. es ist dann notwendig,
     * die Hilfsmethode boardRepresentation auf die verwendete Datenstruktur anzupassen.
     */
    /*private*/ Animal[] myAnimals;
    /*private*/ int nrAnimals;

    /**
     * Spieler, der als naechstes ziehen darf ('M' oder 'W').
     * Wird jedes Mal aktualisiert, wenn eine Seite ihre Zuege ausfuehrt.
     */
    /*private*/ char next = 'W';


    /**
     * Stellt die Anfangsposition des Spiels her.
     * Der Parameter gibt an, welche Seite beginnt ('M' oder 'W').
     */
    public void reset(char movesNext) {
        //TODO
    }


    /**
     * Fuehrt die uebergebenen Zuege fuer einen der Spieler aus.
     * Die Reihenfolge soll keinen Unterschied machen.
     * Diese Methode geht davon aus, dass dies bereits ueberprueft wurde.
     * <p>
     * Der Zustand des Spiels wird entsprechend angepasst, d. h. ein Spiel
     * kann von der Anfangsposition aus allein mittels Aufrufen dieser Methode
     * gespielt werden. Insbesondere wechselt durch den Aufruf das Zugrecht,
     * da M und W abwechselnd ziehen.
     *
     * @param move Array mit den Zuegen, die ausgefuehrt werden sollen.
     */
    public void applyMoves(Move[] move) {
        //TODO
    }


    /**
     * Ermittelt, ob/wer gewonnen hat.
     *
     * @return 'W' falls W gewonnen hat,
     * 'M' falls M gewonnen hat,
     * 'N' falls das Spiel unentschieden zu Ende ist,
     * 'X' falls das Spiel noch nicht zu Ende ist.
     */
    public char theWinner() {
        //TODO
        return 0;
    }


    // Ausgabe der Spielposition

    private static final int[] I = {8, 7, 6, 5, 4, 3, 2, 1};
    private static final String[] J = {"a", "b", "c", "d", "e", "f", "g", "h"};

    private static int toIndex(String s) {
        return (s.charAt(0) - 'a');
    }

    // Erzeugt eine 2D-Repraesentation der Spielposition.
    // Darf ggf. auf neue Datenstruktur angepasst werden (s.o.)
    // Die Rueckgabe ist ein zweidimensionales Array, welches
    // jedem Feld das darauf befindliche Tier (oder null) zuordnet.
    // Dabei laeuft der erste Index von der 'a'-Linie zur 'h'-Linie,
    // der zweite von der 1. zur 8. Reihe. D.h. wenn z.B. bei a[3][7]
    // ein Tier ist, ist das zugehörige Feld "d8" (vierter Buchstabe,
    // achte Zahl).
    public Animal[][] boardRepresentation() {
        Animal[][] a = new Animal[8][8];
        for (int i : I) {
            for (String j : J) {
                for (int k = 0; k < myAnimals.length; k++) {
                    if (null == myAnimals[k]) {
                        break;
                    }
                    if (myAnimals[k].square.equals(j + i)) {
                        a[toIndex(j)][i - 1] = myAnimals[k];
                    }
                }
            }
        }
        return a;
    }


    @Override
    public String toString() {
        String str = "   a b c d e f g h\n";
        Animal[][] ani = boardRepresentation();
        for (int i : I) {
            str += (i + " ");
            for (String j : J) {
                if (null == ani[toIndex(j)][i - 1]) {
                    str += (i + toIndex(j)) % 2 == 1 ? Globals.ts_empty_square_dark : Globals.ts_empty_square_light;
                } else {
                    str += ani[toIndex(j)][i - 1].toString();
                }
            }
            str += " " + i + "\n";
        }
        str += "  a b c d e f g h\nIt is " + next + "'s turn.\n";
        return str;
    }

    public String getMovesString() {//TODO Please Rename; by CC
        return null;
    }

    public String getPredatorWithoutFood() {//TODO Please Rename; by CC
        return null;
    }

    public void sunset() {//TODO Please Rename; by CC
    }

    public void setMyAnimals(Animal... myAnimals) {
        this.myAnimals = myAnimals;
    }
}
