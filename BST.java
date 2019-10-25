
public class BST<T> {

    public class Node {
        private Comparable data;
        private Node right, left;
        
        public Node(Comparable data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Comparable getData() { return this.data; }
        public Node getRight() { return this.right; }
        public Node getLeft() { return this.left; }
        
        public void setData(Comparable item) { this.data = item; }
        public void setRight(Node n) { this.right = n; }
        public void setLeft(Node n) { this.left = n; }
    }

    private Node root;

    public BST() {
        root = null;
    }


    public boolean find(Comparable item) {
        return find(item, root);
    }

    public void insert(Comparable item) {
        if (root == null) {
            root = new Node(item, null, null);
        }
        insert(item, root);
    }

    public void delete(Comparable item) {
        root = delete(item, root);
    }
    
    private boolean find(Comparable item, Node node) {
        if (node == null) {
            return false;
        }
        if (node.getData().compareTo(item) == 0) {
            return true;
        } else if (node.getData().compareTo(item) < 0) {
            return find(item, node.right);
        } else {
            return find(item, node.left);
        }
    }

    private void insert(Comparable item, Node node) {
        if (node.getData().compareTo(item) < 0) {
            if (node.getRight() == null) {
                node.setRight(new Node(item, null, null));
            } else {
                insert(item, node.getRight());
            }
        } else {
            if (node.getLeft() == null) {
                node.setLeft(new Node(item, null, null));
            } else {
                insert(item, node.left);
            }
        }
    }
    /*
    private Node delete(Comparable item, Node node) {
        if (node == null) {
            return null;
        }
        if (node.getData().compareTo(item) == 0) {
            // removal if one or no children
            if (node.getRight() == null && node.getLeft() == null) {
                return null;
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else {
                // if two children
                Node replacement = smallestNode(node.getRight());
                node.setData(replacement.getData());

                node.setRight(delete(replacement.getData(), node.getRight()));
                return node;
            }
        } else if(node.getData().compareTo(item) < 0) {
            // need to go to right subtree
            node.setRight(delete(item, node.left));
            return node;
        } else {
            // go to left subtree
            node.setLeft(delete(item, node.right));
            return node;
        }
    }
    */
    private Node delete(Comparable item, Node node) {
        if (node == null) {
            return null;
        } else if (node.getData().equals(item)) {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else if (node.getRight().getLeft() == null) {
                node.setData(node.getRight().getData());
                node.setRight(node.getRight().getRight());
                return node;
            } else {
                node.setData(smallestNode(node.getRight()).getData());
                return node;
            }
        } else if (node.getData().compareTo(item) < 0) {
            node.setLeft(delete(item, node.getLeft()));
        } else {
            node.setRight(delete(item, node.getRight()));
        }
        return node;
    }

    private Node smallestNode(Node n) {
        if (n.left == null) { return n; }
        else { return smallestNode(n.getLeft()); }
    }

    public void print() {
        System.out.print("Pre Order Tree:\n[ ");
        inOrder(root);
        System.out.print(" ]\n");
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.getData() + " ");
            inOrder(node.getRight());
        }
    }

    public Node getRoot() {
        return root;
    }

}