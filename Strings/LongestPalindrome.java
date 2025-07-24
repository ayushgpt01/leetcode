package Strings;

// Pending. O(n) solution is Manacher's algorithm.
public class LongestPalindrome {
  public static boolean isPalindrome(String str) {
    int left = 0, right = str.length() - 1;
    while (left < right) {
      char chl = str.charAt(left);
      char chr = str.charAt(right);

      if (chl != chr) {
        return false;
      }

      left++;
      right--;
    }

    return true;
  }

  public static String longestPalindrome(String s) {
    if (isPalindrome(s)) {
      return s;
    }

    int left = 0, right = s.length() - 1;
    String result = String.valueOf(s.charAt(0));

    while (left < right) {
      String sub = s.substring(left, right + 1);
      if (sub.length() > result.length() && isPalindrome(sub)) {
        result = sub;
      }

    }

    return result;
  }

  public static void main(String[] args) {
    String s = "babad";
    System.out.println(longestPalindrome(s));

    String s2 = "eabcb";
    System.out.println(longestPalindrome(s2));

    String s3 = "ab";
    System.out.println(longestPalindrome(s3));

    String s4 = "bacabab";
    System.out.println(longestPalindrome(s4));
  }
}
