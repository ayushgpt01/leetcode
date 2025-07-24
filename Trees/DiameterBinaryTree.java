package Trees;

import java.util.List;

// The diameter can be calculated by left depth + right depth + 1
// if i take max of all the diameters for each sub tree, I will have the
// diameter of entire tree. For leaf nodes that is 1.
// So.. we can transverse the tree recursively and collect left, right and
// calculate diameter at each node. But.. how would i collect depth and diameter
// at same time. Maybe store the diameter in something?
public class DiameterBinaryTree {
  static int diameter;

  public static int depth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = depth(root.left);
    int right = depth(root.right);

    diameter = Math.max(diameter, left + right);

    return Math.max(left, right) + 1;
  }

  public static int diameterOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }

    diameter = 0;
    depth(root);

    return diameter;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(
        List.of(1, 2, 3, 4, 5));
    TreeNode.printTree(root, "", false);

    System.out.println(diameterOfBinaryTree(root));
  }
}
