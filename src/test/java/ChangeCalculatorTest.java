import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ChangeCalculatorTest {

    private static ChangeCalculator testCalculator;

    @BeforeAll
    public static void setup(){
        testCalculator = new ChangeCalculator(1,2,3,4,5);
    }

    @Test
    public void testChange1(){
        int[] expected = {1,0,0,1,1};
        int[] actual = testCalculator.findChange(23);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testChange2(){
        int[] expected = {0,1,1,1,0};
        int[] actual = testCalculator.findChange(17);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testChange3(){
        int[] expected = {1,2,1,2,0};
        int[] actual = testCalculator.findChange(49);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testChange4(){
        int[] expected = {0,0,1,1,0};
        int[] actual = testCalculator.findChange(7);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testChange5(){
        int[] expected = null;
        int[] actual = testCalculator.findChange(73);
        assertArrayEquals(expected, actual);
    }
}
