package LinkedList;

import java.util.ArrayList;
import java.util.List;

public class NodeRandom {
  int val;
  NodeRandom next;
  NodeRandom random;

  NodeRandom() {
  }

  public static NodeRandom createListWithRandomPointers(int[] values, int[] randomIndices) {
    if (values == null || values.length == 0) {
      return null;
    }

    // Step 1: Create all nodes and store them in an array for easy access by index
    List<NodeRandom> nodes = new ArrayList<>();
    for (int val : values) {
      nodes.add(new NodeRandom(val));
    }

    // Step 2: Connect 'next' and 'random' pointers
    for (int i = 0; i < values.length; i++) {
      NodeRandom currentNode = nodes.get(i);

      // Connect 'next' pointer
      if (i < values.length - 1) {
        currentNode.next = nodes.get(i + 1);
      }

      // Connect 'random' pointer
      if (randomIndices[i] != -1) {
        currentNode.random = nodes.get(randomIndices[i]);
      } else {
        currentNode.random = null; // Explicitly set to null if index is -1
      }
    }

    return nodes.get(0); // Return the head of the constructed list
  }

  NodeRandom(int val) {
    this.val = val;
  }

  NodeRandom(int val, NodeRandom next) {
    this.val = val;
    this.next = next;
  }

  NodeRandom(int val, NodeRandom next, NodeRandom random) {
    this.val = val;
    this.next = next;
    this.random = random;
  }

  static void printList(NodeRandom head) {
    System.out.print("[");
    NodeRandom current = head;
    while (current != null) {
      String randomVal = (current.random == null) ? "null" : String.valueOf(current.random.val);
      System.out.print("(" + current.val + "," + randomVal + ")");
      current = current.next;
      if (current != null) {
        System.out.print(", ");
      }
    }
    System.out.print("]");
    System.out.println();
  }
}
