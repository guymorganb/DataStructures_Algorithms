package DynamicPrograming;

public class editStringDistance {

	public static int editDistance(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // Base cases
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (s.charAt(i-1) == t.charAt(j-1)) {
                    // If the characters match, no operation is needed
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // Else take the minimum of the three operations
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],  // Replace
                                    Math.min(dp[i][j-1],  // Insert
                                             dp[i-1][j])); // Delete
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "saturday";
        String t = "sunday";
        System.out.println(editDistance(s, t));
    }
}
