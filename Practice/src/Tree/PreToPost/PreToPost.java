package Tree.PreToPost;

import java.util.*;

/**
 * PreToPost will take an expression in prefix notation and convert it into postfix using
 * a binary tree.
 *
 * @author Charles Hoot
 * @version 4.0
 */


public class PreToPost {

    public static void main(String[] args) {
        BinaryTree<String> expression = null;

        String toParse = getExpressionString();
        System.out.println(toParse);

        Scanner scString = new Scanner(toParse);

        BinaryTree<String> resultTree = getTree(scString);
        printPostOrder(resultTree);

    }

    private static void printPostOrder(BinaryTree<String> tree) {
        if (tree == null) {
            return;
        }
        printPostOrder(tree.getLeft());
        printPostOrder(tree.getRight());
        System.out.print( tree.getRootDate() + " ");
    }

    /**
     * Get the tree from the pre-order expression tokens recursively
     *
     * @param expressionScanner a Scanner that holds the tokens in pre-order
     * @return a Binary tree hold the expression
     */
    private static BinaryTree<String> getTree(Scanner expressionScanner) {
        if (!expressionScanner.hasNext()) {
            return null;
        }

        String token = expressionScanner.next();

        BinaryTree<String> result = new BinaryTree<>(token);
        if (isOperator(token)) {
            result.setLeft(getTree(expressionScanner));
            result.setRight(getTree(expressionScanner));
        }
        return result;

    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }


    /**
     * getExpressionString - Get the string with the expression.
     *
     * @return A String from the keyboard.
     */
    private static String getExpressionString() {
        Scanner input;
        String inString = "";
        try {
            input = new Scanner(System.in);

            System.out.println("Please enter a prefix expression");
            inString = input.nextLine().trim();
        } catch (Exception e) {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use the expression + a b / c");
            inString = "+ a b / c";
        }

        return inString;

    }

}
