package LinkedList;

public class ReverseLinkedList {
  public static ListNode reverseList(ListNode head) {
    ListNode curr = head;
    ListNode prev = null;

    while (curr != null) {
      // Get the next node in current
      ListNode next = curr.next;
      // Move the pointer 180 by assigning previous to current instead
      // So now i value points to i - 1 effectively reversing the situation
      curr.next = prev;
      // Now, update the prev with current node
      prev = curr;
      // And update the current node with next node in list
      curr = next;
    }

    // Finally return last seen (tail) node which now works like the new head node.
    return prev;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(new int[] { 1, 2, 3, 4, 5 });
    ListNode.printList(head);
    ListNode.printList(reverseList(head));
  }
}
