package gad17.blatt09;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static gad17.blatt09.AVLTreeNodeBuilder.node;

public class AVLTreeTest {
    private AVLTree test;

    @Before
    public void setUp() throws Exception {
        test = new AVLTree();
    }

    @Test
    public void validALL_givenEmpty_returnsFalse() throws Exception {
        Assert.assertFalse(test.validAVL());
    }

    @Test
    public void validALL_givenNotEmpty_thenCalls_validAVL() throws Exception {
        AVLTreeNodeBuilder node = node();
        test.root = node;

        Assert.assertTrue(test.validAVL());
        Assert.assertEquals(1, node.validAVLCounter);
    }

    @Test
    public void insert_givenEmpty_thenRootIsNotNull() throws Exception {
        test.insert(1);
        Assert.assertNotNull(test.root);
    }

    @Test
    public void insert_givenEmpty_input10_thenRootHas10() throws Exception {
        test.insert(10);
        Assert.assertTrue(test.root.find(10));
    }

    @Test
    public void insert_givenNotEmpty_thenCalls_insert() throws Exception {
        AVLTreeNodeBuilder node = node();
        test.root = node;

        test.insert(10);
        Assert.assertEquals(10, node.insertInput);
    }

    @Test
    public void find_givenEmpty_returnsFalse() throws Exception {
        Assert.assertFalse(test.find(10));
    }

    @Test
    public void find_givenNotEmpty_thenCalls_find() throws Exception {
        AVLTreeNodeBuilder node = node();
        test.root = node;

        Assert.assertTrue(test.find(0));
        Assert.assertEquals(0, node.findInput);
    }
}