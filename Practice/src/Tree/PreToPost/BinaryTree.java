package Tree.PreToPost;

public class BinaryTree<T> {
    private class Node {
        private T data;
        private BinaryTree<T> left;
        private BinaryTree<T> right;

        public Node(T data) {
            this.data = data;
        }

        public BinaryTree<T> getLeft() {
            return left;
        }

        public void setLeft(BinaryTree<T> left) {
            this.left = left;
        }

        public BinaryTree<T> getRight() {
            return right;
        }

        public void setRight(BinaryTree<T> right) {
            this.right = right;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

    }

    private Node root;

    public BinaryTree(T data) {
        root = new Node(data);
    }

    public T getRootDate() {
        return root.data;
    }

    public void setLeft(BinaryTree<T> left) {
        root.left = left;
    }

    public void setRight(BinaryTree<T> right) {
        root.right = right;
    }

    public BinaryTree<T> getLeft() {
        return root.left;
    }

    public BinaryTree<T> getRight() {
        return root.right;
    }

}
