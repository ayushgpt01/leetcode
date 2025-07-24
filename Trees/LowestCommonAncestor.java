package Trees;

import java.util.List;

/**
 * 2 conditions for ancestors -
 * 1. They are on different levels, then whichever is on upper level will be the
 * ancestor.
 * 2. They are on same level. This is a bit of change. Since for this we need to
 * track the path from top to this node and the note at which both paths meet is
 * the ancestor.
 * 
 * So, level wise I can do BFS. But the problem is second point. How do i make
 * sure to track the path as well. Well one way is to just start from root when
 * i find this node and do a dfs which will give me path.
 * But wait this is a BST!. That means L < Root < R.
 * That makes it easier so now, actually i can just do dfs transversal to both
 * nodes and the point at which they diverge is the LCA.
 * 
 * What's the divergence point though ?
 * It is when Min(p, q) < root and Max(p, q) > root
 * 
 * This shows that they both are moving onto separate branches now and this is
 * the last point they have common which is lowest common ancestor as well.
 * But wait is it? umm.. yes it will be because it's a BST and this whole sub
 * tree is smaller than any other ancestors in the tree.
 */
public class LowestCommonAncestor {
  public static TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }

    if (root.val == p.val || root.val == q.val) {
      return root;
    }

    if (Math.min(p.val, q.val) < root.val && Math.max(p.val, q.val) > root.val) {
      return root;
    }

    TreeNode left = dfs(root.left, p, q);
    if (left != null) {
      return left;
    }

    TreeNode right = dfs(root.right, p, q);
    if (right != null) {
      return right;
    }

    return null;
  }

  public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (p == null || q == null) {
      return null;
    }

    return dfs(root, p, q);
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(
        List.of(6, 2, 8, 0, 4, 7, 9, Integer.MIN_VALUE, Integer.MIN_VALUE, 3, 5));

    TreeNode p = new TreeNode(2);
    TreeNode q = new TreeNode(4);
    TreeNode.printTree(root, "", false);

    TreeNode.printTree(lowestCommonAncestor(root, p, q), "", false);
  }
}
