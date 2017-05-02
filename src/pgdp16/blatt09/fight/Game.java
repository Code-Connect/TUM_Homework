package pgdp16.blatt09.fight;

/**
 * Die Klasse Game fuehrt die Benutzerinteraktion durch.
 */

public class Game {
    public IO io; //TODO Please Rename; by CC
    /*private*/ Position pos;


    /**
     * Startet ein neues Spiel.
     * Der Benutzer wird ueber das Spielgeschehen informiert.
     * <p>
     * Dazu gehoert auch die Information, wie lange die
     * einzelnen Raubtiere noch ohne Essen auskommen koennen.
     * Diese Information soll auf Anfrage oder immer angezeigt werden.
     * <p>
     * Es soll ausserdem eine Moeglichkeit geben, sich alle Zuege
     * anzeigen zu lassen, die in der Spielsituation moeglich sind.
     * <p>
     * Bei fehlerhaften Eingaben wird die Eingabe natuerlich wiederholt.
     * <p>
     * Der Parameter spezifiziert, wer das Spiel beginnen darf.
     */
    public void startGame(boolean ladiesFirst) {
        pos = new Position();
        pos.reset(ladiesFirst ? 'W' : 'M');
        //TODO
        loop();
    }

    public void loop() { //TODO Please Rename; by CC

    }

    public void printBoard() {//TODO Please Rename; by CC

    }

    public void printPredatorWithoutFood() {//TODO Please Rename; by CC
    }

    public void printMoves() {//TODO Please Rename; by CC

    }

}
