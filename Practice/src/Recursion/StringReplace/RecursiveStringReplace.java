package Recursion.StringReplace;

/**
 * A class that has a methond to recursively replaces characters in a String.
 *
 * @author Charles Hoot
 * @version 4.0
 */
public class RecursiveStringReplace {

    /**
     * replace - Replace all instances of one character by another.
     *
     * @param  s   The string to do the replacement on.
     * @param  from   The character to be replaced.
     * @param  to   the character to change to.
     * @return A new string with the appropriate characters replaced.
     */
    public static String replace(String s, char from, char to) {
        // base case
        if (s == null || s.isEmpty()) {
            return "";
        }
        // replace first char is it match
        char firstChar = (s.charAt(0) == from) ? to : s.charAt(0);
        // keep returning firstChar + recursive call with param substring.
        return firstChar + replace(s.substring(1), from, to);
    }

    public static void main(String[] args) {
        String test1 = "Hello, world.";
        String test2 = "How are you?";
        String test3 = "I am recursive string replacement!!!";

        System.out.println(replace(test1, 'l', '1'));
        System.out.println(replace(test2, ' ', '_'));
        System.out.println(replace(test3, 'r', 'R'));

    }

}
