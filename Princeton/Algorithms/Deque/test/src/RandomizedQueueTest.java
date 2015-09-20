import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * @author jose
 * @since 20/09/15
 */
public class RandomizedQueueTest {

    List<Object> objectList;
    @Before
    public void setUp() throws Exception {
        objectList = new ArrayList<Object>(10);
    }

    @Test
    public void testRandomizedQueueImplementsIterable() throws Exception {
        assertTrue(Iterable.class.isAssignableFrom(RandomizedQueue.class));
    }

    @Test
    public void testNewQueueIsEmpty() throws Exception {
        assertTrue(new RandomizedQueue<Object>().isEmpty());
    }

    @Test
    public void testNewRandomizedQueueHasSizeZero() throws Exception {
        assertThat(new RandomizedQueue<Object>().size(), is(0));
    }

    @Test
    public void testRandomizedQueueWithOneElementIsNotEmpty() throws Exception {
        RandomizedQueue<Object> queue = new RandomizedQueue<Object>();
        queue.enqueue(new Object());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testRandomizedQueueWithOneElementHasSizeOne() throws Exception {
        RandomizedQueue<Object> queue = new RandomizedQueue<Object>();
        queue.enqueue(new Object());
        assertThat(queue.size(), is(1));
    }

    @Test
    public void testRandomizedQueueueUpdatesSize() throws Exception {
        RandomizedQueue<Object> queue = new RandomizedQueue<Object>();
        queue.enqueue(new Object());
        queue.dequeue();
        assertThat(queue.size(), is(0));
    }

    @Test(expected = NullPointerException.class)
    public void testThrowsNPEForNull() throws Exception {
        new RandomizedQueue<Object>().enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testThrowsNSEERemovingFromEmptyQueue() throws Exception {
        new RandomizedQueue<Object>().dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void testThrowsNSEESamplingFromEmptyQueue() throws Exception {
        new RandomizedQueue<Object>().sample();
    }

    @Test
    public void testRandomizedQueueReturnsElements() throws Exception {
        RandomizedQueue<Object> queue = new RandomizedQueue<Object>();
        Object first = new Object();
        Object second = new Object();
        objectList.add(first);
        objectList.add(second);
        queue.enqueue(second);
        queue.enqueue(first);
        Object removedFirst = queue.dequeue();
        assertTrue(objectList.remove(removedFirst));
        assertThat(removedFirst, isOneOf(first, second));
        assertThat(removedFirst, not(isIn(objectList)));
        assertThat(queue.size(), is(1));
        assertThat(queue.dequeue(), isIn(objectList));
        assertThat(queue.size(), is(0));
    }

    @Test
    public void testMultipleManipulations() throws Exception {
        RandomizedQueue<Object> queue = new RandomizedQueue<Object>();
        Object first = new Object();
        Object second = new Object();
        Object third = new Object();
        queue.enqueue(first);
        objectList.add(first);
        assertThat(queue.size(), is(1));
        queue.enqueue(second);
        objectList.add(second);
        assertThat(queue.size(), is(2));
        Object removedFirst = queue.dequeue();
        assertTrue(objectList.remove(removedFirst));
        assertThat(removedFirst, isOneOf(first, second, third));
        assertThat(removedFirst, not(isIn(objectList)));
        assertThat(queue.size(), is(1));
        queue.enqueue(third);
        objectList.add(third);
        Object removedSecond = queue.dequeue();
        assertThat(removedSecond, isIn(objectList));
        assertTrue(objectList.remove(removedSecond));
        assertThat(queue.size(), is(1));
        assertThat(queue.dequeue(), isIn(objectList));
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIteratorIsNotNull() throws Exception {
        assertNotNull(new RandomizedQueue<Object>().iterator());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorDoesNotSupportRemove() throws Exception {
        new RandomizedQueue<Object>().iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorThrowsNSEEIfNoMoreElementsAvailable() throws Exception {
        new RandomizedQueue<Object>().iterator().next();
    }

    @Test
    public void testEmptyIteratorHasNotNext() throws Exception {
        assertFalse(new RandomizedQueue<Object>().iterator().hasNext());
    }

    @Test
    public void testNonEmptyIteratorHasNext() throws Exception {
        RandomizedQueue<Object> queue = new RandomizedQueue<Object>();
        queue.enqueue(new Object());
        assertTrue(queue.iterator().hasNext());
    }

    @Test
    public void testIteratorIteratesAsExpected() throws Exception {
        RandomizedQueue<Object> queue = new RandomizedQueue<Object>();
        Object first = new Object();
        Object second = new Object();
        queue.enqueue(second);
        queue.enqueue(first);
        objectList.add(first);
        objectList.add(second);
        Iterator<Object> iterator = queue.iterator();
        assertTrue(iterator.hasNext());
        Object next = iterator.next();
        assertThat(next, isIn(objectList));
        assertTrue(objectList.remove(next));
        assertTrue(iterator.hasNext());
        next = iterator.next();
        assertThat(next, isIn(objectList));
        assertTrue(objectList.remove(next));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorIteratesAsExpectedHavingManipulatedTheRandomizedQueue() throws Exception {
        RandomizedQueue<Object> queue = new RandomizedQueue<Object>();
        Object first = new Object();
        Object second = new Object();
        Object third = new Object();
        Object fourth = new Object();

        queue.enqueue(second);
        queue.enqueue(first);
        queue.enqueue(third);
        queue.enqueue(fourth);

        objectList.add(first);
        objectList.add(second);
        objectList.add(third);
        objectList.add(fourth);

        Iterator<Object> iterator = queue.iterator();
        assertTrue(iterator.hasNext());
        Object i1 = iterator.next();
        assertTrue(iterator.hasNext());
        Object i2 = iterator.next();
        assertTrue(iterator.hasNext());
        Object i3 = iterator.next();
        assertTrue(iterator.hasNext());
        Object i4 = iterator.next();
        assertFalse(iterator.hasNext());

        assertTrue(objectList.remove(i1));
        assertTrue(objectList.remove(i2));
        assertTrue(objectList.remove(i3));
        assertTrue(objectList.remove(i4));

        assertEquals(0,objectList.size());
    }

    @Test
    public void testScenario2() throws Exception {
            RandomizedQueue<Object> rq = new RandomizedQueue<Object>();
            rq.enqueue(966);
            rq.isEmpty();
            rq.dequeue();
            rq.isEmpty();
            rq.isEmpty();
            rq.isEmpty();
            rq.size();
            rq.size();
            rq.enqueue(947);
    }

    @Test
    public void testScenario6Loitering() throws Exception {
        RandomizedQueue<Object> rq = new RandomizedQueue<Object>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.dequeue();
        rq.dequeue();
    }
}