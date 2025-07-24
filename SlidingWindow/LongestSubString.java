package SlidingWindow;

import java.util.HashMap;

public class LongestSubString {
  public static int lengthOfLongestSubstring(String s) {
    int currentLongestString = 0;
    int start = 0;
    HashMap<Character, Integer> lastSeen = new HashMap<Character, Integer>();

    for (int end = 0; end < s.length(); end++) {
      char character = s.charAt(end);

      if (lastSeen.containsKey(character)) {
        int lastIndex = lastSeen.get(character);

        if (lastIndex >= start) {
          start = lastIndex + 1;
        }
      }

      lastSeen.put(character, end);
      currentLongestString = Math.max(currentLongestString, end - start + 1);
    }

    return currentLongestString;
  }

  public static void main(String[] args) {
    String s = "bbbbb";
    System.out.println(lengthOfLongestSubstring(s));
  }
}

/**
 * abcdab
 * start - 0, end - 0
 * 
 * check duplicate -> add index to set -> end++ -> continue
 * if duplicate -> lastIndex ->
 * if lastIndex in window -> slide the window -> update the index
 * if lastIndex not in window -> update the index
 * 
 * slide the window -> check end - start + 1 and update length -> update start
 * to end -> continue
 */