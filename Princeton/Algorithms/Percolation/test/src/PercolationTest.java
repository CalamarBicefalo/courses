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
        percolation.open(0, 3);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void openWithZeroJThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.open(3, 0);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void openWithHighIThrowsException() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.open(6, 3);
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
        percolation.isOpen(3, 6);
    }

    @Test
    public void allCellsAreClosed() throws Exception {
        Percolation percolation = new Percolation(5);
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                assertFalse(percolation.isOpen(i, j));
            }
        }
    }

    @Test
    public void openCellIsOpen() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.open(2,2);
        assertTrue(percolation.isOpen(2,2));
    }

    @Test
    public void isFullSingleCell() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.open(1,2);
        assertTrue(percolation.isFull(1, 2));
        assertFalse(percolation.isFull(1, 1));
        assertFalse(percolation.isFull(1, 3));
    }

    @Test
    public void isNotFullSingleCell() throws Exception {
        Percolation percolation = new Percolation(5);
        percolation.open(2, 2);
        assertFalse(percolation.isFull(2, 2));
    }
    @Test
    public void emptyGridDoesNotPercolate() throws Exception {
        Percolation percolation = new Percolation(5);
        assertFalse(percolation.percolates());
    }

    @Test
    public void singleCellPercolates() throws Exception {
        Percolation percolation = new Percolation(1);
        assertFalse(percolation.percolates());
        percolation.open(1,1);
        assertTrue(percolation.percolates());
    }

    @Test
    public void simpleConnectedColumnPercolates() throws Exception {
        Percolation percolation = new Percolation(3);
        percolation.open(1,2);
        percolation.open(2, 2);
        percolation.open(3, 2);
        assertTrue(percolation.percolates());
    }

    @Test
    public void complexConnectedColumnPercolates() throws Exception {
        Percolation percolation = new Percolation(8);
        percolation.open(1,2);
        assertFalse(percolation.percolates());
        percolation.open(1,3);
        assertFalse(percolation.percolates());
        percolation.open(2,3);
        assertFalse(percolation.percolates());
        percolation.open(3,3);
        assertFalse(percolation.percolates());
        percolation.open(3,2);
        assertFalse(percolation.percolates());
        percolation.open(4,2);
        assertFalse(percolation.percolates());
        percolation.open(5,2);
        assertFalse(percolation.percolates());
        percolation.open(5,3);
        assertFalse(percolation.percolates());
        percolation.open(5,4);
        assertFalse(percolation.percolates());
        percolation.open(5,5);
        assertFalse(percolation.percolates());
        percolation.open(6,5);
        assertFalse(percolation.percolates());
        percolation.open(7,5);
        assertFalse(percolation.percolates());
        percolation.open(8, 5);
        assertTrue(percolation.percolates());
    }

    @Test
    public void realScenario3() throws Exception {
        Percolation percolation = new Percolation(3);
        percolation.open(1,3);
        assertFalse(percolation.percolates());
        percolation.open(2,3);
        assertFalse(percolation.percolates());
        percolation.open(3,3);
        assertTrue(percolation.percolates());
        percolation.open(3,1);
        assertFalse(percolation.isFull(3,1));
        assertTrue(percolation.percolates());
        percolation.open(2,1);
        percolation.open(1,1);
        assertTrue(percolation.percolates());
    }

    @Test
    public void realScenario7() throws Exception {
        Percolation percolation = new Percolation(7);

        percolation.open(6,1);
        percolation.open(7,1);
        percolation.open(7,2);
        percolation.open(7,4);
        percolation.open(1,1);
        percolation.open(1,5);
        percolation.open(2,5);
        percolation.open(3,5);
        percolation.open(4,5);
        percolation.open(5,5);
        percolation.open(6,5);
        percolation.open(7,5);
        assertFalse(percolation.isFull(6,1));
        percolation.open(2,1);
        percolation.open(4,1);
        percolation.open(5,1);
        percolation.open(3,1);
    }


}