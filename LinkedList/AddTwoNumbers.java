package LinkedList;

public class AddTwoNumbers {
  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyNode = new ListNode(-1);
    ListNode temp1 = l1;
    ListNode temp2 = l2;
    ListNode temp = dummyNode;
    int carry = 0;

    while (temp1 != null && temp2 != null) {
      int sum = temp1.val + temp2.val + carry;
      temp.next = new ListNode(sum % 10);

      carry = sum / 10;
      temp = temp.next;
      temp1 = temp1.next;
      temp2 = temp2.next;
    }

    if (temp1 != null) {
      while (temp1 != null) {
        int sum = temp1.val + carry;
        temp.next = new ListNode(sum % 10);
        carry = sum / 10;
        temp = temp.next;
        temp1 = temp1.next;
      }
    }

    if (temp2 != null) {
      while (temp2 != null) {
        int sum = temp2.val + carry;
        temp.next = new ListNode(sum % 10);
        carry = sum / 10;
        temp = temp.next;
        temp2 = temp2.next;
      }
    }

    if (carry != 0) {
      temp.next = new ListNode(carry);
    }

    return dummyNode.next;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(new int[] { 9, 9, 9, 9, 9, 9, 9 });
    ListNode l2 = new ListNode(new int[] { 9, 9, 9, 9 });

    ListNode.printList(addTwoNumbers(l1, l2));
  }
}
