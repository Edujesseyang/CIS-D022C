package BagClient;


import java.util.Scanner;

/**
 * LongestCommonSubsequence is a program that will determine the longest string that is 
 * a subsequence of two input strings. This program applies a brute force solution technique.
 *
 * @author Charles Hoot
 * @version 4.0
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        BagInterface<String> toCheckContainer = null;

        Scanner input;
        input = new Scanner(System.in);

        System.out.println("This program determines the longest string that is a subsequence of two input string.");
        System.out.println("Please enter the first string:");
        String first = input.next();

        System.out.println("Please enter the second string:");
        String second = input.next();


        // ADD CODE HERE TO CREATE THE BAG WITH THE INITIAL STRING
        toCheckContainer = new ArrayBag<>();
        toCheckContainer.add(first);
        toCheckContainer.add(second);

        System.out.println("The strings to check are: " + toCheckContainer);
        String bestSubsequence = "";


        // ADD CODE HERE TO CHECK THE STRINGS IN THE BAG
        int longestLength = 0;
        int endIndex = 0;
        int[][] dp = new int[first.length() + 1][second.length() + 1];

        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > longestLength) {
                        longestLength = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }
        if (longestLength > 0) {
            bestSubsequence = first.substring(endIndex - longestLength, endIndex);
        }

        System.out.println("Found \"" + bestSubsequence + "\" is the longest common subsequence");

    }

    /**
     * Determine if one string is a subsequence of the other.
     *
     * @param check See if this is a subsequence of the other argument.
     * @param other The string to check against. 
     * @return A boolean if check is a subsequence of others.
     */
    public static boolean isSubsequence(String check, String other) {

        // ADD CODE HERE TO CHECK IF WE HAVE A SUBSEQUENCE
        return check.contains(other);
    }
}
