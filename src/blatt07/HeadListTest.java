package blatt07;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

public class HeadListTest {

    @Test
    public void testConstructor() {
        HeadList list = new HeadList();
        assertList(list, "[]");
    }
    
    @Test
    public void testAdd() {
        HeadList list = new HeadList();
        list.add(1337);
        assertList(list, "[1337]");
        list.add(42);
        assertList(list, "[1337,42]");
        list.add(0);
        assertList(list, "[1337,42,0]");
        list.add(-88);
        assertList(list, "[1337,42,0,-88]");
        
        HeadList list2 = new HeadList();
        list2.add(-19);
        assertList(list2, "[-19]");
    }

    @Test
    public void testRemove() {
        HeadList list = new HeadList();
        list.add(0);
        list.add(-18);
        list.add(1337);
        list.add(17);
        list.add(42);
        
        assertListRemove(list, -1, Integer.MIN_VALUE);
        assertList(list, "[0,-18,1337,17,42]");
        
        assertListRemove(list, 5, Integer.MIN_VALUE);
        assertList(list, "[0,-18,1337,17,42]");
        
        assertListRemove(list, 0, 0);
        assertList(list, "[-18,1337,17,42]");
        
        assertListRemove(list, 4, Integer.MIN_VALUE);
        assertList(list, "[-18,1337,17,42]");
        
        assertListRemove(list, 3, 42);
        assertList(list, "[-18,1337,17]");
        
        assertListRemove(list, 1, 1337);
        assertList(list, "[-18,17]");
    }

    @Test
    public void testReverse() {
        HeadList list = new HeadList();
        list.add(17);
        list.add(42);
        list.add(1337);
        list.add(-90);
        list.add(0);
        
        assertList(list, "[17,42,1337,-90,0]");
        list.reverse();
        assertList(list, "[0,-90,1337,42,17]");
        
        list = new HeadList();
        list.reverse();
        assertList(list, "[]");
        
        list = new HeadList();
        list.add(0);
        list.reverse();
        assertList(list, "[0]");
        
        list = new HeadList();
        list.add(1);
        list.add(2);
        list.reverse();
        assertList(list, "[2,1]");
    }
    
    public void assertListRemove(HeadList list, int index, int expected) {
        Assert.assertEquals(expected, list.remove(index));
    }

    public void assertList(HeadList list, String expected) {
        Assert.assertEquals(expected, list.toString());
    }
}
