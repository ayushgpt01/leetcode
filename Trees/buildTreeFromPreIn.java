package Trees;

import java.util.HashMap;

public class buildTreeFromPreIn {
  static HashMap<Integer, Integer> rootIndexInorder;

  public static TreeNode dfsBuild(int[] preorder, int preStart, int preEnd, int inStart, int inEnd) {
    if (inStart > inEnd || preStart > preEnd) {
      return null;
    }

    TreeNode root = new TreeNode(preorder[preStart]);

    if (!rootIndexInorder.containsKey(root.val)) {
      return null;
    }

    int rootIndex = rootIndexInorder.get(root.val);
    int numsLeft = rootIndex - inStart;

    // preorder - Root Left Right
    // Root = preStart, Left = root + 1:root + numsLeft, Right = LeftEnd + 1:preEnd

    // Inorder - Left Root Right
    // Root = rootIndex, Left = inStart:rootIndex - 1, Right = rootIndex + 1:inEnd

    root.left = dfsBuild(preorder, preStart + 1, preStart + numsLeft, inStart, rootIndex - 1);
    root.right = dfsBuild(preorder, preStart + numsLeft + 1, preEnd, rootIndex + 1, inEnd);

    return root;
  }

  public static TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0 || inorder.length == 0) {
      return null;
    }

    rootIndexInorder = new HashMap<Integer, Integer>();
    for (int i = 0; i < inorder.length; i++) {
      rootIndexInorder.put(inorder[i], i);
    }

    TreeNode root = dfsBuild(preorder, 0, preorder.length - 1, 0, inorder.length - 1);

    return root;
  }

  public static void main(String[] args) {
    int[] preorder = new int[] { 3, 9, 20, 15, 7 };
    int[] inorder = new int[] { 9, 3, 15, 20, 7 };
    TreeNode.printTree(buildTree(preorder, inorder), "", false);
  }
}
