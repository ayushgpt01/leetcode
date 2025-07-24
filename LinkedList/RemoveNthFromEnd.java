package LinkedList;

public class RemoveNthFromEnd {
  public static ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) {
      return head;
    }

    // Transverse to end and store the total number of elements.
    int total = 0;
    ListNode temp = head;
    while (temp != null) {
      total++;
      temp = temp.next;
    }

    // Get the index of element by doing total - n
    int index = total - n; // Total = 5, n = 2, index = 3

    // If n is equal to length and currIndex = 0 then we can return head.next
    if (index == 0) {
      return head.next;
    }

    // Transverse list again until we reach index total - n and keep a prev pointer
    ListNode current = head;

    ListNode prev = null;
    int i = 0;

    while (current != null && i < index) {
      ListNode next = current.next;
      prev = current;
      current = next;
      i++;
    }

    // Connect the prev to curr.next, this will effectively remove curr.
    if (prev != null) {
      prev.next = current.next;
    }

    return head;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(new int[] { 1, 2, 3, 4, 5 });
    int n = 5;
    ListNode.printList(head);
    ListNode.printList(removeNthFromEnd(head, n));
  }
}
