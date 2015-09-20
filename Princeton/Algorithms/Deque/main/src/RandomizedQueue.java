import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author José Carlos Valero
 * @since 20/09/15
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] data;
    private int size = 0;

    public RandomizedQueue() {
        data = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        checkItem(item);
        if (size == data.length) {
            growArray();
        }
        data[size] = item;
        size++;
    }

    private void reduceArray() {
        this.data = Arrays.copyOf(data, (data.length / 2));
    }

    private void growArray() {
        this.data = Arrays.copyOf(data, (data.length * 2) + 1);
    }

    private void checkItem(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    public Item dequeue() {
        checkStatusToRemove();
        int i = StdRandom.uniform(size);
        Item target = data[i];
        data[i] = data[size - 1];
        data[size - 1] = null;
        size--;
        if (size <= (data.length / 4)) {
            reduceArray();
        }
        if(size == 0){
            this.data = (Item[]) new Object[1];
        }
        return target;
    }

    private void checkStatusToRemove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    public Item sample() {
        checkStatusToRemove();
        return data[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        int[] index;
        int next = 0;

        public QueueIterator() {
            this.index = randomSequence(RandomizedQueue.this.size);
        }

        private int[] randomSequence(int to) {
            int[] index = new int[to];
            for (int i = 0; i < to; i++) {
                int r = StdRandom.uniform(i + 1);
                index[i] = i;
                int aux = index[r];
                index[r] = i;
                index[i] = aux;
            }
            return index;
        }

        @Override
        public boolean hasNext() {
            return next < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return data[index[next++]];
        }
    }

    public static void main(String[] args) {
    }

}
