import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Deque implementation where
 * <p>
 * Performance requirements.
 * Your deque implementation must support each deque operation in constant worst-case time.
 * A deque containing N items must use at most 48N + 192 bytes of memory.
 * and use space proportional to the number of items currently in the deque.
 * Additionally, your iterator implementation must support each operation (including construction)
 * in constant worst-case time.
 * <p>
 * Because of the fact that worst-case time needs to be constant, and we are hitting always the top
 * or the bottom of the queue, the best data structure is a DoubleLinkedList.
 *
 * @param <Item>
 * @author José Carlos Valero Sánchez
 * @since 19 September 2015
 */
public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node<Item> first, last;

    public Deque() {
    }

    public static void main(String[] args) {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        checkItem(item);
        size++;
        Node<Item> newFirst = new Node<Item>(item, this.first, null);
        if (first != null) {
            first.previous = newFirst;
        }
        first = newFirst;
        if (size == 1) {
            last = newFirst;
        }
    }

    public void addLast(Item item) {
        checkItem(item);
        size++;
        Node<Item> newLast = new Node<Item>(item, null, this.last);
        if (last != null) {
            last.next = newLast;
        }
        last = newLast;
        if (size == 1) {
            first = newLast;
        }
    }

    private void checkItem(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    public Item removeFirst() {
        checkStatusToRemove();
        size--;
        Node<Item> first = this.first;
        this.first = first.next;
        if (this.first != null) {
            this.first.previous = null;
        } else {
            this.last = null;
        }
        return first._this;
    }

    public Item removeLast() {
        checkStatusToRemove();
        size--;
        Node<Item> last = this.last;
        this.last = last.previous;
        if (this.last != null) {
            this.last.next = null;
        } else {
            this.first = null;
        }
        return last._this;
    }

    private void checkStatusToRemove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private static class Node<T> {
        Node<T> previous;
        Node<T> next;
        T _this;

        public Node(T _this, Node<T> next, Node<T> previous) {
            this._this = _this;
            this.next = next;
            this.previous = previous;
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> next;

        public DequeIterator() {
            this.next = Deque.this.first;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item cNext = next._this;
            next = next.next;
            return cNext;
        }
    }
}