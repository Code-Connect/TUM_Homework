package pgdp16.blatt07;

public class DameSpiel extends MiniJava {

    /*private*/ int nrRows, nrColumns; // Board dimensions
    /*private*/ boolean[][] board;     // true = queen, false = empty
    /*private*/ boolean whiteToMove;   // Whose turn it is
    /*private*/ String white, black;   // Players' names

    //TODO Mehr Attribute?

    //TODO Weitere Methoden?


    /**
     * Der Konstruktor registriert Spielernamen fuer Weiss und Schwarz.
     *
     * @param white Name des als 'Weiss' bezeichneten Spielers
     * @param black Name des als 'Schwarz' bezeichneten Spielers
     */
    public DameSpiel(String white, String black){
        //TODO
    }


    /**
     * Gibt das Spielbrett aus.
     */
    /*private*/ void printBoard(){
        for (int j = board[0].length - 1; j >= 0; j--) {
            System.out.print("\n " + (1 + j));
            for (int i = 0; i < board.length; i++) {
                System.out.print(board[i][j] ? " X" : " -");
            }
        }
        System.out.print("\n  ");
        for (int i = 1; i <= board.length; i++) {
            System.out.print(" " + i);
        }
        System.out.println("\n" + (whiteToMove ? white : black) + " ist am Zug.");
    }


    /**
     * Initialisiert das Spielbrett ueberall mit false.
     * Dazu wird (ggf. neuer) Speicher allokiert.
     */
    /*private*/ void initBoard(){
        //TODO
    }


    /**
     * Ermittelt die Groesse des Spielbretts gemaess den Spielregeln.
     * Das Ergebnis der Abfrage wird in den Attributen nrRows und nrColumns abgelegt.
     */
    /*private*/ void determineBoardSize(){
        //TODO
    }


    /**
     * Ermittelt, wer anfaengt zu ziehen.
     * Das Ergebnis der Abfrage wird im Attribut whiteToMove abgelegt.
     */
    /*private*/ void determineFirstPlayer(){
        //TODO
    }


    /**
     * Fuehrt den Zug aus.
     *
     * @param move der auszufuehrende Zug!
     */
    /*private*/ void applyMove(int move){
        //TODO
    }


    /**
     * Startet die Hauptschleife des Spiels
     * mit der Abfrage nach Zuegen.
     * Die Schleife wird durch Eingabe von -1 beendet.
     */
    /*private*/ void mainLoop(){
        //TODO
    }


    /**
     * Informiert die Benutzerin ueber den Ausgang des Spiels.
     * Speziell: Wer hat gewonnen (Weiss oder Schwarz)?
     */
    /*private*/ void reportWinner(){
        //TODO
    }


    /**
     * Startet das Spiel.
     */
    public void startGame(){
        determineBoardSize();
        initBoard();
        determineFirstPlayer();
        printBoard();
        mainLoop();
        reportWinner();
    }


    public static void main(String[] args) {
        DameSpiel ds = new DameSpiel("Weiß", "Schwarz");
        ds.startGame();
    }

}
