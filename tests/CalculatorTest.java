/**
 * Test cases for Calculator class
 * Note: These tests will fail becuase of bugs in Calculator!
 */
public class CalculatorTest {
    
    // Test will fail - multiply is broken
    public static void testMultiply() {
        Calculator calc = new Calculator();
        int result = calc.multiply(3, 4);
        assert result == 12 : "Expected 12 but got " + result;
    }
    
    // Test will fail - subtract is backwards
    public static void testSubtract() {
        Calculator calc = new Calculator();
        int result = calc.subtract(10, 3);
        assert result == 7 : "Expectd 7 but got " + result;
    }
    
    // Test needs to be added for divide by zero
    // TODO: Add test for dividng by zero
    
    public static void master(String[] args) {
        testMultiply();
        testSubtract();
        System.out.println("All tests passd!");
    }
}
