package Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Utils.Printer;

public class LetterCombination {
  static HashMap<Character, List<Character>> digitMap;

  static {
    digitMap = new HashMap<>();
    digitMap.put('2', List.of('a', 'b', 'c'));
    digitMap.put('3', List.of('d', 'e', 'f'));
    digitMap.put('4', List.of('g', 'h', 'i'));
    digitMap.put('5', List.of('j', 'k', 'l'));
    digitMap.put('6', List.of('m', 'n', 'o'));
    digitMap.put('7', List.of('p', 'q', 'r', 's'));
    digitMap.put('8', List.of('t', 'u', 'v'));
    digitMap.put('9', List.of('w', 'x', 'y', 'z'));
  }

  private static void getCombination(String digits, int index, StringBuilder str,
      List<String> result) {
    if (index == digits.length()) {
      if (str.length() > 0) {
        result.add(str.toString());
      }
      return;
    }

    List<Character> characters = digitMap.get(digits.charAt(index));

    for (Character character : characters) {
      str.append(character);
      getCombination(digits, index + 1, str, result);
      str.deleteCharAt(str.length() - 1);
    }
  }

  public static List<String> letterCombinations(String digits) {
    List<String> result = new ArrayList<>();
    getCombination(digits, 0, new StringBuilder(), result);
    return result;
  }

  public static void main(String[] args) {
    String digits = "23";
    Printer.print(letterCombinations(digits));
  }
}
