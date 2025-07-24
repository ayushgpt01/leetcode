package Trees;

import java.util.List;

/**
 * InOrder traversal of a BST leads to ascending order sorted array. So..
 * ideally if i do inorder and count nodes as i go. I can stop at k then that
 * will be the value which we need to return.
 * L - Root - R
 */
public class KthSmallestInBST {
  static int i = 0;

  public static int kthSmallest(TreeNode root, int k) {
    if (root == null) {
      return -1;
    }

    int count = kthSmallest(root.left, k);
    if (count != -1) {
      return count;
    }

    i++;

    if (i == k) {
      return root.val;
    }

    int countR = kthSmallest(root.right, k);
    if (countR != -1) {
      return countR;
    }

    return -1;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(
        List.of(5, 3, 6, 2, 4, Integer.MIN_VALUE, Integer.MIN_VALUE, 1));

    int k = 3;
    TreeNode.printTree(root, "", false);

    System.out.println(kthSmallest(root, k));
  }
}
