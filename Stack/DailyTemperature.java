package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

import Utils.Printer;

/**
 * This one has a bit of a thinking involved. Basically, we need to get the next
 * maximum on each place. Now naive way to do this is to just check every other
 * value starting from n+1 which gives a time complexity of O(n^2).
 * 
 * Next, we can look at this problem a bit differently, say by looking back
 * instead of ahead. So, if at any place there is a smaller number just below it
 * we can be sure that the length between these two is the answer. So, what we
 * can do is start going from 0 to n and keep storing what value we have seen so
 * far. Then, when we get a bigger value then current minimum, the length
 * between these two will be the answer for minimum element's position. So, if
 * we initialize a stack to store each element as they come then we can pop the
 * updated element and place length at it's index. And since whenever we find a
 * max, we remove the previous minimum values the stack is always decreasing or
 * 'monotonic decreasing stack'. Then to make it easier we store index instead
 * of element since we are interested in length rather than actual element.
 * 
 * This allows for a O(n + m) time complexity where n is the length and m is the
 * number of backtrack pops we had to do. Space complexity will be O(n)
 */
public class DailyTemperature {
  public static int[] dailyTemperatures(int[] temperatures) {
    if (temperatures.length < 1) {
      return new int[] {};
    }

    int[] result = new int[temperatures.length];
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(0);

    for (int i = 1; i < temperatures.length; i++) {
      while (stack.peek() != null && temperatures[stack.peek()] < temperatures[i]) {
        int lastIndex = stack.pop();
        result[lastIndex] = i - lastIndex;
      }

      stack.push(i);
    }

    return result;
  }

  public static void main(String[] args) {
    int[] temperatures = { 34, 80, 80, 34, 34, 80, 80, 80, 80, 34 };
    Printer.print(dailyTemperatures(temperatures));
  }
}
