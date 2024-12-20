package Tree.Identifiers;

import java.io.*;
import java.util.*;

/**
 * Find the unique identifiers from a .java file.
 *
 * @author Charles Hoot
 * @version 4.0
 */

public class Identifiers {

    public static void main(String[] args) {


        String fileName = getFileName();
        System.out.println();

        BinarySearchTree unique = getPossibleIds(fileName);

        // ADD CODE HERE FOR PRINTING THE IDENTIFIERS
        String printOut = unique.toString();
        System.out.println(printOut);
    }

    /**
     * Get the possible identifiers from the file.
     *
     * @return A tree of possible identifiers from the file.
     */
    private static BinarySearchTree getPossibleIds(String theFileName) {
        Scanner input;
        String inString = "";
        BinarySearchTree possible = new BinarySearchTree();

        try {
            input = new Scanner(new File(theFileName));

            //ADD CODE TO READ THE FILE AND CONSTRUCT THE BINARY SEARCH TREE
            while (input.hasNext()) {
                String word = input.next().replaceAll("[^a-zA-Z]", "");
                if (!word.isEmpty()) {
                    possible.add(word);
                }
            }
        } catch (IOException e) {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
        }

        return possible;
    }

    private static String getFileName() {
        Scanner input;
        String inString = "data.txt";

        try {
            input = new Scanner(System.in);

            System.out.println("Please enter the name of the file:");
            inString = input.next();
        } catch (Exception e) {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will try the default file name data.txt");
        }

        return inString;
    }
}
