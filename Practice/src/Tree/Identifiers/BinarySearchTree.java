package Tree.Identifiers;


import java.util.Stack;

public class BinarySearchTree {
    private static class BinaryNode {
        private String identifier;
        private BinaryNode leftChild;
        private BinaryNode rightChild;

        public BinaryNode() {
        }

        public BinaryNode(String identifier) {
            this.identifier = identifier;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    private BinaryNode root;
    private int numOfEntries;

    public boolean add(String identifier) {
        if (root == null) {
            root = new BinaryNode(identifier);
            numOfEntries++;
            return true;
        }
        BinaryNode current = root;
        BinaryNode parent = null;
        while (current != null) {
            parent = root;
            int compare = identifier.compareTo(current.identifier);
            if (compare == 0) {
                return false;
            } else if (compare < 0) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        int compare = identifier.compareTo(parent.identifier);
        if (compare < 0) {
            parent.leftChild = new BinaryNode(identifier);
        } else {
            parent.rightChild = new BinaryNode(identifier);
        }
        numOfEntries++;
        return true;
    }

    public boolean contains(String identifier) {
        if (root == null) {
            return false;
        }
        BinaryNode current = root;
        while (current != null) {
            int compare = identifier.compareTo(current.identifier);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        return false;
    }

    public String remove(String identifier) {
        // locate the deleting node;
        BinaryNode current = root;
        BinaryNode parent = null;
        while (current != null) {
            int compare = identifier.compareTo(current.identifier);
            // check if same first;
            if (compare == 0) {
                break;
            }
            // then, update parent;
            parent = current;
            // find the correct path;
            if (compare < 0) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        // return null if not found;
        if (current == null) { // return if tree is empty;
            return null;
        }


        String returning = current.identifier;
        // case 1: on leaf;
        if (current.leftChild == null && current.rightChild == null) {
            if (parent == null) { // if the root is leaf;
                root = null;
            } else if (parent.leftChild == current) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
            numOfEntries--;
            return returning;
        }

        // case 2: deleting only has one child;
        if (current.leftChild == null || current.rightChild == null) { // current has only one child
            BinaryNode child = (current.leftChild == null) ? current.rightChild : current.leftChild;
            if (root == current) {
                root = child;
            } else if (parent.leftChild == current) {
                parent.leftChild = child;
            } else if (parent.rightChild == current) {
                parent.rightChild = child;
            }
            numOfEntries--;
            return returning;
        }

        // case 3: current has two children;

        // init successor and its parent;
        BinaryNode successor = current.leftChild;
        BinaryNode successorParent = current;
        while (successor.rightChild != null) {
            successorParent = successor;
            successor = successor.rightChild;
        } // found the right successor and its parent;

        if (successorParent != current) { // if there is at least one right child under successor:
            successorParent.rightChild = successor.leftChild;  // jump successor;
            successor.leftChild = current.leftChild; // replace successor's left with current's left
        }

        successor.rightChild = current.rightChild; // replace successor's right with current's right;
        // ... this step is always needed not matter is successor has right child or not.

        if (root == current) { // if current is root, replace root;
            root = successor;
        } else { // connect parent with successor;
            if (parent.leftChild == current) {
                parent.leftChild = successor;
            } else if (parent.rightChild == current) {
                parent.rightChild = successor;
            }

        }

        numOfEntries--;
        return returning;
    }


    public int getNumOfEntries() {
        return numOfEntries;
    }

    public int getHeight() {
        return getHeightRecursively(this.root);
    }

    private int getHeightRecursively(BinaryNode node) {
        if (node != null) {
            return Math.max(getHeightRecursively(node.leftChild), getHeightRecursively(node.rightChild)) + 1;
        }
        return 0;
    }

    public String toString() {
        if (root == null) {
            return "｛｝";
        }

        StringBuilder result = new StringBuilder("{");
        Stack<BinaryNode> stack = new Stack<>();
        BinaryNode current = root;

        while (current != null) {
            stack.push(current);
            current = current.leftChild;
        }

        while (!stack.isEmpty()) {
            BinaryNode temp = stack.pop();
            result.append(temp.identifier).append(", ");

            temp = temp.rightChild;
            while (temp != null) {
                stack.push(temp);
                temp = temp.leftChild;
            }
        }
        result.setLength(result.length() - 2);
        return result.append("}").toString();
    }
}
