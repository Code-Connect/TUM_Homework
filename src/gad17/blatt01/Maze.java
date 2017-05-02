package gad17.blatt01;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.util.Random;

public class Maze extends Applet {
    private static final long serialVersionUID = 1L;

    private class Field extends JPanel {
        private static final long serialVersionUID = 1L;
        Point p;
        int x, y;

        public Field(int x, int y) {
            this.x = x;
            this.y = y;
            p = getLocation();
        }

        public void paint(Graphics g) {
            GradientPaint wallPaint = new GradientPaint(10, 50, Color.DARK_GRAY, getWidth(), 0, Color.DARK_GRAY);
            GradientPaint floorPaint = new GradientPaint(10, 50, Color.WHITE, getWidth(), 0, Color.WHITE);
            GradientPaint pathPaint = new GradientPaint(15, 0, Color.GREEN, getWidth(), 0, Color.LIGHT_GRAY);
            GradientPaint positionPaint = new GradientPaint(15, 0, Color.RED, getWidth(), 0, Color.LIGHT_GRAY);

            super.paint(g);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (spielFeld[x][y]) {
                ((Graphics2D) g).setPaint(wallPaint);
            } else {
                ((Graphics2D) g).setPaint(floorPaint);
            }
            g.fillRect(p.x, p.y, getWidth(), getHeight());

            if (sol != null && sol[x][y]) {
                ((Graphics2D) g).setPaint(pathPaint);
                g.fillOval((int) (getWidth() * .25), (int) (getHeight() * .25), (int) (getWidth() * .5), (int) (getHeight() * .5));
            }
            if (x == posx && y == posy) {
                ((Graphics2D) g).setPaint(positionPaint);
                g.fillOval((int) (getWidth() * .25), (int) (getHeight() * .25), (int) (getWidth() * .5), (int) (getHeight() * .5));
            }
        }
    }

    public static void setSolution(boolean[][] sol) {
        r.sol = sol;
    }

    private JFrame myFrame = new JFrame("Spielfeld");
    private JPanel pan = new JPanel();
    private boolean[][] spielFeld;
    private int posx, posy;

    private boolean[][] sol = null;

    public Maze() {
    }

    private Maze(int px, int py, boolean[][] feld) {
        int width = feld.length;
        int height = feld[0].length;
        spielFeld = new boolean[width][height];
        // careful: we define as x growing to the right, y growing to the bottom, while GridLayout defines height first, width second!
        pan.setLayout(new GridLayout(height, width));

        Field[][] field = new Field[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                field[x][y] = new Field(x, y);
                pan.add(field[x][y]);
                spielFeld[x][y] = feld[x][y];
            }
        }

        myFrame.getContentPane().add(pan);
        int windowWidth = 800, windowHeight = 800;
        if (width >= height) {
            windowHeight = windowHeight * height / width;
        } else {
            windowWidth = windowWidth * width / height;
        }
        myFrame.setSize(windowWidth, windowHeight);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
        update(px, py, feld);
    }

    private void update(int px, int py, boolean[][] feld) {
        if (feld.length != spielFeld.length) {
            System.err.println("Spielfeld darf sich nicht vergroessern/verkleinern...");
        }
        if (feld[0].length != spielFeld[0].length) {
            System.err.println("Spielfeld darf sich nicht vergroessern/verkleinern...");
        }

        for (int x = 0; x < spielFeld.length; x++) {
            for (int y = 0; y < spielFeld[0].length; y++) {
                spielFeld[x][y] = feld[x][y];
            }
        }
        this.posx = px;
        this.posy = py;
        pan.repaint();
    }

    private static Maze r;

    public static void draw(int x, int y, boolean[][] feld, boolean[][] solution) {
        if (r == null) {
            r = new Maze(x, y, feld);
        }

        r.sol = solution;
        r.update(x, y, feld);

        try {
            Thread.sleep(0);
        } catch (InterruptedException ie) {
        }
    }

    public static boolean[][] generateMaze() {
        return generateMaze(11, 11);
    }

    public static boolean[][] generateMaze(int width, int height) {
        Random r = new Random();
        return generateMazeInternal(width, height, r);
    }

    public static boolean[][] generateStandardMaze() {
        return generateStandardMaze(11, 11);
    }

    public static boolean[][] generateStandardMaze(int width, int height) {
        Random r = new Random();
        r.setSeed(0);
        return generateMazeInternal(width, height, r);
    }

    private static boolean[][] generateMazeInternal(int width, int height, Random rng) {
        if (width < 3) {
            width = 3;
        }
        if (height < 3) {
            height = 3;
        }
        boolean[][] maze = new boolean[width][height];

        // borders
        for (int x = 0; x < width; x++) {
            maze[x][0] = true;
            maze[x][height - 1] = true;
        }
        for (int y = 0; y < height; y++) {
            maze[0][y] = true;
            maze[width - 1][y] = true;
        }

        // create random obstacles
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (rng.nextInt(4) == 0) {
                    maze[x][y] = true;
                }
            }
        }

        // entrance and exit
        maze[1][0] = false;
        maze[1][1] = false;
        maze[width - 1][height - 2] = false;
        maze[width - 2][height - 2] = false;

        return maze;
    }


    //TODO implement main via r
    public static void main(String[] args) {
    }

    //TODO implement Algorithm; use runDraw() and printEndCouldBeReached() for Testing
    void walk(int x, int y, int direction) {
    }

    // TODO Delete after Testing
    void runDraw(int x, int y, boolean[][] feld, boolean[][] solution) {
        draw(x, y, feld, solution);
    }

    //TODO implement End Message
    void printEndCouldBeReached(boolean reachable) {
    }


    // TODO Delete after Testing
    void setSpielFeld(boolean[][] spielfeld) {
        this.spielFeld = spielfeld;
    }

    // TODO Delete after Testing
    boolean[][] runGenerateMazeInternal(int width, int height, Random r) {
        return generateMazeInternal(width, height, r);
    }
}
