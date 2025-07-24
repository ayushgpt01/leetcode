package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

import Utils.Printer;

/**
 * - Start by looking for the maximum in the first k items and store the 0.
 * - Then start the loop with left from 1 to n - k
 * - right will be left + k
 * - We have the previous maximum, we compare the previous maximum with right
 * element
 * - If it is larger we put this number else old max
 * - Continue until end and return the array
 * 
 * Problem is this - Getting maximum value in each window optimally
 * This doesn't work if the maximum in the old window was at first character
 * since after moving it might go out of window and then we don't know the next
 * max.
 * 
 * One way to resolve this is to make a max-heap and push all the items from
 * window in it then we can get out the max always. However, there's another
 * problem of having to remove the element from queue as we move window.
 * 
 * So, we can use a dequeue for this, in such a way that tail can be removed
 * easily when moving the window and top always has the maximum element on it.
 */
public class SlidingWindowMaximum {
  public static int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    int[] result = new int[n - k + 1];
    Deque<Integer> queue = new ArrayDeque<>(n);

    for (int i = 0; i < k; i++) {
      while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
        queue.pollLast();
      }

      queue.addLast(i);
    }

    for (int i = k; i < n; i++) {
      result[i - k] = nums[queue.peekFirst()];
      if (queue.peekFirst() == i - k) {
        queue.pollFirst();
      }

      while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
        queue.pollLast();
      }

      queue.addLast(i);
    }

    result[n - k] = nums[queue.peekFirst()];

    return result;
  }

  public static void main(String[] args) {
    int[] nums = { 1, -1 };
    int k = 1;
    Printer.print(maxSlidingWindow(nums, k));
  }
}
