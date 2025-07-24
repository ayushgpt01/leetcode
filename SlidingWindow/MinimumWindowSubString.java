package SlidingWindow;

import java.util.HashMap;

public class MinimumWindowSubString {
  public static String minWindow(String s, String t) {
    HashMap<Character, Integer> countMap = new HashMap<>();

    for (int i = 0; i < t.length(); i++) {
      char c = t.charAt(i);
      countMap.put(c, countMap.getOrDefault(c, 0) + 1);
    }

    int left = 0, right = 0, formed = 0;
    int required = t.length();
    int minStart = -1;
    int minLength = Integer.MAX_VALUE;

    while (right < s.length()) {
      char c = s.charAt(right);
      if (countMap.containsKey(c) && countMap.get(c) > 0) {
        formed++;
      }

      countMap.put(c, countMap.getOrDefault(c, 0) - 1);

      // Start shrinking
      while (formed == required) {
        if (minLength > right - left + 1) {
          minLength = right - left + 1;
          minStart = left;
        }

        char ch = s.charAt(left);
        countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);

        if (countMap.get(ch) > 0) {
          formed--;
        }

        left++;
      }

      right++;
    }

    return minStart == -1 ? "" : s.substring(minStart, minStart + minLength);
  }

  public static void main(String[] args) {
    String s = "ADOBECODEBANC";
    String t = "ABC";

    System.out.println(minWindow(s, t));
  }
}

// ABBCD - BCD
//