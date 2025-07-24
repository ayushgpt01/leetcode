package SlidingWindow;

import java.util.Arrays;
import java.util.HashMap;

public class LongestSubArray {
  public static int[] longestSubarray(int[] s) {
    int maxLength = 0;
    int start = 0;
    int startIndex = 0;
    HashMap<Integer, Integer> lastSeen = new HashMap<Integer, Integer>();

    for (int end = 0; end < s.length; end++) {
      int digit = s[end];

      if (lastSeen.containsKey(digit)) {
        int lastIndex = lastSeen.get(digit);

        if (lastIndex >= start) {
          start = lastIndex + 1;
        }
      }

      lastSeen.put(digit, end);
      if (maxLength < end - start + 1) {
        maxLength = end - start + 1;
        startIndex = start;
      }
    }

    return Arrays.copyOfRange(s, startIndex, startIndex + maxLength);
  }

  public static void main(String[] args) {
    int[] arr = { 2, 3, 5, 3, 4, 6, 3, 4 };
    int[] subUnique = longestSubarray(arr);
    for (int i : subUnique) {
      System.out.print(i + " ,");
    }
  }
}
