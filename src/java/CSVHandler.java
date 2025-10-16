import java.io.*;
import java.util.*;

/**
 * CSV file handeling utilities
 * Provides parsing and writing capabilities for CSV files
 */
public class CSVHandler {

    // BUG: Doesn't handle quoted fields with commas
    // BUG: BufferedReader never closed - RESOURCE LEAK
    public List<String[]> parseCSV(String filepath) throws IOException {
        List<String[]> records = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        while ((line = reader.readLine()) != null) {
            records.add(line.split(","));
        }
        // BUG: Reader never closed
        return records;
    }

    // TYPO: "wirteCSV" instead of "writeCSV"
    // BUG: FileWriter never closed - RESOURCE LEAK
    public void writeCSV(String filepath, List<String[]> records) throws IOException {
        FileWriter writer = new FileWriter(filepath);
        for (String[] record : records) {
            writer.write(String.join(",", record) + "\n");
        }
        // BUG: Writer never closed
    }

    // BUG: Doesn't validate column index
    // BUG: Will throw IndexOutOfBoundsException for invalid index
    public List<String> getColumn(List<String[]> records, int columnIndex) {
        List<String> column = new ArrayList<>();
        for (String[] record : records) {
            column.add(record[columnIndex]);
        }
        return column;
    }

    // TYPO: "getHeaderr" instead of "getHeader" (double 'r')
    // BUG: BufferedReader never closed - RESOURCE LEAK
    public String[] getHeader(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String firstLine = reader.readLine();
        // BUG: Reader never closed
        return firstLine.split(",");
    }

    // BUG: Doesn't handle missing columns
    public List<String[]> filterRows(List<String[]> records, int columnIndex, String value) {
        List<String[]> filtered = new ArrayList<>();
        for (String[] record : records) {
            if (record[columnIndex].equals(value)) {
                filtered.add(record);
            }
        }
        return filtered;
    }

    // TYPO: "getRowCountt" instead of "getRowCount" (double 't')
    public int getRowCountt(String filepath) throws IOException {
        return parseCSV(filepath).size();
    }
}
