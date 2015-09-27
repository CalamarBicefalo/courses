import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * @author jose
 * @since 27/09/15
 */
public class PointTest {

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

}