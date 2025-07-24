package LinkedList;

import java.util.HashSet;

import Utils.Printer;

public class LinkedListCycle {
  public static boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (slow != null && fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
        return true;
      }
    }

    return false;
  }

  public static boolean hasCycleBrute(ListNode head) {
    ListNode temp = head;
    HashSet<ListNode> visited = new HashSet<>();
    while (temp != null) {
      if (visited.contains(temp)) {
        return true;
      }

      visited.add(temp);
      temp = temp.next;
    }

    return false;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(new int[] { 3, 2, 0, -4 });
    Printer.print(hasCycle(head));
  }
}
