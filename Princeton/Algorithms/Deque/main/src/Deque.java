import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Deque implementation where
 *
 * Performance requirements.
 * Your deque implementation must support each deque operation in constant worst-case time.
 * A deque containing N items must use at most 48N + 192 bytes of memory.
 * and use space proportional to the number of items currently in the deque.
 * Additionally, your iterator implementation must support each operation (including construction)
 * in constant worst-case time.
 *
 * Because of the fact that worst-case time needs to be constant, and we are hitting always the top
 * or the bottom of the queue, the best data structure is a DoubleLinkedList.
 * @param <T>
 */
public class Deque<T> implements Iterable<T> {

    private int size;
    private Node<T> first,last;

    public Deque() {
    }                           // construct an empty deque

    public boolean isEmpty() {
        return size==0;
    }                 // is the deque empty?

    public int size() {
        return size;
    }                        // return the number of items on the deque

    public void addFirst(T item) {
        checkItem(item);
        size++;
        Node<T> newFirst = new Node<T>(item, this.first, null);
        if(first != null){
            first.previous = newFirst;
        }
        first = newFirst;
        if(size==1){
            last = newFirst;
        }
    }

    public void addLast(T item) {
        checkItem(item);
        size++;
        Node<T> newLast = new Node<T>(item, null, this.last);
        if(last != null){
            last.next = newLast;
        }
        last = newLast;
        if(size==1){
            first = newLast;
        }
    }

    private void checkItem(T item) {
        if(item==null){
            throw new NullPointerException();
        }
    }

    public T removeFirst() {
        checkStatusToRemove();
        size--;
        Node<T> first = this.first;
        this.first = first.next;
        return first._this;
    }

    public T removeLast() {
        checkStatusToRemove();
        size--;
        Node<T> last = this.last;
        this.last = last.previous;
        return last._this;
    }

    private void checkStatusToRemove() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
    }

    public Iterator<T> iterator() {
        return null;
    }         // return an iterator over items in order from front to end

    public static void main(String[] args) {
    }   // unit testing

    private static class Node<T>{
        Node<T> previous;
        Node<T> next;
        T _this;

        public Node(T _this, Node<T> next, Node<T> previous) {
            this._this = _this;
            this.next = next;
            this.previous = previous;
        }
    }
}