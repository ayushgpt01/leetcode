package Trees;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SubTree {
  public static boolean isSameTree(TreeNode root, TreeNode sub) {
    if (root == null && sub == null) {
      return true;
    }

    if (root == null || sub == null) {
      return false;
    }

    boolean isLeftSame = isSameTree(root.left, sub.left);
    boolean isRightSame = isSameTree(root.right, sub.right);

    if (isLeftSame && isRightSame && root.val == sub.val) {
      return true;
    }

    return false;
  }

  public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
    Deque<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();

        if (isSameTree(node, subRoot)) {
          return true;
        }

        if (node.left != null)
          queue.offer(node.left);
        if (node.right != null)
          queue.offer(node.right);
      }
    }

    return false;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(
        List.of(3, 4, 5, 1, 2));
    TreeNode.printTree(root, "", false);
    System.out.println("");
    TreeNode root2 = new TreeNode(
        List.of(4, 1, 2));
    TreeNode.printTree(root2, "", false);

    System.out.println(isSubtree(root, root2));
  }
}
