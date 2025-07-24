package LinkedList;

// We initalise two pointers start and end at head
// Then we move end to k places ahead.
// Then we diconnect end to next and we reverse the list
// Then we reconnect the new end with next
// Then we move start and end to next
// Continue until end != null
// One thing to note is we can initialise a dummy pointer and store the first
// end in it, effectively allowing us to return the new head after being
// reversed.

// One more optimisation is that we can reverse the list in place as we do the
// transversal and stop at k. Though this one will be tough to do in last list
// since it can also be less amount of numbers than k and then we gotta
// re-reverse the list. Hmm.. then maybe above method is good.
public class ReverseKList {
  private static ListNode reverse(ListNode head) {
    ListNode current = head;
    ListNode prev = null;
    while (current != null) {
      ListNode next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }

    return prev;
  }

  public static ListNode reverseKGroup(ListNode head, int k) {
    ListNode dummyNode = new ListNode();
    ListNode start = head;
    ListNode end = head;
    ListNode prevNode = null;

    do {
      int i = 1;
      while (i < k && end != null) {
        end = end.next;
        i++;
      }

      if (end == null) {
        break;
      }

      ListNode next = end.next;
      end.next = null;

      // start is now at end and end is at start
      end = reverse(start);
      start.next = next;

      if (prevNode != null) {
        prevNode.next = end;
      } else {
        dummyNode.next = end;
      }

      prevNode = start;
      end = next;
      start = next;
    } while (end != null);

    return dummyNode.next;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(new int[] { 1, 2, 3, 4, 5 });
    int k = 2;

    ListNode.printList(head);
    ListNode.printList(reverseKGroup(head, k));
  }
}
