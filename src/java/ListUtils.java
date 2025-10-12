import java.util.*;

/**
 * Utility class for list operatons
 */
public class ListUtils {
    
    // BUG: doesn't handle empty list
    public static int findMaximum(List<Integer> list) {
        int max = list.get(0);
        for (int num : list) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
    
    // BUG: doesn't preserve order
    public static <T> List<T> removeDuplicates(List<T> list) {
        return new ArrayList<>(new HashSet<>(list));
    }
    
    // TYPO: "avarege" instead of "average"
    public static double avarege(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum / list.size();
    }
    
    // BUG: integer division in average loses precision
    public static double calculateAverage(List<Integer> numbers) {
        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        return sum / numbers.size();
    }
    
    // BUG: doesn't handle negative chunk size
    public static <T> List<List<T>> chunkList(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunkSize) {
            chunks.add(list.subList(i, Math.min(i + chunkSize, list.size())));
        }
        return chunks;
    }
}
