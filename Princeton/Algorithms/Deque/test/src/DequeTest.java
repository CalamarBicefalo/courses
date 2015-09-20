import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * @author jose
 * @since 19/09/15
 */
public class DequeTest {

    @Test
    public void testDequeImplementsIterable() throws Exception {
        assertTrue(Iterable.class.isAssignableFrom(Deque.class));
    }

    @Test
    public void testNewQueueIsEmpty() throws Exception {
        assertTrue(new Deque<Object>().isEmpty());
    }

    @Test
    public void testNewDequeHasSizeZero() throws Exception {
        assertThat(new Deque<Object>().size(), is(0));
    }

    @Test
    public void testDequeWithOneElementFirstIsNotEmpty() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        deque.addFirst(new Object());
        assertFalse(deque.isEmpty());
    }

    @Test
    public void testDequeWithOneLastElementIsNotEmpty() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        deque.addLast(new Object());
        assertFalse(deque.isEmpty());
    }

    @Test
    public void testDequeWithOneFirstElementHasSizeOne() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        deque.addFirst(new Object());
        assertThat(deque.size(), is(1));
    }

    @Test
    public void testDequeWithOneLastElementHasSizeOne() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        deque.addLast(new Object());
        assertThat(deque.size(), is(1));
    }

    @Test
    public void testRemoveFirstUpdatesSize() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        deque.addFirst(new Object());
        deque.removeFirst();
        assertThat(deque.size(), is(0));
    }

    @Test
    public void testRemoveLastUpdatesSize() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        deque.addFirst(new Object());
        deque.removeLast();
        assertThat(deque.size(), is(0));
    }

    @Test(expected = NullPointerException.class)
    public void testThrowsNPEForNullFirst() throws Exception {
        new Deque<Object>().addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void testThrowsNPEForNullLast() throws Exception {
        new Deque<Object>().addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testThrowsNSEERemovingFromEmptyQueueFirst() throws Exception {
        new Deque<Object>().removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void testThrowsNSEERemovingFromEmptyQueueLast() throws Exception {
        new Deque<Object>().removeLast();
    }

    @Test
    public void testRemoveFirstReturnsFirst() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        Object first = new Object();
        Object second = new Object();
        deque.addFirst(second);
        deque.addFirst(first);
        assertEquals(deque.removeFirst(), first);
        assertThat(deque.size(), is(1));
        assertEquals(deque.removeFirst(), second);
        assertThat(deque.size(), is(0));
    }

    @Test
    public void testRemoveLastUReturnsLast() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        Object first = new Object();
        Object second = new Object();
        deque.addFirst(second);
        deque.addFirst(first);
        assertEquals(deque.removeLast(), second);
        assertThat(deque.size(), is(1));
        assertEquals(deque.removeLast(), first);
        assertThat(deque.size(), is(0));
    }

    @Test
    public void testMultipleManipulations() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        Object first = new Object();
        Object second = new Object();
        Object third = new Object();
        deque.addFirst(first);
        assertThat(deque.size(), is(1));
        deque.addLast(second);
        assertThat(deque.size(), is(2));
        assertEquals(deque.removeFirst(), first);
        assertThat(deque.size(), is(1));
        deque.addLast(third);
        assertEquals(deque.removeLast(), third);
        assertThat(deque.size(), is(1));
        assertEquals(deque.removeFirst(), second);
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testIteratorIsNotNull() throws Exception {
        assertNotNull(new Deque<Object>().iterator());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorDoesNotSupportRemove() throws Exception {
        new Deque<Object>().iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorThrowsNSEEIfNoMoreElementsAvailable() throws Exception {
        new Deque<Object>().iterator().next();
    }

    @Test
    public void testEmptyIteratorHasNotNext() throws Exception {
        assertFalse(new Deque<Object>().iterator().hasNext());
    }

    @Test
    public void testNonEmptyIteratorHasNext() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        deque.addLast(new Object());
        assertTrue(deque.iterator().hasNext());
    }

    @Test
    public void testIteratorIteratesAsExpected() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        Object first = new Object();
        Object second = new Object();
        deque.addFirst(second);
        deque.addFirst(first);
        Iterator<Object> iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(first,iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(second, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorIteratesAsExpectedHavingManipulatedTheDeque() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        Object first = new Object();
        Object second = new Object();
        deque.addFirst(second);
        deque.addFirst(first);

        deque.addFirst(new Object());
        deque.addLast(new Object());
        deque.removeFirst();
        deque.removeLast();
        Iterator<Object> iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(first, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(second, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testScenario11() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        deque.addFirst(1);
        deque.removeLast();
        assertEquals(0, deque.size());
        assertFalse(deque.iterator().hasNext());
    }

}