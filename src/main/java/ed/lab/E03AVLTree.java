package ed.lab;

import java.util.Comparator;

public class E03AVLTree<T> {

    private TreeNode<T> root;
    private final Comparator<T> comparator;

    public E03AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
    } //this.root = null;


        public int high(TreeNode<T> root) {
            if(root != null) {
                int Left = high(root.left);
                int Right = high(root.right);
                if(Left > Right)
                    return Left +1;
                else
                    return Right + 1;
            }
            else
                return 0;
        }

        public int Balancefactor(TreeNode<T> root) {
            if(root == null)
                return 0;

            return high(root.left) - high(root.right);
        }

        private TreeNode<T> rightRotate(TreeNode<T> root) {
            if(root.left == null)
                return root;

            TreeNode<T> nodeleft = root.left;
            TreeNode<T> Aux = nodeleft.right;

            nodeleft.right = root;
            root.left = Aux;

            return nodeleft;
        }

        private TreeNode<T> leftRotate(TreeNode<T> root) {
            if(root.right == null)
                return root;

            TreeNode<T> noderight = root.right;
            TreeNode<T> Aux = noderight.left;

            noderight.left = root;
            root.right = Aux;

            return noderight;
        }

        public TreeNode<T> balanceBST(TreeNode<T> root) {
            if(root == null)
                return root;

            int balance = Balancefactor(root);
            if(balance > 1) {
                if(Balancefactor(root.left) > 0) {
                    root = rightRotate(root);
                }
                else {
                    root.left = leftRotate(root.left);
                    root = rightRotate(root);
                }
            }
            else if(balance < -1){
                if(Balancefactor(root.right) < 0) {
                    root = leftRotate(root);
                }
                else {
                    root.right = rightRotate(root.right);
                    root = leftRotate(root);
                }
            }

            return root;
        }


    public void insert(T value) {
        root = insert(root, value);
    }

    private TreeNode<T> insert(TreeNode<T> root, T value) {
        if (root == null)
            return new TreeNode<>(value);
        if(root.value == value)
            return root;

        if (comparator.compare(value, root.value) < 0) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }

        root = balanceBST(root);
        return root;
    }




    public void delete(T value) {
        root = delete(root, value);
    }

    private TreeNode<T> delete(TreeNode<T> root, T value) {
        if (root == null)
            return null;

        if (comparator.compare(value, root.value) < 0) {
            root.left = delete(root.left, value);
        }
        else if (comparator.compare(value, root.value) > 0) {
            root.right = delete(root.right, value);
        }
        else {
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;

            root.value = findMin(root.right).value;
            root.right = delete(root.right, root.value);
        }
        root = balanceBST(root);
        return root;
    }

    private TreeNode<T> findMin(TreeNode<T> root) {
        if (root == null)
            return null;

        TreeNode<T> current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }




    public T search(T value) {
        if(buscar(root, value))
            return value;

        return null;
    }

    public boolean buscar(TreeNode<T> root, T value) {
        if(root == null)
            return false;

        if(comparator.compare(value, root.value)==0)
            return true;

        if(comparator.compare(value, root.value) < 0)
            return buscar(root.left, value);

        if(comparator.compare(value, root.value) > 0)
            return buscar(root.right, value);

        return false;
    }





    public int height() {
        return high(root);
    }

    private int count;
    public int size() {
        count = 0;

        completeNode(root);

        return count;
    }

    private boolean completeNode(TreeNode<T> root) {
        if(root == null)
            return true;

        boolean isRootvalue = ((root.left == null) || (root.left.value != root.value)) || ((root.right == null) || (root.right.value != root.value));

        boolean isleftvalue = completeNode(root.left);
        boolean isrightvalue = completeNode(root.right);

        if(isRootvalue || isleftvalue || isrightvalue)
            count++;

        return isRootvalue || isleftvalue || isrightvalue;
    }
}
