package Hashmaps;
import java.util.HashSet;

public class extractUniqueCharecters {

	public static String uniqueChar(String str) {
        StringBuilder uniqueStr = new StringBuilder(); // To store the result
        HashSet<Character> seen = new HashSet<>(); // To keep track of characters we've already seen

        for (char c : str.toCharArray()) {
            if (!seen.contains(c)) {
                uniqueStr.append(c);
                seen.add(c);
            }
        }

        return uniqueStr.toString();
    }

    public static void main(String[] args) {
        String str = "abcabcabc";
        System.out.println(uniqueChar(str));  // Output will be "abc"
    }
}
