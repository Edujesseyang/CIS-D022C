package Tree.BinarySearchTree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<? super T>> implements SearchTreeInterface<T> {
    private class TreeNode {
        private T data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode() {

        }

        public TreeNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

    }

    private int numOfEntries;
    private TreeNode root;


    public BinarySearchTree() {
        this.root = null;
        numOfEntries = 0;
    }

    /**
     * Searches for a specific entry in this tree.
     *
     * @param entry An object to be found.
     * @return True if the object was found in the tree.
     */
    @Override
    public boolean contains(T entry) {
        if (root == null) {
            return false;
        }
        TreeNode current = root;
        while (current != null) {
            int compression = current.data.compareTo(entry);
            if (compression == 0) {
                return true;
            } else if (compression > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    /**
     * Retrieves a specific entry in this tree.
     *
     * @param entry An object to be found.
     * @return Either the object that was found in the tree or
     * null if no such object exists.
     */
    @Override
    public T getEntry(T entry) {
        if (root == null) {
            return null;
        }
        TreeNode current = root;
        while (current != null) {
            int compression = current.data.compareTo(entry);
            if (compression == 0) {
                return current.data;
            } else if (compression > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    /**
     * Adds a new entry to this tree, if it does not match an existing
     * object in the tree. Otherwise, replaces the existing object with
     * the new entry.
     *
     * @param newEntry An object to be added to the tree.
     * @return Either null if newEntry was not in the tree already, or
     * the existing entry that matched the parameter newEntry
     * and has been replaced in the tree.
     */
    @Override
    public T add(T newEntry) {
        // create a new node
        TreeNode newNode = new TreeNode(newEntry);
        // handle the case if the tree is empty
        if (root == null) {
            root = newNode;
            numOfEntries++;
        }
        // define current and parent
        TreeNode current = root;
        TreeNode parent = null;

        // iterate nodes,
        while (current != null) {
            // compare current to newEntry
            int compare = current.data.compareTo(newEntry);
            // if same, return oldEntry's data
            if (compare == 0) {
                T returnData = current.data;
                current.setData(newEntry);
                return returnData;
            }
            // set parent before update current.
            parent = current;
            // update current, if newEntry is smaller, current goes left, vise versa
            if (compare > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // fide the available spot, compare current, if new is smaller, put it to left.
        if (parent.data.compareTo(newEntry) > 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        // update entries count
        numOfEntries++;
        return null;
    }

    /**
     * Removes a specific entry from this tree.
     *
     * @param entry An object to be removed.
     * @return Either the object that was removed from the tree or
     * null if no such object exists.
     */
    @Override
    public T remove(T entry) {
        // case 1: empty tree.
        if (root == null) {
            return null;
        }
        // spot the right position.
        TreeNode current = root;
        TreeNode parent = null;
        while (current != null) {
            int compare = current.data.compareTo(entry);
            if (compare == 0) {
                break;
            }
            parent = current;
            if (compare > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // store the returning data, or return null if didn't find.
        if (current == null) {
            return null;
        }
        T returning = current.data;

        // check the status of current.
        int numOfChildren = 0;
        if (current.left != null) {
            numOfChildren++;
        }
        if (current.right != null) {
            numOfChildren++;
        }

        // case 2: deleting node is on a leaf.
        if (numOfChildren == 0) {
            if (current == root) {
                root = null;
            } else if (parent.left == current) {
                parent.left = null;
            } else if (parent.right == current) {
                parent.right = null;
            }
            numOfEntries--;
            return returning;
        }

        // case 3: deleting node has only one child.
        if (numOfChildren == 1) {
            TreeNode child = (current.left != null) ? current.left : current.right;
            if (current == root) {
                root = child;
            } else if (parent.left == current) {
                parent.left = child;
            } else if (parent.right == current) {
                parent.right = child;
            }
            numOfEntries--;
            return returning;
        }


        // case 4: deleting node has two children.
        // find in-order successor
        TreeNode successorParent = current;
        TreeNode successor = current.right;
        while (successor.left != null) {
            successorParent = successor;
            successor = successor.left;
        }

        // replace current's data with successor's data.
        current.setData(successor.data);

        // remove the successor.
        if (successorParent.left == successor) {
            successorParent.left = successor.right;
        } else if (successorParent.right == successor) {
            successorParent.right = successor.right;
        }
        numOfEntries--;
        return returning;
    }

    /**
     * Creates an iterator that traverses all entries in this tree.
     *
     * @return An iterator that provides sequential and ordered access
     * to the entries in the tree.
     */
    @Override
    public Iterator<T> getInorderIterator() {
        return new Iterator<>() {
            final Stack<TreeNode> stack = new Stack<>();

            { // push all left in stack
                TreeNode current = root;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the iterator");
                }
                // pop the top one, which is lowest and left one.
                TreeNode current = stack.pop();
                T result = current.data;

                // update current if it has right
                if (current.right != null) {
                    current = current.right;
                    // add all left from there
                    while (current != null) {
                        stack.add(current);
                        current = current.left;
                    }
                }
                return result;
            }
        };
    }

    @Override
    public T getRootData() {
        if (root == null) {
            return null;
        }
        return root.data;
    }

    @Override
    public int getHeight() {
        return getHeightRecursively(root);
    }

    private int getHeightRecursively(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeightRecursively(node.left);
        int rightHeight = getHeightRecursively(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    public int getNumberOfNodes() {
        return numOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
        numOfEntries = 0;
    }
}
