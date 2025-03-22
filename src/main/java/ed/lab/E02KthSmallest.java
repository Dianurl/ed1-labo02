package ed.lab;

public class E02KthSmallest {

    int count = 0;
    int result = -1;

    public int kthSmallest(TreeNode<Integer> root, int k) {
        if(root == null)
            return k -k;

        if(buscar(root, k) != true) {
            Addk(root, k);
        }

        Addk(root, k);
        return result;
    }

    public boolean buscar(TreeNode<Integer> root, int value) {
        if(root == null)
            return false;

        if(root.value == value)
            return true;

        if(root.value < value)
            return buscar(root.left, value);

        if(root.value > value)
            return buscar(root.right, value);

        return false;
    }

    private void Addk(TreeNode<Integer> root, int k) {

        if(root == null)
            return;

        Addk(root.left, k);

        count++;
        if(count == k) {
            result = root.value;
            return;
        }

        if(count >= k)
            return;

        Addk(root.right, k);
    }
}
