import org.junit.Test;

/**
 * @author jose
 * @since 27/09/15
 */
public class BruteCollinearPointsTest {

    @Test(expected = NullPointerException.class)
    public void testNullArrayShouldThrowNPE() throws Exception {
        new BruteCollinearPoints(null);
    }

    @Test(expected = NullPointerException.class)
    public void testIfNullPointsArePresentShouldThrowNPE() throws Exception {
        Point[] points = {new Point(0, 1), new Point(1, 0), null, new Point(2, 0)};
        new BruteCollinearPoints(points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIAEIfRepeatedPointsArePreent() throws Exception {
        Point[] points = {new Point(0, 1), new Point(0, 1), new Point(2, 0)};
        new BruteCollinearPoints(points);
    }
}