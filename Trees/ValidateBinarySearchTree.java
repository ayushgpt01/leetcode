package Trees;

import java.util.List;
import java.util.Stack;

public class ValidateBinarySearchTree {
  public static boolean isValid(TreeNode root, long min, long max) {
    if (root == null) {
      return true;
    }

    if (root.val <= min || root.val >= max) {
      return false;
    }

    return isValid(root.right, root.val, max) && isValid(root.left, min, root.val);
  }

  public static boolean isValidBSTIterative(TreeNode root) {
    if (root == null || (root.left == null && root.right == null)) {
      return true;
    }

    Stack<TreeNode> stack = new Stack<>();
    TreeNode prev = null;

    while (root != null || !stack.isEmpty()) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }

      root = stack.pop();
      if (prev != null && root.val <= prev.val) {
        return false;
      }

      prev = root;
      root = root.right;
    }

    return true;
  }

  public static boolean isValidBST(TreeNode root) {
    return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(
        List.of(-2147483648));
    TreeNode.printTree(root, "", false);
    System.out.println(isValidBST(root));

  }
}
