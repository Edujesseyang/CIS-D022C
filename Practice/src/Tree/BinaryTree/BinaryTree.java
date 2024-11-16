package Tree.BinaryTree;


import java.util.*;


public class BinaryTree<T> implements BinaryTreeInterface<T> {
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

    }


    private TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(T data) {
        root = new TreeNode(data);
    }

    public BinaryTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
        root = new TreeNode(data);
        if (left != null) {
            root.left = left.root;
        }
        if (right != null) {
            root.right = right.root;
        }
    }


    /**
     * Sets this binary tree to a new one-node binary tree.
     *
     * @param rootData The object that is the data for the new tree's root.
     */
    @Override
    public void setTree(T rootData) {
        this.root = new TreeNode(rootData);
    }


    /**
     * Sets this binary tree to a new binary tree.
     *
     * @param rootData  The object that is the data for the new tree's root.
     * @param leftTree  The left subtree of the new tree.
     * @param rightTree The right subtree of the new tree.
     */
    @Override
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {

        // create root node
        root = new TreeNode(rootData);

        // connect left
        if (leftTree instanceof BinaryTree) {
            root.left = ((BinaryTree<T>) leftTree).root;
        } else {
            root.left = null;
        }

        // connect right
        if (rightTree instanceof BinaryTree) {
            root.right = ((BinaryTree<T>) rightTree).root;
        } else {
            root.right = null;
        }


    }

    public TreeNode getRoot() {
        return this.root;
    }

    @Override
    public T getRootData() {
        return root.data;
    }

    @Override
    public int getHeight() {
        int height = 0;
        TreeNode current = root;
        while (current != null) {
            current = current.left;
            height++;
        }
        return height;
    }

    @Override
    public int getNumberOfNodes() {
        if (root == null) {
            return 0;
        }
        int numOfNodes = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            numOfNodes++;
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return numOfNodes;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
    }


    private class TreeIterator implements TreeIteratorInterface<T> {


        @Override
        public Iterator<T> getPreorderIterator() {
            return new Iterator<>() {
                private final Stack<TreeNode> stack = new Stack<>();

                { // init add root to stack
                    if (root != null) {
                        stack.push(root);
                    }
                }

                @Override
                public boolean hasNext() {
                    return !stack.isEmpty();
                }

                @Override
                public T next() {
                    if (stack.isEmpty()) { // throw error if no more element
                        throw new NoSuchElementException("no more element");
                    }

                    // pop the top, set it as current
                    TreeNode current = stack.pop();

                    // check right side first, if there is right child, push to stack
                    if (current.right != null) {
                        stack.push(current.right);
                    }
                    // the left,
                    if (current.left != null) {
                        stack.push(current.left);
                    }

                    return current.data;
                }
            };
        }

        @Override
        public Iterator<T> getPostorderIterator() {
            return new Iterator<>() {
                private final Stack<TreeNode> stack = new Stack<>();
                private TreeNode lastVisited = null;

                { // init stack, push the far-left path in
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
                    while (!stack.isEmpty()) { // if stack has elements
                        TreeNode current = stack.peek(); // set current the lowest left leaf

                        // if current has right child and is not the last visited node
                        if (current.right != null && current.right != lastVisited) {
                            TreeNode temp = current.right; // set its right child as temp

                            while (temp != null) { // push all temp's far-left path in stack
                                stack.push(temp);
                                temp = temp.left;
                            }
                        } else { // if current has not right child, or it is the last visited
                            stack.pop(); // delete the top of stack.
                            lastVisited = current; // set last visited as current
                            return current.data; // return its data
                        }
                    }
                    // throw exception if stack is empty
                    throw new NoSuchElementException("No more element");
                }
            };
        }

        @Override
        public Iterator<T> getInorderIterator() {
            return new Iterator<>() {
                private final Stack<TreeNode> stack = new Stack<>();

                { // init the stack, push all left in the stack
                    TreeNode current = root;
                    while (current != null) {
                        stack.push(current);
                        current = current.left;
                    }
                }

                @Override
                public boolean hasNext() {
                    // if stack is empty, means there is not more entry
                    return !stack.isEmpty();
                }

                @Override
                public T next() {
                    // if the stack is empty, pop no more element
                    if (stack.isEmpty()) {
                        throw new NoSuchElementException("no more element");
                    }
                    // pop the lowest left
                    TreeNode current = stack.pop();

                    // push all right in the stack
                    if (current.right != null) {
                        TreeNode temp = current.right;
                        while (temp != null) {
                            stack.push(temp);
                            temp = temp.left;  // key, after pushed, update temp to left child.
                        }
                    }
                    return current.data;
                }
            };
        }

        @Override
        public Iterator<T> getLevelOrderIterator() {
            return new Iterator<>() {
                private final Queue<TreeNode> queue = new LinkedList<>();

                { // init a queue, add root to the queue
                    if (root != null) {
                        queue.add(root);
                    }
                }

                @Override
                public boolean hasNext() {
                    return !queue.isEmpty();
                }

                @Override
                public T next() {
                    if (queue.isEmpty()) {
                        throw new NoSuchElementException("no more element");
                    }

                    TreeNode current = queue.poll();

                    if (current.left != null) {
                        queue.add(current.left);
                    }
                    if (current.right != null) {
                        queue.add(current.right);
                    }
                    return current.data;
                }
            };
        }
    }

    @Override
    public Iterator<T> getPreorderIterator() {
        TreeIteratorInterface<T> iterator = new TreeIterator();
        return iterator.getPreorderIterator();
    }

    @Override
    public Iterator<T> getPostorderIterator() {
        TreeIteratorInterface<T> iterator = new TreeIterator();
        return iterator.getPostorderIterator();
    }

    @Override
    public Iterator<T> getInorderIterator() {
        TreeIteratorInterface<T> iterator = new TreeIterator();
        return iterator.getInorderIterator();
    }

    @Override
    public Iterator<T> getLevelOrderIterator() {
        TreeIteratorInterface<T> iterator = new TreeIterator();
        return iterator.getLevelOrderIterator();
    }

    public void iterativePreorderTraverse() {
        if (this.isEmpty()) {
            System.out.println("Tree is empty");
            return;
        }
        Iterator<T> iterator = this.getPreorderIterator();
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            result.append(iterator.next()).append(", ");
        }
        if (!result.isEmpty()) {
            result.setLength(result.length() - 2);
        }
        System.out.println(result);
    }

    public void iterativeInorderTraverse() {
        if (this.isEmpty()) {
            System.out.println("Tree is empty");
            return;
        }
        Iterator<T> iterator = this.getInorderIterator();
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            result.append(iterator.next()).append(", ");
        }
        if (!result.isEmpty()) {
            result.setLength(result.length() - 2);
        }
        System.out.println(result);
    }


}
