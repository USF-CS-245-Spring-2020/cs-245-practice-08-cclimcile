public class BST<T extends Comparable<T>>{

    private BSTNode root;

    private class BSTNode {
        T data;
        BSTNode right;
        BSTNode left;

        public BSTNode(T some_data){
            data = some_data;
            right = left = null;
        }
    }

    /* function find implementation */
    public boolean find(T key){
        return find(root, key);
    }

    protected boolean find(BSTNode node, T key){
        /* key DNE */
        if(node == null)
            return false;
        if(key.compareTo(node.data) == 0){
            return true;
        } else if(key.compareTo(node.data) < 0){
            return find(node.left, key);
        } else {
            return find(node.right, key);
        }
    }

    /* function insert implementation */
    public void insert (T key) {
        root = insert(root, key);
    }

    protected BSTNode insert(BSTNode node, T key) {
        if(node == null)
            return new BSTNode(key);
        if(key.compareTo(node.data) < 0){
            node.left = insert(node.left, key);
        } else {
            node.right = insert(node.right, key);
        }
        return node;
    }

    /* function print-inOrderTraversal implementation */
    public void print() {
        print(root);
    }

    protected void print(BSTNode node) {
        if(node != null){
            /* if there’s no child to the left, it’ll return to the parent */
            print(node.left);
            System.out.print(node.data);
            print(node.right);
        }
    }

    /* function delete implementation */
    public void delete(T key) {
        root = delete(root, key);
    }

    protected BSTNode delete(BSTNode node, T key) {
        /* node DNE */
        if(node == null)
            return null;
        if(key.compareTo(node.data) > 0){
            node.right = delete(node.right, key);
            return node;
        } else if(key.compareTo(node.data) < 0){
            node.left = delete(node.left, key);
            return node;
        } else {
            /* if key == node.data */
            if(node.left == null) {
                /* case where there is no left child/ it is a leaf */
                return node.right;
            } else if (node.right == null) {
                /* if no child, this is called and now the link is undone
                 * since node.left == null | if there is a child then it gets promoted */
                return node.left;
            } else {
                /* case where there is 2 children */
                if(node.right.left == null){
                    node.data = node.right.data;
                    /* we need to change structure of tree */
                    node.right = node.right.right;
                } else {
                    /* finds the in-order successor */
                    node.data = removeSmallest(node); // might be node.right
                }
            }
            return node; //not sure
        }

    }

    T removeSmallest(BSTNode node) {
        if(node.left.left == null){
        /* essentially ‘swaps’ left with right */
            T smallest = node.left.data;
            node.left = node.left.right;
            return smallest;
        } else {
            return removeSmallest(node.left);
        }
    }

}
