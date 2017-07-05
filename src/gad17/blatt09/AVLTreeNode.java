package gad17.blatt09;

/**
 * Diese Klasse implementiert einen Knoten in einem AVL-Baum.
 */
public class AVLTreeNode {
    /**
     * Diese Variable enthält den Schlüssel, den der Knoten speichert.
     */
    private int key;

    /**
     * Diese Variable speichert die Balancierung des Knotens - wie in der
     * Vorlesung erklärt - ab. Ein Wert von -1 bedeutet, dass der linke Teilbaum
     * um eins tiefer ist als der rechte Teilbaum. Ein Wert von 0 bedeutet, dass
     * die beiden Teilbäume gleich hoch sind. Ein Wert von 1 bedeutet, dass der
     * rechte Teilbaum tiefer ist.
     */
    private int balance = 0;

    private AVLTreeNode left = null;
    private AVLTreeNode right = null;

    public AVLTreeNode(int key) {
        // TODO...
    }

    /**
     * Diese Methode ermittelt die Höhe des Teilbaums unter diesem Knoten.
     *
     * @return die ermittelte Höhe
     */
    public int height() {
        // TODO...
        return -3;
    }

    public boolean validAVL() {
        // TODO...
        return false;
    }

    /**
     * Diese Methode sucht einen Schlüssel im Baum.
     *
     * @param key der zu suchende Schlüssel
     * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
     */
    public boolean find(int key) {
        // TODO...
        return false;
    }

    public AVLTreeNode insert(int key) {
        // TODO...
        return null;
    }

    /**
     * Diese Methode wandelt den Baum in das Graphviz-Format um.
     *
     * @param sb der StringBuilder für die Ausgabe
     */
    public void dot(StringBuilder sb) {
        dotNode(sb, 0);
    }

    private int dotNode(StringBuilder sb, int idx) {
        sb.append(String.format("\t%d [label=\"%d, b=%d\"];\n", idx, key, balance));
        int next = idx + 1;
        if (left != null)
            next = left.dotLink(sb, idx, next, "l");
        if (right != null)
            next = right.dotLink(sb, idx, next, "r");
        return next;
    }

    private int dotLink(StringBuilder sb, int idx, int next, String label) {
        sb.append(String.format("\t%d -> %d [label=\"%s\"];\n", idx, next, label));
        return dotNode(sb, next);
    }

}
