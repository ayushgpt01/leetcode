package Trees;

import java.util.List;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode() {
  }

  public TreeNode(int val) {
    this.val = val;
  }

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  public TreeNode(List<Integer> bfsTransversalList) {
    if (bfsTransversalList == null || bfsTransversalList.isEmpty()) {
      throw new IllegalArgumentException("List cannot be null or empty");
    }

    Integer val = bfsTransversalList.get(0);
    if (val == Integer.MIN_VALUE) {
      return;
    }

    this.val = val;
    this.left = buildTree(bfsTransversalList, 1);
    this.right = buildTree(bfsTransversalList, 2);
  }

  private TreeNode buildTree(List<Integer> bfsTransversalList, int index) {
    if (index >= bfsTransversalList.size()) {
      return null;
    }
    Integer val = bfsTransversalList.get(index);
    if (val == Integer.MIN_VALUE) {
      return null;
    }

    TreeNode node = new TreeNode(val);

    node.left = buildTree(bfsTransversalList, 2 * index + 1);
    node.right = buildTree(bfsTransversalList, 2 * index + 2);

    return node;

  }

  public static void printTree(TreeNode node, String prefix, boolean isLeft) {
    if (node != null) {
      System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.val);
      printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
      printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
    }
  }
}
