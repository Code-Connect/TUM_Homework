package gad17.blatt08;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class BinomialTreeNodeTest {
    private BinomialTreeNode a, b;

    private int getKey() {
        return a.key;
    }

    @Before
    public void setUp() throws Exception {
        a = new BinomialTreeNode(1);
        b = new BinomialTreeNode(5);
    }

    @Test
    public void constructor() {
        Assert.assertEquals(1, getKey());
    }

    @Test
    public void min_givenKey1_return1() throws Exception {
        assertMin(1, a);
    }

    @Test
    public void min_givenKey9_return9() throws Exception {
        a = new BinomialTreeNode(9);
        assertMin(9, a);
    }

    @Test
    public void rank_givenInit_returns0() throws Exception {
        assertRank(0, this.a);
    }

    private void assertRank(int expected, BinomialTreeNode node) {
        Assert.assertEquals(expected, node.rank());
    }

    @Test
    public void rank_given10_returns10() throws Exception {
        assertRank(10, buildTree(10));
    }

    @Test(expected = Exception.class)
    public void merge_given_both_null_throw_exception() throws Exception {
        BinomialTreeNode.merge(null, null);
    }

    @Test(expected = Exception.class)
    public void merge_given_a_null_throw_exception() throws Exception {
        BinomialTreeNode.merge(null, a);
    }

    @Test(expected = Exception.class)
    public void merge_given_b_null_throw_exception() throws Exception {
        BinomialTreeNode.merge(a, null);
    }

    @Test(expected = Exception.class)
    public void merge_given_differentLength_throw_exception() throws Exception {
        BinomialTreeNode.merge(new BinomialTreeNode(10), BinomialTreeNode.merge(a, b));
    }

    @Test(expected = Exception.class)
    public void merge_given_sameTree_throw_exception() throws Exception {
        BinomialTreeNode.merge(a,a);
    }

    @Test
    public void merge_givenRank0_Rank0_return_rank1() throws Exception {
        BinomialTreeNode actual = buildTree(1);
        assertRank(1, actual);
    }

    private BinomialTreeNode buildTreeReverse(int rank) {
        return buildTree(rank, rank - 1, -1);
    }

    private BinomialTreeNode buildTree(int rank) {
        return buildTree(rank, 0, 1);
    }

    private BinomialTreeNode buildTree(int rank, int min) {
        return buildTree(rank, min, 1);
    }

    private BinomialTreeNode buildTree(int rank, int counter, int delta) {
        if (rank <= 0) return new BinomialTreeNode(counter);
        return BinomialTreeNode.merge(buildTree(rank - 1,
                counter, delta),
                buildTree(rank - 1, counter + delta, delta));
    }

    @Test
    public void merge_givenRank1_Rank1_return_rank2() throws Exception {
        assertRank(2, buildTree(2));
    }

    @Test
    public void deleteMin_givenRank1_returnsTreeRank0() throws Exception {
        Assert.assertArrayEquals(new BinomialTreeNode[0], a.deleteMin());
    }

    @Test
    public void min_givenIsRoot_returnKey() throws Exception {
        assertMin(0, buildTree(2));
    }

    private void assertMin(int expected, BinomialTreeNode node) {
        Assert.assertEquals(expected, node.min());
    }

    @Test
    public void min_giveIsLast_returnLastKey() throws Exception {
        assertMin(0, buildTreeReverse(2));
    }


    @Test
    public void deleteMin_given_treeRank1_returnsTreeRank0() throws Exception {
        assertTreeArray(new BinomialTreeNode[]{buildTree(0, 1)}, buildTree(1).deleteMin());
    }

    private void assertTreeArray(BinomialTreeNode[] expected, BinomialTreeNode[] actual) {
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    public void deleteMin_given_treeRank2_returnsArraySize2() throws Exception {
        assertTreeArray(new BinomialTreeNode[]{buildTree(0, 1),
                        buildTree(1, 1)}, buildTree(2).deleteMin());
    }
}