package SlidingWindow;

/**
 * Here, We need to find the permutation (a rearrangement of all characters) in
 * one string for another. This sounds like a substring problem without any
 * ordering requirements.
 * 
 * Brute Force - One way to check is to sort the strings and then check for
 * substring but this is more costly as sorting takes nlogn time to complete.
 * 
 * Optimised - In starting I can store the count of each character in s1. Then I
 * can loop through the s2 string and find a character that is also in s1. Once
 * we find this, we start another pointer that goes ahead and starts to check if
 * we can reconstruct the same counts for each of the characters. If we can then
 * we have a permutation sub string else not.
 * 
 * Optimal - For this I am thinking of using a fixed size window to track all
 * the different characters in s1. This way we can compare the two in O(1) time
 * and they will take O(1) space. Also we can update this window as we move it
 * making O(1) updates and will remove all the n iterations except the main
 * loop.
 * 
 * Time Complexity - O(m + n)
 * Space Complexity - O(1)
 */
public class PermutationsInStrings {
  public static boolean isPermutation(String s1, String s2, int[] countMap1) {
    int[] countMap2 = new int[26];
    for (char c : s2.toCharArray()) {
      countMap2[c - 'a']++;
    }

    for (int i = 0; i < countMap2.length; i++) {
      if (countMap2[i] != countMap1[i]) {
        return false;
      }
    }

    return true;
  }

  public static boolean nonOptimal(String s1, String s2) {
    int[] countMap = new int[26];
    for (char c : s1.toCharArray()) {
      countMap[c - 'a']++;
    }

    for (int left = 0; left < s2.length(); left++) {
      char ch = s2.charAt(left);

      if (countMap[ch - 'a'] != 0 && left + s1.length() <= s2.length()) {
        String subString = s2.substring(left, left + s1.length());

        if (isPermutation(s1, subString, countMap)) {
          return true;
        }
      }
    }

    return false;
  }

  public static boolean checkCountMapEquality(int[] map1, int[] map2) {
    if (map1.length != 26 || map2.length != 26) {
      return false;
    }

    for (int i = 0; i < 26; i++) {
      if (map2[i] != map1[i]) {
        return false;
      }
    }

    return true;
  }

  public static boolean checkInclusion(String s1, String s2) {
    if (s2.length() < s1.length()) {
      return false;
    }

    int[] countMap = new int[26];
    int[] windowMap = new int[26];

    for (int i = 0; i < s1.length(); i++) {
      countMap[s1.charAt(i) - 'a']++;
      windowMap[s2.charAt(i) - 'a']++;
    }

    if (checkCountMapEquality(countMap, windowMap)) {
      return true;
    }

    for (int left = 0; left < s2.length(); left++) {
      int right = left + s1.length();

      if (right < s2.length()) {
        char chLeft = s2.charAt(left);
        char chRight = s2.charAt(right);
        windowMap[chLeft - 'a']--;
        windowMap[chRight - 'a']++;
      } else {
        break;
      }

      if (checkCountMapEquality(countMap, windowMap)) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    String s1 = "a";
    String s2 = "ab";

    System.out.println(checkInclusion(s1, s2));

    String s3 = "ab";
    String s4 = "eidboaoo";
    System.out.println(checkInclusion(s3, s4));
  }
}