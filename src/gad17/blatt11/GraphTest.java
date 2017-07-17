package gad17.blatt11;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Function;


public class GraphTest extends Graph {
    private Graph g;

    private int getIndex(Node node) {
        //TODO adjust to your code
        return node.index;
    }

    private Collection<Node> getConnected(Node a) {
        //TODO adjust to your code
        return a.connected;
    }

    private List<Node> getNodes() {
        //TODO adjust to your code
        return g.nodes;
    }

    @Before
    public void setup() {
        g = new Graph();
    }

    @Test
    public void constructor_givenNodesIsNull_thenNodesIsEmpty() {
        Assert.assertEquals(true, getNodes().isEmpty());
    }

    @Test
    public void addNode_givenNodesIsEmpty_thenNodesSizeEquals1() {
        g.addNode();
        Assert.assertEquals(1, getNodes().size());
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
        assertIndex(0, 0);
    }

    private void assertIndex(int index, int expected) {
        Assert.assertEquals(expected, getIndex(getNodes().get(index)));
    }

    @Test
    public void addNode_given2Elements_returnsIndex1() {
        int i = addNodes(2);
        assertIndex(i - 1, 1);
    }

    @Test
    public void addNode_given10Elements_returnsIndex9() {
        int i = addNodes(10);
        assertIndex(i - 1, 9);
    }

    @Test
    public void addNode_givenRandomElements() {
        assertOverIndexes(j -> new Object[]{j, getIndex(getNodes().get(j))});
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
        addNode();
        g.nodes = new ArrayList<>(nodes);
        Assert.assertEquals(nodes.get(0), g.getNode(0));
    }

    @Test
    public void getNode_givenNodesSizeRandom() {
        assertOverIndexes(j -> new Object[]{getNodes().get(j), g.getNode(j)});

    }

    private void assertOverIndexes(Function<Integer, Object[]> f) {
        int i = addNodes(new Random().nextInt(1000));
        for (int j = 0; j < i; j++)
            Assert.assertEquals(f.apply(j)[0], f.apply(j)[1]);
    }

    @Test
    public void addEdge_givenEdgesEmpty_thenEdgesSizeIs1() {
        addNodes(2);
        Node a = g.getNode(0);
        Node b = g.getNode(1);
        g.addEdge(a, b);
        Assert.assertEquals(1, getConnected(a).size());
        Assert.assertEquals(1, getConnected(b).size());
    }

    @Test
    public void addEdge_input2TimesSameEdge_givenEdgesEmpty_thenEdgesSizeIs1() {
        addNodes(2);
        Node a = g.getNode(0);
        Node b = g.getNode(1);
        g.addEdge(a, b);
        g.addEdge(b, a);
        Assert.assertEquals(1, getConnected(a).size());
        Assert.assertEquals(1, getConnected(b).size());
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
                getConnected(g.getNode(key)).toArray());
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
