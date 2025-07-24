package LinkedList;

public class MergeLinkedList {
  public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }

    if (list2 == null) {
      return list1;
    }

    ListNode temp1 = list1;
    ListNode temp2 = list2;
    ListNode head = new ListNode();
    ListNode tail = head;

    while (temp1 != null && temp2 != null) {
      if (temp1.val < temp2.val) {
        tail.next = temp1;
        tail = tail.next;
        temp1 = temp1.next;
      } else {
        tail.next = temp2;
        tail = tail.next;
        temp2 = temp2.next;
      }
    }

    if (temp1 != null)
      tail.next = temp1;
    if (temp2 != null)
      tail.next = temp2;

    return head.next;
  }

  public static void main(String[] args) {
    ListNode list1 = new ListNode(new int[] { 1, 2, 4 });
    ListNode list2 = new ListNode(new int[] { 1, 3, 4 });
    ListNode.printList(mergeTwoLists(list1, list2));
  }
}
