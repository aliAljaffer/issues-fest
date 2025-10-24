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
        return a - b;
    }
    
    //BUG: calling the in built power function in the user defined one makes no sense 
    public double power(double base, int exponent) {
        if(exponent>0){
            return powerHelper(base, exponent);
        }
        if(exponent==0){
            return 1.0;
        }
        else {
            if (exponent == Integer.MIN_VALUE) {
                return 1.0/(base * powerHelper(base, Integer.MAX_VALUE));
            } else {
                return 1.0 /powerHelper(base, -exponent);
            }
        }
    }
    double powerHelper(double base, int exponent) {
        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result = result * base;
        }
        return result;
    }
    
    // TYPO: "sqare" instead of "square"
    public double square(int number) {
        return number * number;
    }
}
