package LinkedList;

public class ListNode {
  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int[] values) {
    this.val = values[0];
    ListNode current = this;
    for (int i = 1; i < values.length; i++) {
      current.next = new ListNode(values[i]);
      current = current.next;
    }
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  static void printList(ListNode head) {
    System.out.print("[");
    while (head != null) {
      System.out.print(head.val);
      head = head.next;

      if (head != null) {
        System.out.print(",");

      }
    }
    System.out.print("]");
    System.out.println();
  }
}
