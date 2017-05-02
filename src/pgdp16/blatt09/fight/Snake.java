package pgdp16.blatt09.fight;

public class Snake extends Predator {

    // Eine Schlange kann 9 Tage bzw. Spielrunden ohne Essen auskommen.
    // Die Deklaration darf entfernt (und der Wert z. B. direkt im Code
    // verwendet) werden.
    private static int withoutFood = 9;


    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     */
    public Snake(boolean female) {
        //TODO
        super(false);
    }


    @Override
    public String toString() {
        return this.female
                ? (Globals.darkSquare(this.square) ? Globals.ts_female_snake_dark : Globals.ts_female_snake_light)
                : (Globals.darkSquare(this.square) ? Globals.ts_male_snake_dark : Globals.ts_male_snake_light);
    }

}
