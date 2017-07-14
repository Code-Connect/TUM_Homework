package gad17.blatt10;

import java.util.ArrayList;

/**
 * Diese Klasse implementiert einen (a,b)-Baum.
 */
public class ABTree {
    /**
     * Diese Variable speichert die untere Schranke des Knotengrades.
     */
    private int a;

    /**
     * Diese Variable speichert die obere Schranke des Knotengrades.
     */
    private int b;

    /**
     * Diese Klasse repräsentiert einen Knoten des Baumes. Es kann sich
     * dabei um einen inneren Knoten oder ein Blatt handeln.
     */
    public abstract class ABTreeNode {
        /**
         * Diese Methode fügt einen Schlüssel in den Baum ein.
         *
         * @param key der Schlüssel, der eingefügt wird
         */
        public abstract void insert(int key);

        /**
         * Diese Methode ermittelt, ob aus dem entsprechenden Knoten gestohlen
         * werden kann oder nicht.
         *
         * @return 'true' falls gestohlen werden kann, 'false' sonst
         */
        public abstract boolean canSteal();

        /**
         * Diese Methode sucht den Schlüssel 'key' im Teilbaum.
         *
         * @param key der Schlüssel, der gesucht wird
         * @return 'true' falls der Schlüssel vorhanden ist, 'false' sonst
         */
        public abstract boolean find(int key);

        /**
         * Diese Methode entfernt den Schlüssel 'key' im Teilbaum, falls es ihn gibt.
         *
         * @param key der Schlüssel, der entfernt werden soll
         * @return 'true' falls der Schlüssel gefunden und entfernt wurde, 'false' sonst
         */
        public abstract boolean remove(int key);

        /**
         * Diese Methode ermittelt die Höhe des Teilbaums unter diesem Knoten.
         *
         * @return die ermittelte Höhe
         */
        public abstract int height();

        /**
         * Diese Methode ermittelt das Minimum im jeweiligen Teilbaum.
         *
         * @return das Minimum
         */
        public abstract Integer min();

        /**
         * Diese Methode ermittelt das Maximum im jeweiligen Teilbaum.
         *
         * @return das Maximum
         */
        public abstract Integer max();

        /**
         * Diese Methode ist zum Debuggen gedacht und prüft, ob es sich
         * um einen validen (a,b)-Baum handelt.
         *
         * @return 'true' falls der Baum ein valider (a,b)-Baum ist, 'false' sonst
         */
        public abstract boolean validAB(boolean root);

        /**
         * Diese Methode wandelt den Baum in das Graphviz-Format um.
         *
         * @return der nächste freie Index für das Graphviz-Format
         */
        public abstract int dot(StringBuilder sb, int from);
    }

    /**
     * Diese Klasse repräsentiert einen inneren Knoten des Baumes.
     */
    private class ABTreeInnerNode extends ABTreeNode {
        private ArrayList<Integer> keys;
        private ArrayList<ABTreeNode> children;

        public ABTreeInnerNode(ArrayList<Integer> keys, ArrayList<ABTreeNode> children) {
            this.keys = keys;
            this.children = children;
        }

        public ABTreeInnerNode(int key, ABTreeNode left, ABTreeNode right) {
            keys = new ArrayList<Integer>();
            children = new ArrayList<ABTreeNode>();
            keys.add(key);
            children.add(left);
            children.add(right);
        }

        public ABTreeInnerNode(int key) {
            this(key, new ABTreeLeaf(), new ABTreeLeaf());
        }

        @Override
        public void insert(int key) {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public boolean canSteal() {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public boolean find(int key) {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        public boolean remove(int key) {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public int height() {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public Integer min() {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public Integer max() {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public boolean validAB(boolean root) {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public int dot(StringBuilder sb, int from) {
            int mine = from++;
            sb.append(String.format("\tstruct%s [label=\"", mine));
            for (int i = 0; i < 2 * keys.size() + 1; i++) {
                if (i > 0)
                    sb.append("|");
                sb.append(String.format("<f%d> ", i));
                if (i % 2 == 1)
                    sb.append(keys.get(i / 2));
            }
            sb.append("\"];\n");
            for (int i = 0; i < children.size(); i++) {
                int field = 2 * i;
                sb.append(String.format("\tstruct%d:<f%d> -> struct%d;\n", mine, field, from));
                from = children.get(i).dot(sb, from);
            }
            return from;
        }
    }

    /**
     * Diese Klasse repräsentiert ein Blatt des Baumes.
     */
    private class ABTreeLeaf extends ABTreeNode {
        @Override
        public void insert(int key) {
            // TODO (???)
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public boolean canSteal() {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public boolean find(int key) {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public boolean remove(int key) {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public int height() {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public Integer min() {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public Integer max() {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public boolean validAB(boolean root) {
            // TODO
            throw new RuntimeException("Not Implemented");
        }

        @Override
        public int dot(StringBuilder sb, int from) {
            sb.append(String.format("\tstruct%d [label=leaf, shape=ellipse];\n", from));
            return from + 1;
        }
    }

    public ABTree(int a, int b) {
        // TODO
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Diese Objektvariable speichert die Wurzel des Baumes.
     */
    private ABTreeInnerNode root = null;

    public boolean validAB() {
        // TODO
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Diese Methode ermittelt die Höhe des Baumes.
     *
     * @return die ermittelte Höhe
     */
    public int height() {
        // TODO
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Diese Methode sucht einen Schlüssel im (a,b)-Baum.
     *
     * @param key der Schlüssel, der gesucht werden soll
     * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
     */
    public boolean find(int key) {
        // TODO
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Diese Methode fügt einen neuen Schlüssel in den (a,b)-Baum ein.
     *
     * @param key der einzufügende Schlüssel
     */
    public void insert(int key) {
        // TODO
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Diese Methode löscht einen Schlüssel aus dem (a,b)-Baum.
     *
     * @param key der zu löschende Schlüssel
     * @return 'true' falls der Schlüssel gefunden und gelöscht wurde, 'false' sonst
     */
    public boolean remove(int key) {
        // TODO
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Diese Methode wandelt den Baum in das Graphviz-Format um.
     *
     * @return der Baum im Graphiz-Format
     */
    String dot() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n");
        sb.append("\tnode [shape=record];\n");
        if (root != null)
            root.dot(sb, 0);
        sb.append("}");
        return sb.toString();
    }
}
