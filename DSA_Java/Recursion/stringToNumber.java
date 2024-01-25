package Recursion;

public class stringToNumber {
	public static int convertToNum(String str) {
        return convertToNumberRecursive(str, 0);
    }

    public static int convertToNumberRecursive(String str, int index) {
        if (index == str.length()) {
            return 0;
        }

        int digit = str.charAt(index) - '0';
        int multiplier = (int) Math.pow(10, str.length() - 1 - index);
        int remainingNumber = convertToNumberRecursive(str, index + 1);

        return digit * multiplier + remainingNumber;
    }

    public static void main(String[] args) {
        String str = "12345";
        int number = convertToNum(str);
        System.out.println("Converted number: " + number);
    }
}