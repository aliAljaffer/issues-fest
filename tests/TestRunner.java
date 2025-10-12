// Simple test runner
public class TestRunner {
    private int passedTests = 0;
    private int failedTests = 0;
    
    public void runTest(String testName, boolean result) {
        if (result) {
            passedTests++;
            System.out.println("✓ " + testName + " passed");
        } else {
            failedTests++;
            System.out.println("✗ " + testName + " failed");
        }
    }
    
    public void printSummary() {
        System.out.println("Tests passed: " + passedTests);
        System.out.println("Tests failed: " + failedTests);
    }
}

    // TYPO: "reportt" instead of "report"
    public String reportt() {
        return "Passed: " + passedTests + ", Failed: " + failedTests;
    }
