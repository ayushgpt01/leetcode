package LinkedList;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandom {
  public static NodeRandom copyRandom(NodeRandom head) {
    // To solve it in O(1) space i will need to remove the need for hashmap. So,
    // this case hashmap is storing the new nodes with the original nodes. But..
    // since this is a linked list I can also store the new nodes with original
    // nodes themselves.
    // To do this I can store it like this - old[1] -> new[1] -> old[2] -> new[2]
    // and so on.. this will allow me to copy the list.. but how do i get the
    // random value reference?
    // Random value reference.. will always be actually random.next if this is the
    // list. Since new node is always the next and next node is node.next.next

    // Initialise the combined list with new nodes and their values
    NodeRandom temp = head;
    while (temp != null) {
      NodeRandom newNode = new NodeRandom(temp.val);
      NodeRandom next = temp.next;
      temp.next = newNode;
      newNode.next = next;
      temp = next;
    }

    // Update the random values of all new nodes
    temp = head;
    while (temp != null) {
      NodeRandom random = temp.random;
      if (random != null) {
        temp.next.random = random.next;
      }
      temp = temp.next.next;
    }

    // Create a dummy node and connect all even nodes in the list which will give
    // the copied list now.
    NodeRandom dummy = new NodeRandom(-1);
    temp = head;
    NodeRandom temp2 = dummy;

    while (temp != null) {
      // Assign curr.next (new node) node to dummy.next
      NodeRandom newNode = temp.next;
      temp2.next = newNode;
      // Move new list pointer to next node.
      temp2 = temp2.next;

      // Get next old node
      temp.next = newNode.next;
      // Move pointer to next old node
      temp = temp.next;
    }

    return dummy.next;
  }

  public static NodeRandom copyRandomList(NodeRandom head) {
    // Go through the list once and build out a new list with all randoms to NULL
    // and their pointers with new node stored somewhere like a hashmap.
    Map<NodeRandom, NodeRandom> oldToNewMap = new HashMap<>();
    NodeRandom temp = head;
    while (temp != null) {
      NodeRandom node = new NodeRandom(temp.val);
      oldToNewMap.put(temp, node);
      temp = temp.next;
    }

    // Then we do another pass through this list and assign the node pointers based
    // on indexes of original list.
    NodeRandom temp1 = head;
    NodeRandom dummy = new NodeRandom();
    NodeRandom temp2 = dummy;
    while (temp1 != null) {
      temp2.next = oldToNewMap.get(temp1);
      temp2.next.random = oldToNewMap.get(temp1.random);
      temp2 = temp2.next;
      temp1 = temp1.next;
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    NodeRandom head = NodeRandom.createListWithRandomPointers(
        new int[] { 7, 13, 11, 10, 1 },
        new int[] { -1, 0, 4, 2, 0 });

    NodeRandom.printList(head);
    NodeRandom.printList(copyRandom(head));
  }
}
