package Trees;

import java.util.List;

// A balanced binary tree has depth difference of log n 
public class BalancedBinaryTree {
  private static int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = maxDepth(root.left);
    if (left == -1) {
      return -1;
    }

    int right = maxDepth(root.right);
    if (left == -1) {
      return -1;
    }

    if (Math.abs(left - right) > 1) {
      return -1;
    }

    return Math.max(left, right) + 1;
  }

  public static boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }

    int balanced = maxDepth(root);

    return balanced != -1;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(
        List.of(1, 2, 2, 3, Integer.MIN_VALUE, Integer.MIN_VALUE, 3, 4, Integer.MIN_VALUE, Integer.MIN_VALUE, 4));
    TreeNode.printTree(root, "", false);

    System.out.println(isBalanced(root));
  }
}
