package Strings;

public class Palindrome {
  public static boolean isPalindrome(String s) {
    String newString = s.strip().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");

    if (newString == "") {
      return true;
    }

    int left = 0;
    int right = newString.length() - 1;

    while (left < right) {
      if (newString.charAt(left) != newString.charAt(right)) {
        return false;
      }

      left++;
      right--;
    }

    return true;
  }

  public static void main(String[] args) {
    String s = "A man, a plan, a canal: Panama";
    System.out.println(isPalindrome(s));
  }
}