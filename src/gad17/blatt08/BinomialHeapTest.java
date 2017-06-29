package gad17.blatt08;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;


public class BinomialHeapTest {

    private BinomialHeap test;

    @Before
    public void setUp() throws Exception {
        test = new BinomialHeap();
    }

    @Test
    public void constructor() throws Exception {
        Assert.assertNotNull(test.heap);
        Assert.assertTrue(test.heap.isEmpty());
    }

    @Test(expected = Exception.class)
    public void min_isEmpty_throwsException() throws Exception {
        test.min();
    }

    @Test
    public void min_givenKey0_return0() throws Exception {
        insert(0);
        assertMin(0);
    }

    private void insert(int... keys) {
        for (int key : keys) test.insert(key);
    }

    private void assertMin(int expected) {
        Assert.assertEquals(expected, test.min());
    }

    @Test
    public void min_givenKeys2_1_return1() throws Exception {
        insert(2, 1);
        assertMin(1);
    }

    @Test
    public void min_givenKeys2_0_1_return0() throws Exception {
        insert(2, 0, 1);
        assertMin(0);
    }

    @Test
    public void insert_input2_givenInit() throws Exception {
        insert(2);
        assertHeap(new BinomialTreeNode(2));
    }

    @Test
    public void insert_given0_input2() throws Exception {
        assertInsert(0, 2, 0, 2);
    }

    @Test
    public void insert_given2_input0() throws Exception {
        assertInsert(2, 0, 0, 2);
    }

    private void assertInsert(int in0, int in1, int ex0, int ex1) {
        insert(in0, in1);
        BinomialTreeNode a = new BinomialTreeNode(ex0);
        BinomialTreeNode b = new BinomialTreeNode(ex1);
        a.tree.add(b);
        assertHeap(a);
    }

    @Test
    public void insert_givenRank0And1_input99_thenMerge() throws Exception {
        assertRisingHeap(1);
    }

    private void assertRisingHeap(int size) {
        BinomialTreeNode e0 = buildRisingHeap(size);

        insert(99);
        assertHeap(e0);
    }

    @Test
    public void insert_givenRanksFrom0To2_input99_thenMerge() throws Exception {
        assertRisingHeap(2);
    }

    @Test
    public void insert_givenRanksFrom0To10_input99_thenMerge() throws Exception {
        assertRisingHeap(10);
    }

    private BinomialTreeNode buildRisingHeap(int size) {
        for (int i = 0; i <= size; i++)
            test.heap.add(BinomialTreeNodeTest.buildTree(i, i));
        return test.heap.get(0);
    }

    private void assertHeap(BinomialTreeNode... nodes) {
        Assert.assertEquals(Arrays.asList(nodes).toString(), test.heap.toString());
    }

    @Test(expected = Exception.class)
    public void deleteMin_given_emptyHeap_throwRuntimeException() throws Exception {
        test.deleteMin();
    }

    @Test
    public void deleteMin0_given_Rank1_return0() throws Exception {
        insert(1, 2, 0);
        System.out.println(test.heap);
        assertDeleteMin(0);
    }

    private void assertDeleteMin(int... expecteds) {
        for (int expected : expecteds)
            Assert.assertEquals(expected, test.deleteMin());
    }

    @Test
    public void deleteMin0_givenRank2_givenHeapSize1_thenHeapSize2() throws Exception {
        insert(1, 0, 4, 2);
        test.deleteMin();
        Assert.assertEquals(2, test.heap.size());
    }

    @Test
    public void deleteMin0_given_Rank2_thenRank1() throws Exception {
        insert(1, 0, 4, 2);
        test.deleteMin();
        test.heap.sort(Comparator.comparingInt(BinomialTreeNode::rank));
        Assert.assertEquals(0, test.heap.get(0).rank());
        Assert.assertEquals(1, test.heap.get(1).rank());
    }

    @Test
    public void deleteMinAll_given_Rank2_returnsSorted() throws Exception {
        insert(1, 0, 4, 2);
        assertDeleteMin(0, 1, 2, 4);
    }

    @Test
    public void deleteMin_given_Rank2_thenUpdateTree() throws Exception {
        insert(1, 0, 4, 2);
        String e1 = test.heap.get(0).toString();
        test.deleteMin();
        Assert.assertNotEquals(e1, test.heap.get(0).toString());
    }

    @Test
    public void deleteMin0_given_heapSize2_then_heapSize1() throws Exception {
        insert(1, 2, 0);
        test.deleteMin();
        Assert.assertEquals(1, test.heap.size());
    }

    @Test
    public void deleteMin0_givenRank2AndRank0_givenSize2_thanSize1() throws Exception {
        insert(1, 2, 0, 4, 5);
        test.deleteMin();
        Assert.assertEquals(1, test.heap.size());
    }

    @Test
    public void deleteMin0_givenRanks0To3_givenHeapSize4_thenHeapSize3() throws Exception {
        insert(0, 1, 2, 3, 4, 5, 6);
        test.deleteMin();
        Assert.assertEquals(3, test.heap.size());
    }
}