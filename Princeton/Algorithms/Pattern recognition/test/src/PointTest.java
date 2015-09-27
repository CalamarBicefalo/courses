import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;

/**
 * @author jose
 * @since 27/09/15
 */
public class PointTest {

    /*
    COMPARE-TO TESTS
     */
    @Test
    public void testNullInputIsLowerThanAnyPoint() throws Exception {
        Point p = new Point(0,0);
        assertThat(p.compareTo(null),greaterThan(0));
    }

    @Test
    public void testSamePointIsEqual() throws Exception {
        Point p = new Point(0,0);
        assertThat(p.compareTo(p),is(0));
    }

    @Test
    public void testEqualPointIsEqual() throws Exception {
        Point p = new Point(0,0);
        Point q = new Point(0, 0);
        assertThat(p.compareTo(q),is(0));
    }

    @Test
    public void testLowerYMakesThisLess() throws Exception {
        Point p = new Point(0,0);
        Point q = new Point(0, 1);
        assertThat(p.compareTo(q),lessThan(0));
    }

    @Test
    public void testGreaterYMakesThisGreater() throws Exception {
        Point p = new Point(0,1);
        Point q = new Point(0, 0);
        assertThat(p.compareTo(q),greaterThan(0));
    }

    @Test
    public void testOnYTieLowerXMakesThisLess() throws Exception {
        Point p = new Point(0,1);
        Point q = new Point(1, 1);
        assertThat(p.compareTo(q),lessThan(0));
    }

    @Test
    public void testOnYTieGreaterXMakesThisGreater() throws Exception {
        Point p = new Point(1,1);
        Point q = new Point(0, 1);
        assertThat(p.compareTo(q),greaterThan(0));
    }

    /*
    SLOPE-TO TESTS
     */

    @Test(expected = IllegalArgumentException.class)
    public void testNullSlopeToShouldThrowIllegalArgumentException() throws Exception {
        Point p = new Point(0, 0);
        p.slopeTo(null);
    }

    @Test
    public void testEqualPointsReturnNegativeInfinite() throws Exception {
        Point p = new Point(1, 1);
        Point q = new Point(1, 1);
        assertThat(p.slopeTo(q), is(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void testHorizontalSlopeIsZero() throws Exception {
        Point p = new Point(1, 1);
        Point q = new Point(2, 1);
        assertThat(p.slopeTo(q), is(0d));
    }

    @Test
    public void testVerticalSlopeIsPositiveInfinity() throws Exception {
        Point p = new Point(1, 1);
        Point q = new Point(1, 2);
        assertThat(p.slopeTo(q), is(Double.POSITIVE_INFINITY));
    }

    @Test
    public void testRegularSlopeCase1() throws Exception {
        Point p = new Point(1, 1);
        Point q = new Point(2, 2);
        assertThat(p.slopeTo(q), is(1d));
    }

    @Test
    public void testRegularSlopeCase2() throws Exception {
        Point p = new Point(2, 2);
        Point q = new Point(1, 1);
        assertThat(p.slopeTo(q), is(1d));
    }

    @Test
    public void testRegularSlopeCase3() throws Exception {
        Point p = new Point(2, 0);
        Point q = new Point(1, 1);
        assertThat(p.slopeTo(q), is(-1d));
    }

    /*
    SLOPE-ORDER
     */
    @Test
    public void testSlopeOrderReturnsComparator() throws Exception {
        assertThat(new Point(0, 0).slopeOrder(), notNullValue());
    }

    @Test
    public void testSlopeOrderReliesOnSlopeToOfFirstArgument() throws Exception {
        Point p = Mockito.mock(Point.class);
        Point q = Mockito.mock(Point.class);

        Point point = Mockito.mock(Point.class);
        Mockito.when(point.slopeOrder()).thenCallRealMethod();
        point.slopeOrder().compare(p, q);

        Mockito.verify(point, times(1)).slopeTo(p);
        Mockito.verify(point, times(1)).slopeTo(q);
    }

    @Test
    public void testSlopeOrderComparesSlopes() throws Exception {
        Point p = Mockito.mock(Point.class);
        Point q = Mockito.mock(Point.class);

        Point point = Mockito.mock(Point.class);
        Mockito.when(point.slopeOrder()).thenCallRealMethod();
        Mockito.when(point.slopeTo(p)).thenReturn(2d);
        Mockito.when(point.slopeTo(q)).thenReturn(3d);
        int compare = point.slopeOrder().compare(p, q);

        assertThat(compare, is(-1));
    }
}