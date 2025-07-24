package Backtracking;

import java.util.ArrayList;
import java.util.List;

import Utils.Printer;

public class palindromePartioning {
  private static boolean isPalindrome(String s) {
    int low = 0;
    int high = s.length() - 1;

    while (low <= high) {
      if (s.charAt(low) != s.charAt(high)) {
        return false;
      }

      low++;
      high--;
    }

    return true;
  }

  private static void getPartition(String s, int index, List<List<String>> result,
      List<String> current) {
    // Base Case
    if (index == s.length()) {
      result.add(new ArrayList<>(current));
      return;
    }

    // Choices
    for (int i = index + 1; i <= s.length(); i++) {
      String subStr = s.substring(index, i);
      if (isPalindrome(subStr)) {
        current.add(subStr);
        getPartition(s, i, result, current);
        current.remove(current.size() - 1);
      }
    }
  }

  public static List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    getPartition(s, 0, result, new ArrayList<>());
    return result;
  }

  public static void main(String[] args) {
    String s = "aab";
    Printer.print(partition(s));
  }
}
