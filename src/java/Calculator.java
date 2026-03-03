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
    // ANS: added the zero handling case and typecasted the return value according to the return type
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return (double) a / b;
    }
    
    public int add(int a, int b) {
        return a + b;
    }
    
    // BUG: subtract is backwards
    public int subtract(int a, int b) {
        return a - b;
    }
    
    //BUG: calling the in built power function in the user defined one makes no sense 
    public double power(double base, int exponent) {
        return Math.pow(base, exponent);
    }
    
    // TYPO: "sqare" instead of "square"
    public double square(int number) {
        return number * number;
    }
}
