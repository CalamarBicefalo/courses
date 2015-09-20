import edu.princeton.cs.algs4.RandomSeq;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author José Carlos Valero
 * @since 20/09/15
 */
public class RandomizedQueue<T> implements Iterable<T> {

    private T[] data;
    private int size = 0;

    public RandomizedQueue() {
        data = (T[]) new Object[10];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(T item) {
        checkItem(item);
        if(data.length == size){
            growArray();
        }
        if(size < (data.length/3)){
            reduceArray();
        }
        data[size]=item;
        size++;
    }

    private void reduceArray() {
        this.data = Arrays.copyOf(data,data.length/2);
    }

    private void growArray() {
        this.data = Arrays.copyOf(data,data.length*2);
    }

    private void checkItem(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    public T dequeue() {
        checkStatusToRemove();
        int i = StdRandom.uniform(size);
        T target = data[i];
        data[i] = data[size-1];
        size--;
        return target;
    }

    private void checkStatusToRemove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    public T sample() {
        return data[StdRandom.uniform(size)];
    }

    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
        int[] index;
        int next = 0;

        public QueueIterator() {
            this.index = randomSequence(RandomizedQueue.this.size);
        }

        private int[] randomSequence(int to){
            int[] index = new int[to];
            for (int i = 0; i < to; i++) {
                int r = StdRandom.uniform(i+1);
                index[i]=i;
                int aux = index[r];
                index[r]=i;
                index[i]=aux;
            }
            System.out.print(Arrays.toString(index));
            return index;
        }

        @Override
        public boolean hasNext() {
            return next < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return data[index[next++]];
        }
    }

    public static void main(String[] args) {
    }

}
