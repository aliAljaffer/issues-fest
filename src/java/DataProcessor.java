import java.io.*;
import java.util.*;

/**
 * Data processing and transformaton utilities
 * Handles file I/O and statistical calculations
 */
public class DataProcessor {

    // BUG: No error handling for file operations
    // BUG: BufferedReader never closed - RESOURCE LEAK
    public List<String> readFile(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        // BUG: Reader never closed
        return lines;
    }

    // TYPO: "wirte" instead of "write"
    // BUG: FileWriter never closed - RESOURCE LEAK
    public void wirteFile(String filepath, List<String> data) throws IOException {
        FileWriter writer = new FileWriter(filepath);
        for (String line : data) {
            writer.write(line + "\n");
        }
        // BUG: Writer never closed
    }

    // BUG: Doesn't handle empty lists - Division by zero
    // BUG: Returns 0 for empty list without indication
    public double calculateMean(List<Double> numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum / numbers.size();
    }

    // TYPO: "medain" instead of "median"
    // BUG: Doesn't handle even-length lists correctly
    // BUG: Doesn't handle empty lists
    public double median(List<Double> numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        Collections.sort(numbers);
        if (numbers.size() % 2 == 0) {
            return(numbers.get(numbers.size() / 2 - 1) + numbers.get(numbers.size() / 2)) / 2;
        } else {
            return numbers.get(numbers.size() / 2);
        }
    }

    // BUG: No validation for chunk size (negative or zero)
    public List<List<String>> splitData(List<String> data, int chunkSize) {
        List<List<String>> chunks = new ArrayList<>();
        for (int i = 0; i < data.size(); i += chunkSize) {
            chunks.add(data.subList(i, Math.min(i + chunkSize, data.size())));
        }
        return chunks;
    }

    // BUG: Doesn't handle empty list
    public double calculateStandardDeviation(List<Double> numbers) {
        double mean = calculateMean(numbers);
        double sumSquares = 0;
        for (double num : numbers) {
            sumSquares += Math.pow(num - mean, 2);
        }
        return Math.sqrt(sumSquares / numbers.size());
    }

    // TYPO: "readLins" instead of "readLines"
    public int readLins(String filepath) throws IOException {
        return readFile(filepath).size();
    }
}
