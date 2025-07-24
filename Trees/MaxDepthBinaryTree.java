package Trees;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MaxDepthBinaryTree {
  public static int maxDepthDFS(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = maxDepth(root.left);
    int right = maxDepth(root.right);

    // We find if left subtree has more depth or right then we add current to it and
    // return
    return Math.max(left, right) + 1;
  }

  public static int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int depth = 0;
    Deque<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int queueSize = queue.size();

      for (int i = 0; i < queueSize; i++) {
        TreeNode node = queue.poll();

        if (node.left != null)
          queue.offer(node.left);
        if (node.right != null)
          queue.offer(node.right);
      }

      depth++;
    }

    return depth;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(List.of(3, 9, 20, Integer.MIN_VALUE, Integer.MIN_VALUE, 15, 7));
    TreeNode.printTree(root, "", false);

    System.out.println(maxDepthDFS(root));
  }
}
