package pgdp16.blatt09.fight;

/**
 * Enthaelt Konstanten fuer die toString-Methoden der Tierklassen.
 * <p>
 * Kurzfassung: Bei Problemen mit der Spielfeldausgabe hilft es
 * eventuell, den Wert von ANSI auf false zu setzen.
 * <p>
 * Langfassung:
 * Jeder String sollte zwei Zeichen breit sein, damit die Ausgabe
 * des Spielbretts gut lesbar ist. Die Konstante ANSI gibt an, ob
 * Unicode-Zeichen fuer Tiere mittels Surrogates als Folge von zwei
 * Zeichen (Datentyp char) dargestellt werden, siehe z. B. unter
 * https://docs.oracle.com/javase/tutorial/i18n/text/supplementaryChars.html
 * Sollte die Anzeige auf dem verwendeten System nicht funktionieren,
 * kann der Wert von ANSI auf false gesetzt werden. Dann werden die
 * Tiere als einzelne Buchstaben dargestellt, getrennt mit Leerzeichen.
 */
public class Globals {

    // Der Wert von ANSI darf geaendert werden.
    // Alles andere muss unveraendert bleiben.
    private static final boolean ANSI = false;//true; // or false


    // Ab hier darf nichts mehr veraendert werden.
    private static final String ANSI_W_LIGHT = "\u001B[34;47m";
    private static final String ANSI_W_DARK = "\u001B[34;43m";
    private static final String ANSI_M_LIGHT = "\u001B[31;47m";
    private static final String ANSI_M_DARK = "\u001B[31;43m";

    public static final String ts_male_penguin_light = ANSI ? (ANSI_M_LIGHT + new String(Character.toChars(0x1F427)) + " " + "\u001B[0m") : " P";
    public static final String ts_male_horse_light = ANSI ? (ANSI_M_LIGHT + new String(Character.toChars(0x1F434)) + " " + "\u001B[0m") : " H";
    public static final String ts_male_elephant_light = ANSI ? (ANSI_M_LIGHT + new String(Character.toChars(0x1F418)) + " " + "\u001B[0m") : " E";
    public static final String ts_male_snake_light = ANSI ? (ANSI_M_LIGHT + new String(Character.toChars(0x1F40D)) + " " + "\u001B[0m") : " S";
    public static final String ts_male_leopard_light = ANSI ? (ANSI_M_LIGHT + new String(Character.toChars(0x1F406)) + " " + "\u001B[0m") : " L";
    public static final String ts_male_rabbit_light = ANSI ? (ANSI_M_LIGHT + new String(Character.toChars(0x1F407)) + " " + "\u001B[0m") : " R";

    public static final String ts_male_penguin_dark = ANSI ? (ANSI_M_DARK + new String(Character.toChars(0x1F427)) + " " + "\u001B[0m") : " P";
    public static final String ts_male_horse_dark = ANSI ? (ANSI_M_DARK + new String(Character.toChars(0x1F434)) + " " + "\u001B[0m") : " H";
    public static final String ts_male_elephant_dark = ANSI ? (ANSI_M_DARK + new String(Character.toChars(0x1F418)) + " " + "\u001B[0m") : " E";
    public static final String ts_male_snake_dark = ANSI ? (ANSI_M_DARK + new String(Character.toChars(0x1F40D)) + " " + "\u001B[0m") : " S";
    public static final String ts_male_leopard_dark = ANSI ? (ANSI_M_DARK + new String(Character.toChars(0x1F406)) + " " + "\u001B[0m") : " L";
    public static final String ts_male_rabbit_dark = ANSI ? (ANSI_M_DARK + new String(Character.toChars(0x1F407)) + " " + "\u001B[0m") : " R";


    public static final String ts_female_penguin_light = ANSI ? (ANSI_W_LIGHT + new String(Character.toChars(0x1F427)) + " " + "\u001B[0m") : " p";
    public static final String ts_female_horse_light = ANSI ? (ANSI_W_LIGHT + new String(Character.toChars(0x1F434)) + " " + "\u001B[0m") : " h";
    public static final String ts_female_elephant_light = ANSI ? (ANSI_W_LIGHT + new String(Character.toChars(0x1F418)) + " " + "\u001B[0m") : " e";
    public static final String ts_female_snake_light = ANSI ? (ANSI_W_LIGHT + new String(Character.toChars(0x1F40D)) + " " + "\u001B[0m") : " s";
    public static final String ts_female_leopard_light = ANSI ? (ANSI_W_LIGHT + new String(Character.toChars(0x1F406)) + " " + "\u001B[0m") : " l";
    public static final String ts_female_rabbit_light = ANSI ? (ANSI_W_LIGHT + new String(Character.toChars(0x1F407)) + " " + "\u001B[0m") : " r";

    public static final String ts_female_penguin_dark = ANSI ? (ANSI_W_DARK + new String(Character.toChars(0x1F427)) + " " + "\u001B[0m") : " p";
    public static final String ts_female_horse_dark = ANSI ? (ANSI_W_DARK + new String(Character.toChars(0x1F434)) + " " + "\u001B[0m") : " h";
    public static final String ts_female_elephant_dark = ANSI ? (ANSI_W_DARK + new String(Character.toChars(0x1F418)) + " " + "\u001B[0m") : " e";
    public static final String ts_female_snake_dark = ANSI ? (ANSI_W_DARK + new String(Character.toChars(0x1F40D)) + " " + "\u001B[0m") : " s";
    public static final String ts_female_leopard_dark = ANSI ? (ANSI_W_DARK + new String(Character.toChars(0x1F406)) + " " + "\u001B[0m") : " l";
    public static final String ts_female_rabbit_dark = ANSI ? (ANSI_W_DARK + new String(Character.toChars(0x1F407)) + " " + "\u001B[0m") : " r";


    public static final String ts_empty_square_light = ANSI ? "\u001B[47m" + "  " + "\u001B[0m" : "  ";
    public static final String ts_empty_square_dark = ANSI ? "\u001B[43m" + "  " + "\u001B[0m" : "  ";

    public static boolean darkSquare(String square) {
        if (null == square) return false;
        return (square.charAt(0) + square.charAt(1)) % 2 == 0;
    }

}
