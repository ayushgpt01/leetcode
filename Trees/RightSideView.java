package Trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import Utils.Printer;

public class RightSideView {
  public static List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Deque<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      result.add(queue.peekLast().val);

      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();

        if (node.left != null)
          queue.offer(node.left);
        if (node.right != null)
          queue.offer(node.right);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(List.of(1, 2, 3, Integer.MIN_VALUE, 5, Integer.MIN_VALUE, 4));
    TreeNode.printTree(root, "", false);

    Printer.print(rightSideView(root));
  }
}
