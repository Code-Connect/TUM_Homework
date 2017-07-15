package gad17.blatt11;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;


public class GraphTest {
    private Graph g;

    @Before
    public void setup() {
        g = new Graph();
    }

    @Test
    public void constructor_givenNodesIsNull_thenNodesIsEmpty() {
        Assert.assertEquals(true, g.nodes.isEmpty());
    }

    @Test
    public void constructor_givenEdgesIsNull_thenEdgesIsEmpty() {
        Assert.assertEquals(true, g.edges.isEmpty());
    }

    @Test
    public void addNode_givenNodesIsEmpty_thenNodesSizeEquals1() {
        g.addNode();
        Assert.assertEquals(1, g.nodes.size());
    }

    @Test
    public void addNode_givenNodesSizeIs0_returns0() {
        Assert.assertEquals(new Integer(0), g.addNode());
    }

    @Test
    public void addNode_givenNodesSizeIs1_returns1() {
        g.addNode();
        Assert.assertEquals(new Integer(1), g.addNode());
    }

    @Test
    public void addNode_checkIndeciesFromFirstAdded() {
        g.addNode();
        Assert.assertEquals(0, g.nodes.get(0).index);
    }

    @Test
    public void addNode_given2Elements_returnsIndex1() {
        int i = addNodes(2);
        Assert.assertEquals(1, g.nodes.get(i - 1).index);
    }

    @Test
    public void addNode_given10Elements_returnsIndex9() {
        int i = addNodes(10);
        Assert.assertEquals(9, g.nodes.get(i - 1).index);
    }

    @Test
    public void addNode_givenRandomElements() {
        assertOverIndexes(j -> new Object[]{j, g.nodes.get(j).index});
    }

    private int addNodes(int times) {
        int i = 0;
        for (; i < times; i++)
            g.addNode();
        return i;
    }

    @Test(expected = Exception.class)
    public void getNode_givenNodesIsEmpty_throwsException() {
        g.getNode(0);
    }

    @Test
    public void getNode_givenNodesSizeOne_ReturnsNodeAtIndexOne() {
        addNodes(1);
        Assert.assertEquals(g.nodes.get(0), g.getNode(0));
    }

    @Test
    public void getNode_givenNodesSizeRandom() {
        assertOverIndexes(j -> new Object[]{g.nodes.get(j), g.getNode(j)});

    }

    private void assertOverIndexes(Function<Integer, Object[]> f) {
        int i = addNodes(new Random().nextInt(1000));
        for (int j = 0; j < i; j++)
            Assert.assertEquals(f.apply(j)[0], f.apply(j)[1]);
    }

    @Test
    public void addEdge_givenEdgesEmpty_thenEdgesSizeIs2() {
        addNodes(2);
        g.addEdge(g.getNode(0), g.getNode(1));
        Assert.assertEquals(2, g.edges.size());
    }

    @Test
    public void addEdge_inputTwoNodes_thenConnectsWithEdge() {
        addNodes(2);
        g.addEdge(g.getNode(0), g.getNode(1));

        assertEdges(0, 1);
        assertEdges(1, 0);
    }

    @Test
    public void addEdge_connect1To3Notes() {
        addNodes(4);
        g.addEdge(g.getNode(0), g.getNode(1));
        g.addEdge(g.getNode(2), g.getNode(0));
        g.addEdge(g.getNode(0), g.getNode(3));

        assertEdges(1, 0);
        assertEdges(2, 0);
        assertEdges(3, 0);
        assertEdges(0, 1, 2, 3);
    }

    private void assertEdges(int key, int... values) {
        ArrayList expected = new ArrayList();
        for (int v : values)
            expected.add(g.getNode(v));
        Assert.assertArrayEquals(expected.toArray(),
                g.edges.get(g.getNode(key)).toArray());
    }

    @Test(expected = Exception.class)
    public void addEdge_inputSameNode_throwsException() {
        g.addNode();
        g.addEdge(g.getNode(0), g.getNode(0));

    }

    @Test(expected = Exception.class)
    public void addEdge_inputAIsNull_throwsException() {
        g.addNode();
        g.addEdge(null, g.getNode(0));

    }

    @Test(expected = Exception.class)
    public void addEdge_inputBIsNull_throwsException() {
        g.addNode();
        g.addEdge(g.getNode(0), null);

    }

    @Test(expected = Exception.class)
    public void addEdge_inputAAndBAreNull_throwsException() {
        g.addEdge(null, null);
    }
}
