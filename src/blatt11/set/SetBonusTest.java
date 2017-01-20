package blatt11.set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SetBonusTest {
    private Set<Integer> s;
    private ArrayList<Integer> expected;

    @Before
    public void setUp() throws Exception {
        s = new Set<Integer>();
        expected = new ArrayList<>();
    }

    private void add(int... input) {
        Assert.assertNotNull(s.add(0));
        Arrays.stream(input).forEach(value -> {
            s = s.add(value);
            expected.add(value);
        });
    }

    @Test
    public void iterator_givenEmptySet() throws Exception {
        Assert.assertNotNull(s.iterator());
        Assert.assertEquals(false, s.iterator().hasNext());
        try {
            s.iterator().next();
            Assert.fail("Empty iterator should throw NoSuchElemntException on next()");
        } catch (NoSuchElementException e) {}
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator_callingNextToOften_throwsException() throws Exception {
        Iterator<Integer> iterator = s.iterator();
        Assert.assertNotNull(iterator);
        while (true)
            iterator.next();
    }

    @Test
    public void iterator_given123() throws Exception {
        add(1, 2, 3);
        ArrayList<Integer> actual = new ArrayList<>();
        for (Integer value : s)
            actual.add(value);
        assertList(expected.toArray(), actual);
    }

    @Test
    public void forEach_given123() throws Exception {
        add(1, 2, 3);

        ArrayList<Integer> actual = new ArrayList<>();
        s.forEach(actual::add);

        assertList(expected.toArray(), actual);
    }

    private void assertList(Object[] expected, ArrayList<Integer> actualList) {
        for (Object o : expected) {
            if (!actualList.contains(o))
                Assert.fail("List does not contain: " + o);
            else
                actualList.remove(o);
        }
        if (!actualList.isEmpty())
            Assert.fail("List contains additional elements: " + actualList);
    }

    /*
    //Not neccessary to get 2 BonusPoints, but nice to have
    @Test
    public void spliterator_givenEmptySet() throws Exception {
        assertSize(0);
    }

    private void assertSize(int expected) {
        assertSize(s.spliterator(), expected);
    }

    private void assertSize(Spliterator<Integer> split, int expected) {
        Assert.assertNotNull(split);
        Assert.assertEquals(expected, split.estimateSize());
        Assert.assertEquals(Spliterator.SIZED, split.characteristics());
    }

    @Test
    public void spliterator_given1234() throws Exception {
        add(1, 2, 3, 4);
        assertSize(4);
    }

    @Test
    public void spliterator_trySplit_givenEmptySet() throws Exception {
        Assert.assertNotNull(s.spliterator());
        Assert.assertNull(s.spliterator().trySplit());
    }

    @Test
    public void spliterator_trySplit_given12() throws Exception {
        add(1, 2);
        Spliterator<Integer> spliterator = s.spliterator();
        Spliterator<Integer> split = spliterator.trySplit();
        assertSize(spliterator, 1);
        assertSize(split, 1);
    }

    @Test
    public void spliterator_trySplit_given1234() throws Exception {
        add(1, 2, 3, 4);
        Spliterator<Integer> spliterator = s.spliterator();
        Spliterator<Integer> split = spliterator.trySplit();
        assertSize(spliterator, 2);
        assertSize(split, 2);
    }

    @Test
    public void spliterator_trySplit_given1() throws Exception {
        add(1);
        Spliterator<Integer> spliterator = s.spliterator();
        Spliterator<Integer> split = spliterator.trySplit();

        assertSize(spliterator, 1);
        Assert.assertNull(split);
    }

    @Test
    public void spliterator_trySplitTwice_given12345() throws Exception {
        add(1, 2, 3, 4, 5);
        Spliterator<Integer> spliterator = s.spliterator();
        Spliterator<Integer> split = spliterator.trySplit();
        Spliterator<Integer> split2 = split.trySplit();

        Assert.assertEquals(5, spliterator.estimateSize()
                + split.estimateSize() + split2.estimateSize());
    }

    @Test
    public void tryAdvance_given1234() throws Exception {
        add(1, 2, 3, 4);
        Spliterator<Integer> spliterator = s.spliterator();
        ArrayList<Integer> spliteratorOut = new ArrayList<>();

        while (spliterator.tryAdvance(spliteratorOut::add)) ;

        assertList(new Object[]{4, 3, 2, 1}, spliteratorOut);
    }

    @Test
    public void spliterator_trySplit_withLoop_given1234() throws Exception {
        add(1, 2, 3, 4);
        Spliterator<Integer> spliterator = s.spliterator();
        Spliterator<Integer> split = spliterator.trySplit();
        ArrayList<Integer> spliteratorOut = new ArrayList<>();
        ArrayList<Integer> splitOut = new ArrayList<>();

        spliterator.forEachRemaining(spliteratorOut::add);
        split.forEachRemaining(splitOut::add);

        assertList(new Object[]{3, 4}, spliteratorOut);
        assertList(new Object[]{1, 2}, splitOut);
    }

    @Test
    public void spliterator_trySplit4times_withLoop_given1234() throws Exception {
        add(1, 2, 3, 4);
        Spliterator<Integer> split1 = s.spliterator();
        Spliterator<Integer> split2 = split1.trySplit();
        Spliterator<Integer> split11 = split1.trySplit();
        Spliterator<Integer> split21 = split2.trySplit();
        ArrayList<Integer> s1Out = new ArrayList<>();
        ArrayList<Integer> s2Out = new ArrayList<>();
        ArrayList<Integer> s11Out = new ArrayList<>();
        ArrayList<Integer> s21Out = new ArrayList<>();

        split1.forEachRemaining(s1Out::add);
        split2.forEachRemaining(s2Out::add);
        split11.forEachRemaining(s11Out::add);
        split21.forEachRemaining(s21Out::add);

        assertList(new Object[]{3}, s1Out);
        assertList(new Object[]{1}, s2Out);
        assertList(new Object[]{4}, s11Out);
        assertList(new Object[]{2}, s21Out);
    }
*/
}