package Trees;

import java.util.List;

public class InvertBinaryTree {
  public static TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return root;
    }

    TreeNode right = root.right;
    root.right = root.left;
    root.left = right;

    invertTree(root.right);
    invertTree(root.left);

    return root;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(
        List.of(4, 2, 7, 1, 3, 6, 9));
    TreeNode.printTree(root, "", false);

    TreeNode.printTree(invertTree(root), "", false);
  }
}
