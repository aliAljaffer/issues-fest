/**
 * Simple calculator class
 * Provides basic arithmatic operations
 */
public class Calculator {
    
    // BUG: multiply returns sum instead of product
    public int multiply(int a, int b) {
        return a + b;
    }
    
    // BUG: no zero division check
    public double divide(int a, int b) {
        return a / b;
    }
    
    public int add(int a, int b) {
        return a + b;
    }
    
    // BUG: subtract is backwards
    public int subtract(int a, int b) {
        return b - a;
    }
    
    // BUG: power function has wrong logic
    public double power(int base, int exponent) {
        return Math.pow(base, exponent);
    }
    
    // TYPO: "sqare" instead of "square"
    public double square(int number) {
        return number * number;
    }
}
