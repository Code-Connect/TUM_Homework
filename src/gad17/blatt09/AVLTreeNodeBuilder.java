package gad17.blatt09;

import java.util.Arrays;
import java.util.TreeSet;

public class AVLTreeNodeBuilder extends AVLTreeNode {
    int validAVLCounter;
    int insertInput;
    int findInput;
    private int height;
    private TreeSet tree = new TreeSet();

    private AVLTreeNodeBuilder(int key) {
        super(key);
        tree.add(key);
    }

    static AVLTreeNodeBuilder node() {
        return new AVLTreeNodeBuilder(0);
    }

    public AVLTreeNodeBuilder withHeight(int height) {
        this.height = height;
        return this;
    }

    public AVLTreeNodeBuilder withKey(int key) {
        AVLTreeNodeBuilder out = new AVLTreeNodeBuilder(key);
        out.height = height;
        tree.remove(tree.first());
        out.tree = tree;
        out.tree.add(key);
        return out;
    }

    public AVLTreeNodeBuilder withTree(Integer... key) {
        tree.addAll(Arrays.asList(key));
        return this;
    }

    public AVLTreeNodeBuilder insert(int... keys) {
        for (int key : keys) insert(key);
        return this;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public boolean find(int key) {
        findInput = key;
        return tree.contains(key);
    }

    @Override
    public boolean validAVL() {
        validAVLCounter++;
        return super.validAVL();
    }

    @Override
    public AVLTreeNode insert(int key) {
        insertInput = key;
        return super.insert(key);
    }

}