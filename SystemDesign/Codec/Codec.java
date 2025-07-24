package SystemDesign.Codec;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import Trees.TreeNode;

/**
 * One way to do this is to store the inorder and post order traversal...
 * Or maybe i can store the level traversal with numbers and null.
 * So 2,NULL,3,4,1 can be stored in the string and then during
 * deserialize I already know the max elements at each levels which will be 1 ->
 * 2 -> 4.. 2^n, n = 0..logn.
 */
public class Codec {
  public String serialize(TreeNode root) {
    if (root == null) {
      return "null";
    }

    Deque<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    StringBuilder stringBuilder = new StringBuilder();

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();

      if (node == null) {
        stringBuilder.append("null").append(",");
      } else {
        stringBuilder.append(node.val).append(",");
        queue.offer(node.left);
        queue.offer(node.right);
      }

    }

    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    return stringBuilder.toString();
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data.isBlank() || data.equals("null")) {
      return null;
    }

    String[] values = data.split(",");
    Deque<TreeNode> queue = new LinkedList<>();
    TreeNode root = new TreeNode(Integer.parseInt(values[0]));
    queue.offer(root);

    int i = 1;

    while (!queue.isEmpty() && i < values.length) {
      TreeNode node = queue.poll();
      System.out.println(node.val);

      if (i < values.length) {
        String leftVal = values[i++];

        if (!leftVal.equals("null")) {
          node.left = new TreeNode(Integer.parseInt(leftVal));
          queue.offer(node.left);
        }

      }

      if (i < values.length) {
        String rightVal = values[i++];
        if (!rightVal.equals("null")) {
          node.right = new TreeNode(Integer.parseInt(rightVal));
          queue.offer(node.right);
        }
      }

    }

    return root;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(List.of(1, 2, 3, Integer.MIN_VALUE,
        Integer.MIN_VALUE, 4, 5, 6, 7));
    TreeNode.printTree(root, "", false);

    Codec ser = new Codec();
    String serialized = ser.serialize(root);
    System.out.println(serialized);

    Codec deser = new Codec();
    TreeNode ans = deser.deserialize(serialized);
    TreeNode.printTree(ans, "", false);
  }
}
