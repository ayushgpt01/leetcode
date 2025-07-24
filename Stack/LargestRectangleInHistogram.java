package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

import Utils.Printer;

public class LargestRectangleInHistogram {
  private static int calculateArea(int minimum, int i, int j) {
    return minimum * (j - i + 1);
  }

  /**
   * Okay, so after some thinking and looking at histogram i noticed two things,
   * - First to calculate the area we need min(elements) * no. of elements.
   * - Second, Each item itself has an area equivalent to its height.
   * 
   * Since, there may be other area where such a rectangle exists. So, we can
   * actually do this for every item in array (maybe). This way we can figure out
   * the maximum from all of these.
   * 
   * Now a sequence of items are valid to be calculated for the area only if the
   * last item's height is less than or equal to next item since that means we can
   * extend this to next bar and calculate the height. If it's less than the top
   * item then we cannot extend and we get the boundary of the top item.
   * 
   * Okay so the trick in this is to store the index and element in the stack and
   * update the index when we are removing top item to count for extending current
   * height to previous ones. And we remove top item when current is less than
   * top. Since top cannot be extended further.
   */
  public static int largestRectangleArea(int[] heights) {
    // 0 is index and 1 is element
    Deque<int[]> stack = new ArrayDeque<>();
    int maximum = 0;

    for (int i = 0; i < heights.length; i++) {
      int startIndex = i;
      while (stack.peek() != null && stack.peek()[1] > heights[i]) {
        int[] top = stack.pop();
        int area = calculateArea(top[1], top[0], i - 1);
        maximum = Math.max(maximum, area);
        startIndex = top[0];
      }

      stack.push(new int[] { startIndex, heights[i] });
    }

    while (!stack.isEmpty()) {
      int[] top = stack.pop();
      int area = calculateArea(top[1], top[0], heights.length - 1);
      maximum = Math.max(maximum, area);
    }

    return maximum;
  }

  public static void main(String[] args) {
    int[] heights = { 2, 4 };
    Printer.print(largestRectangleArea(heights));
  }
}
