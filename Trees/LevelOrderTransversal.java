package Trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import Utils.Printer;

public class LevelOrderTransversal {
  public static List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Deque<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        list.add(node.val);

        if (node.left != null)
          queue.add(node.left);
        if (node.right != null)
          queue.add(node.right);
      }

      result.add(list);
    }

    return result;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(List.of(3, 9, 20, Integer.MIN_VALUE, Integer.MIN_VALUE, 15, 7));
    TreeNode.printTree(root, "", false);

    Printer.print(levelOrder(root));
  }
}
