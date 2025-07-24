package Trees;

import java.util.List;

public class CountGoodNodes {
  public static int dfs(TreeNode root, int max) {
    if (root == null) {
      return 0;
    }

    max = Math.max(max, root.val);
    int countL = dfs(root.left, max);
    int countR = dfs(root.right, max);

    return countL + countR + (root.val >= max ? 1 : 0);
  }

  public static int goodNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return dfs(root, root.val);
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(
        List.of(1));
    TreeNode.printTree(root, "", false);

    System.out.println(goodNodes(root));
  }
}
