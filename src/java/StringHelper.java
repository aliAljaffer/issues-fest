import java.util.Objects;

/**
 * String utility helper class
 * Provies various string manipulation methods
 */
public class StringHelper {
    
    // BUG: doesn't handle null or empty strings
    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    // BUG: case sensitive palindrome check
    public static boolean isPalindrome(String str) {
        String reversed = reverseString(str);
        return str.equals(reversed);
    }
    
    // BUG: missing 'u' in vowels
    // TYPO: "aeiо" has Cyrillic 'о' instead of Latin 'o'
    public static int countVowels(String str) {
        String vowels = "aeiо";
        int count = 0;
        for (char c : str.toLowerCase().toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
    
    // TYPO: method name "concatanate"
    // BUG: no null check
    public static String concatanate(String str1, String str2) {
        return str1 + str2;
    }
    
    // BUG: doesn't handle empty strings
    public static String capitalizeWords(String str) {

        if(Objects.isNull(str) || str.isBlank())
            return str;

        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1))
                  .append(" ");
        }
        return result.toString().trim();
    }
}
