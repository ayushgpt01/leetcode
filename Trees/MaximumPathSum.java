package Trees;

import java.util.List;

/**
 * A few things to note in this -
 * - At any arbitary node the max path sum through it is max path sum for left +
 * max path sum for right + current.
 * What this essentially means that if there's a path in left tree which has a
 * sum lmax and there is a path in right tree which has a sum rmax then if we
 * connect these two via root we can get a new possible answer.
 * This possible answer can then be updated to new overall max sum on each
 * iteration.
 * Then for the next iteration or recursive call, we can return the max path
 * which ends at current node. Why? Well we need the path that's either coming
 * through left or right to continue up the chain. So, we return Math.max(lmax +
 * root, rmax + root).
 * 
 * If we do this for all nodes we'll get the overall max path sum
 */
public class MaximumPathSum {
  private static int max;

  public static int dfs(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int lmax = dfs(root.left);
    int rmax = dfs(root.right);
    lmax = Math.max(lmax, 0);
    rmax = Math.max(rmax, 0);

    max = Math.max(max, lmax + rmax + root.val);

    return Math.max(lmax, rmax) + root.val;
  }

  public static int maxPathSum(TreeNode root) {
    max = Integer.MIN_VALUE;
    // DON'T do max = Math.max(max, dfs(root));
    // This is because java executes code left to right and max will still be
    // Integer.MIN_VALUE even after dfs is evaluated. So, order matters!
    max = Math.max(dfs(root), max);

    // Actually, I don't need to check the above one since it's not the V-shaped
    // structure so that's not valid anyway. If it was, it's already calculated.
    // dfs(root);
    return max;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(
        List.of(2, -1, -2));
    TreeNode.printTree(root, "", false);

    System.out.println(maxPathSum(root));
  }
}
