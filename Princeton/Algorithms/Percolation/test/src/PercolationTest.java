import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by jose on 8/09/15.
 */
public class PercolationTest {

    @Test(expected = IllegalArgumentException.class)
    public void zeroGridIsNotValid() throws Exception {
        new Percolation(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeArgumentIsNotValid() throws Exception {
        new Percolation(-3);
    }

    @Test
    public void positiveGridCanBeCreated() throws Exception {
        Percolation percolation = new Percolation(30);
        assertNotNull(percolation);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void openWithZeroIThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.open(0,3);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void openWithZeroJThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.open(3,0);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void openWithHighIThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.open(6,3);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void openWithHighJThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.open(3, 6);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullWithZeroIThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.isFull(0, 3);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullWithZeroJThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.isFull(3, 0);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullWithHighIThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.isFull(6, 3);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullWithHighJThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.isFull(3, 6);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenWithZeroIThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.isOpen(0, 3);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenWithZeroJThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.isOpen(3, 0);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenWithHighIThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.isOpen(6, 3);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenWithHighJThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.isOpen(3,6);
    }
}