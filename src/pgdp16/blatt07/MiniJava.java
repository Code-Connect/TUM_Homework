package pgdp16.blatt07;

import javax.swing.*;

public class MiniJava {
    public /*static*/ String readString(String text) {
        JFrame frame = new JFrame();
        String s = JOptionPane.showInputDialog(frame, text);
        frame.dispose();

        if (s == null)
            System.exit(0);
        return s;
    }

    public /*static*/ String readString() {
        return readString("Eingabe:");
    }

    public /*static*/ int readInt(String text) {
        JFrame frame = new JFrame();
        String s = JOptionPane.showInputDialog(frame, text);
        frame.dispose();

        int x = 0;
        if (s == null)
            System.exit(0);
        try {
            x = Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            x = readInt(text);
        }
        return x;
    }

    public /*static*/ int readInt() {
        return readInt("Geben Sie eine ganze Zahl ein:");
    }

    public /*static*/ int read(String text) {
        return readInt(text);
    }

    public /*static*/ int read() {
        return readInt();
    }

    public /*static*/ double readDouble(String text) {
        JFrame frame = new JFrame();
        String s = JOptionPane.showInputDialog(frame, text);
        frame.dispose();

        double x = 0;
        if (s == null)
            System.exit(0);
        try {
            x = Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            x = readDouble(text);
        }
        return x;
    }

    public /*static*/ double readDouble() {
        return readDouble("Geben Sie eine Zahl ein:");
    }

    public /*static*/ void write(String output) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, output, "Ausgabe", JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
    }

    public /*static*/ void write(int output) {
        write("" + output);
    }

    public /*static*/ void write(double output) {
        write("" + output);
    }

    public /*static*/ int drawCard(int minval, int maxval){
        return minval + (new java.util.Random()).nextInt(maxval-minval+1);
    }
    public /*static*/ int drawCard(){
        return drawCard(2,11);
    }

    public /*static*/ int dice(){
        return drawCard(1,6);
    }
}
