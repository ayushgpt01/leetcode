package Strings;

/**
 * Anagram - Two strings having same number of characters but rearranged or not
 * are anagrams.
 * So, To find a valid anagram we can easily check that both the strings have
 * same number of each characters and if they have then they are anagram. Time
 * complexity is O(n + m) and space complexity is O(1) if we use simple integer
 * arrays of length 26
 */
public class ValidAnagram {
  public static boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    int[] countS = new int[26];
    int[] countT = new int[26];

    for (char c : t.toCharArray()) {
      countT[c - 'a']++;
    }

    for (char c : s.toCharArray()) {
      countS[c - 'a']++;
    }

    for (int i = 0; i < 26; i++) {
      if (countS[i] != countT[i]) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {

    System.out.println(isAnagram("ddddddddddg", "dgddddddddd"));
  }
}
