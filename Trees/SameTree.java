package Trees;

import java.util.List;

public class SameTree {
  public static boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }

    if (p == null || q == null) {
      return false;
    }

    boolean isLeftSame = isSameTree(p.left, q.left);
    boolean isRightSame = isSameTree(p.right, q.right);

    if (p.val == q.val && isLeftSame && isRightSame) {
      return true;
    }

    return false;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(
        List.of(1, 2));
    TreeNode.printTree(root, "", false);
    System.out.println("");
    TreeNode root2 = new TreeNode(
        List.of(1, 2));
    TreeNode.printTree(root2, "", false);

    System.out.println(isSameTree(root, root2));
  }
}
