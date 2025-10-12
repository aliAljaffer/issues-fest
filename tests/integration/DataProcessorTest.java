import java.util.*;

/**
 * Tests for data procesing functionality
 */
public class DataProcessorTest {

    // Test will fail - calculateMean crashes on empty list
    public void testMeanWithEmptyList() {
        DataProcessor processor = new DataProcessor();
        List<Double> empty = new ArrayList<>();
        double mean = processor.calculateMean(empty);
    }

    // TYPO: "testMedain" instead of "testMedian"
    public void testMedain() {
        DataProcessor processor = new DataProcessor();
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0);
        // Will fail for even-length lists
        double median = processor.medain(numbers);
    }
}
