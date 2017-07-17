package gad17.blatt11;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.LongStream;

public class BFSTest {

    private BFS test;
    private Graph g;

    private void addNodes(int times) {
        for (int i = 0; i < times; i++) g.addNode();
    }

    private void connect(int node, int... connected) {
        for (int i : connected) g.addEdge(g.getNode(node), g.getNode(i));
    }

    private void buildWay(int length) {
        for (int i = 0; i < length - 1; i++) connect(i, i + 1);
    }

    @Before
    public void setUp() throws Exception {
        test = new BFS();
        g = new Graph();
    }

    @Test(expected = Exception.class)
    public void sssp_inputNull_throwsException() throws Exception {
        test.sssp(null);
    }

    @Test
    public void sssp_givenStartHasNoEdges_thenDepthIs0() throws Exception {
        addNodes(1);
        Graph.Node node = g.getNode(0);

        test.sssp(node);

        assertDepth(0, 0);
    }

    @Test(expected = Exception.class)
    public void getDepth_givenSsspDidNotRun_throwsException() throws Exception {
        addNodes(1);
        Graph.Node node = g.getNode(0);

        test.getDepth(node);
    }

    @Test(expected = Exception.class)
    public void getDepth_inputNull_givenSsspRan_throwsException() throws Exception {
        addNodes(1);
        Graph.Node node = g.getNode(0);
        test.sssp(node);

        test.getDepth(null);
    }

    @Test
    public void sssp_givenStartHasNoEdges_thenParentIsNull() throws Exception {
        addNodes(1);
        Graph.Node node = g.getNode(0);

        test.sssp(node);

        assertParent(0, 0);
    }

    private void assertParent(int expected, int id) {
        Assert.assertEquals(g.getNode(expected), test.getParent(g.getNode(id)));
    }

    @Test(expected = Exception.class)
    public void getParent_givenSsspDidNotRun_throwsException() throws Exception {
        addNodes(1);
        Graph.Node node = g.getNode(0);

        test.getParent(node);
    }

    @Test(expected = Exception.class)
    public void getParent_inputNull_givenSsspRan_throwsException() throws Exception {
        addNodes(1);
        Graph.Node node = g.getNode(0);
        test.sssp(node);

        test.getParent(null);
    }

    private void assertDepth(int expected, int id) {
        Assert.assertEquals("Error at Node with id: " + id, new Integer(expected), test.getDepth(g.getNode(id)));
    }

    @Test
    public void getDepth_givenEverythingConnectedWithStart_thenEveryDepthIs1ButStart() throws Exception {
        addNodes(10);
        Graph.Node start = g.getNode(0);
        connect(0, LongStream.range(1, 10).mapToInt(i -> (int) i).toArray());
        test.sssp(start);

        LongStream.range(1, 10).mapToInt(i -> (int) i)
                .forEach(i -> assertDepth(1, i));
    }

    @Test
    public void getParent_givenEverythingConnectedWithStart_thenEveryDepthIsStart() throws Exception {
        addNodes(10);
        Graph.Node start = g.getNode(0);
        connect(0, LongStream.range(1, 10).mapToInt(i -> (int) i).toArray());
        test.sssp(start);

        LongStream.range(1, 10).mapToInt(i -> (int) i)
                .forEach(i -> assertParent(0, i));
    }

    @Test
    public void getDepth_givenWayOfLength10_thenNodesIncreaseInDepth() throws Exception {
        addNodes(10);
        Graph.Node start = g.getNode(0);
        buildWay(10);
        test.sssp(start);

        LongStream.range(0, 10).mapToInt(i -> (int) i)
                .forEach(i -> assertDepth(i, i));
    }

    @Test
    public void getParent_givenWayOfLength10() throws Exception {
        addNodes(10);
        Graph.Node start = g.getNode(0);
        buildWay(10);
        test.sssp(start);

        LongStream.range(0, 9).mapToInt(i -> (int) i)
                .forEach(i -> assertParent(i, i + 1));
    }

    @Test(expected = Exception.class)
    public void getDepth_givenNotConnected() throws Exception {
        addNodes(2);
        Graph.Node start = g.getNode(0);
        test.sssp(start);

        System.out.println("actual: " + test.getDepth(g.getNode(1)));
    }

    @Test(expected = Exception.class)
    public void getParent_givenNotConnected() throws Exception {
        addNodes(2);
        Graph.Node start = g.getNode(0);
        test.sssp(start);

        System.out.println("actual: " + test.getParent(g.getNode(1)));
    }

    @Test
    public void integration() throws Exception {
        addNodes(11);
        Graph.Node start = g.getNode(0);
        connect(0, 1, 7);
        connect(1, 2, 3);
        connect(2, 3);
        connect(3, 4);
        connect(4, 5, 6);
        connect(6, 3);
        connect(7, 8);
        connect(8, 9, 10);
        connect(9, 2, 4, 7);
        connect(10, 4);
        test.sssp(start);

        assertDepth(0, 0);
        assertDepth(1, 1);
        assertDepth(2, 2);
        assertDepth(2, 3);
        assertDepth(3, 4);
        assertDepth(4, 5);
        assertDepth(3, 6);
        assertDepth(1, 7);
        assertDepth(2, 8);
        assertDepth(2, 9);
        assertDepth(3, 10);
    }
}