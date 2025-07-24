package LinkedList;

public class ReorderList {
  public static ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    return prev;
  }

  public static void reorderList(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }

    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    // Break the link
    fast = slow.next;
    slow.next = null;
    fast = reverseList(fast);

    slow = head;

    while (fast != null) {
      ListNode next = slow.next;
      slow.next = fast;
      fast = fast.next;
      slow.next.next = next;
      slow = slow.next.next;
    }
  }

  public static void main(String[] args) {
    System.out.println("Test Case 1: 1->2->3->4");
    ListNode head1 = new ListNode(new int[] { 1, 2, 3, 4 });
    ListNode.printList(head1); // Original
    reorderList(head1);
    ListNode.printList(head1); // Reordered: 1->4->2->3->null

    System.out.println("\nTest Case 2: 1->2->3->4->5");
    ListNode head2 = new ListNode(new int[] { 1, 2, 3, 4, 5 });
    ListNode.printList(head2); // Original
    reorderList(head2);
    ListNode.printList(head2); // Reordered: 1->5->2->4->3->null

    System.out.println("\nTest Case 3: 1");
    ListNode head3 = new ListNode(new int[] { 1 });
    ListNode.printList(head3); // Original
    reorderList(head3);
    ListNode.printList(head3); // Reordered: 1->null

    System.out.println("\nTest Case 4: 1->2");
    ListNode head4 = new ListNode(new int[] { 1, 2 });
    ListNode.printList(head4); // Original
    reorderList(head4);
    ListNode.printList(head4); // Reordered: 1->2->null
  }
}
