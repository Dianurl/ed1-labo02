package ed.lab;

import java.util.LinkedList;
import java.util.List;

public class E01InvertBT {
    public TreeNode<Integer> invertTree(TreeNode<Integer> root) {
        if(root == null)
            return null;

        TreeNode<Integer> NewRight = root.right;
        root.right = root.left;
        root.left = NewRight;
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
