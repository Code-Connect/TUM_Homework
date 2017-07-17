package gad17.blatt11;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.stream.LongStream;

public class ConnectedComponentsTest {
    private ConnectedComponents test;
    private Graph g;

    private void addNodes(int times) {
        for (int i = 0; i < times; i++) g.addNode();
    }

    private void connect(int node, int... connected) {
        for (int i : connected) g.addEdge(g.getNode(node), g.getNode(i));
    }

    private void buildWay(int start, int end) {
        for (int i = start; i < end - 1; i++) connect(i, i + 1);
    }

    @Before
    public void setUp() throws Exception {
        test = new ConnectedComponents();
        g = new Graph();
    }

    @Test
    public void count_givenGrapthEmpty_returns0() throws Exception {
        Assert.assertEquals(0, test.countConnectedComponents(g));
    }

    @Test
    public void count_givenGraphWithoutConnections_returns10() throws Exception {
        addNodes(10);
        Assert.assertEquals(10, test.countConnectedComponents(g));
    }

    @Test
    public void count_givenGraphWithWayLength10_returns1() throws Exception {
        addNodes(10);
        buildWay(0, 10);
        Assert.assertEquals(1, test.countConnectedComponents(g));
    }

    @Test
    public void count_given15Nodes_givenGraphWithWayLength11_returns5() throws Exception {
        addNodes(15);
        buildWay(2, 13);
        Assert.assertEquals(5, test.countConnectedComponents(g));
    }

    @Test
    public void count_givenRandomNodesSize_givenEverySecondConnected_returnsHalf() throws Exception {
        int times = new Random().nextInt(333) * 2;
        addNodes(times);
        LongStream.range(0, times).filter(i -> i % 2 == 0)
                .forEach(i -> connect((int) i, (int) i + 1));
        Assert.assertEquals(times / 2, test.countConnectedComponents(g));
    }


}