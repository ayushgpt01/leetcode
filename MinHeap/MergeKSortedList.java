package MinHeap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class ListNode {
  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

public class MergeKSortedList {
  public static ListNode brutemergeKLists(ListNode[] lists) {
    if (lists.length == 0)
      return null;

    List<Integer> entireList = new ArrayList<>();
    for (ListNode listNode : lists) {
      ListNode nextNode = listNode;

      while (nextNode != null) {
        entireList.add(nextNode.val);
        nextNode = nextNode.next;
      }
    }

    Collections.sort(entireList);

    if (entireList.size() == 0)
      return null;

    ListNode result = new ListNode(entireList.get(entireList.size() - 1));
    for (int i = entireList.size() - 2; i >= 0; i--) {
      int val = entireList.get(i);
      ListNode nextNode = new ListNode(val);
      nextNode.next = result;
      result = nextNode;
    }

    return result;
  }

  public static ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0)
      return null;

    // PriorityQueue<ListNode> queue = new PriorityQueue<>(new
    // Comparator<ListNode>() {
    // @Override
    // public int compare(ListNode x, ListNode y) {
    // return Integer.compare(x.val, y.val);
    // }
    // });

    PriorityQueue<ListNode> queue = new PriorityQueue<>(
        (a, b) -> Integer.compare(a.val, b.val));

    for (ListNode listNode : lists) {
      if (listNode != null) {
        queue.add(listNode);
      }
    }

    ListNode result = new ListNode();
    ListNode temp = result;

    while (!queue.isEmpty()) {
      ListNode current = queue.poll();
      if (current.next != null) {
        queue.add(current.next);
      }

      temp.next = current;
      temp = temp.next;
    }

    return result.next;
  }

  public static void main(String[] args) {
    ListNode[] lists = new ListNode[3];

    lists[0] = new ListNode(1, new ListNode(4, new ListNode(5)));
    lists[1] = new ListNode(1, new ListNode(3, new ListNode(4)));
    lists[2] = new ListNode(2, new ListNode(6));

    ListNode merged = mergeKLists(lists);
    while (merged != null) {
      System.out.print(merged.val + " ,");
      merged = merged.next;
    }
  }
}
