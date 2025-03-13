import com.itacademy.aqa.Fraction;
import org.junit.*;

public class FractionTest {

@BeforeClass
public static void testBeforeClass(){
    System.out.println("The set of tests is started");
}

    @Before
    public void testBefore(){
        System.out.println("Test is started");
    }

    @Test
    public void testAdd() {
        Fraction fraction = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(3, 4);

        Assert.assertEquals("The result of the sum is not correct", "5/4", fraction.add(fraction2));
    }

    @Test
    public void testMultiply() {
        Fraction fraction = new Fraction(1, 2);

        Assert.assertEquals("The result of the multiplying is not correct", "11/10", fraction.multiply(2.2d));
    }

    @Test
    public void testDivide() {
        Fraction fraction = new Fraction(1, 2);

        Assert.assertEquals("The result of the division is not correct", "5/21", fraction.divide(2.1d));

    }

    @After
    public void testAfter(){
        System.out.println("Test is completed");
    }

    @AfterClass
    public static void testAfterClass(){
        System.out.println("The running of all tests is completed");
    }
}
