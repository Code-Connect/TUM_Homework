package pgdp16.blatt09.fight;
/**
 * Die Klasse IO enthaelt Hilfsprogramme zum Einlesen.
 */

import java.util.Scanner;

public class IO {

    public /*static*/ String readString(String msg) {
        System.out.print(msg);
        return (new Scanner(System.in)).nextLine();
    }

    public /*static*/ int readInt(String msg, int low, int high) {
        int result;
        do {
            System.out.print(msg);
            result = (new Scanner(System.in)).nextInt();
        } while (result < low || result > high);
        return result;
    }

    public /*static*/ int readInt() {
        return (new Scanner(System.in)).nextInt();
    }

    public /*static*/ int readInt(String msg) {
        System.out.print(msg);
        return readInt();
    }

}
