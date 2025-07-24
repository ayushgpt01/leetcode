package Backtracking;

import java.util.ArrayList;
import java.util.List;

import Utils.Printer;

/**
 * One possible way to do this is to generate all combinations for n '(' and n
 * ')' then check if it's a valid parenthesis or not. But that's quite
 * inefficient in time complexity as with n number of combinations increase in
 * powers of 2. For easier I am considering '(' as 0 and ')' as 1
 * 
 * Instead I need to think of a better way to solve this. On thing to note is
 * that every valid parenthesis while creating will mean we reverse the symbols
 * based on what we put initially. Also there cannot be a 1 without a prior 0.
 * 
 * So, if I take a stack to build these strings, and push a 0 onto it, on next I
 * can put a 0 or 1.. this can move until max n times if we take 0's and then we
 * have no choice but to put a 1. But we also need to consider situations when
 * in each of those times we put a 1 instead which would lead to a pop of this
 * pair.
 * 
 * Okay based on this there is one more thing i noticed, we need to generate n
 * pairs of 01. Then we generate combinations of these while maintaing their
 * respective orders.
 * 
 * To generate these I need to remove the invalid path right away and go forward
 * if valid. This will require backtracking to come back if we reach a length of
 * 2n as well as if there's an invalid path.
 */
public class GenerateParenthesis {
  public static void generate(int n, int openCount, int closeCount, StringBuilder s, List<String> str) {
    // Base Condition
    if (n == openCount && closeCount == n) {
      str.add(s.toString());
      return;
    }

    if (closeCount > n || closeCount > openCount || openCount > n) {
      return;
    }

    if (openCount < n) {
      s.append("(");
      generate(n, openCount + 1, closeCount, s, str);
      s.deleteCharAt(s.length() - 1);
    }

    if (closeCount < openCount) {
      s.append(")");
      generate(n, openCount, closeCount + 1, s, str);
      s.deleteCharAt(s.length() - 1);
    }
  }

  public static List<String> generateParenthesis(int n) {
    List<String> strs = new ArrayList<>();
    StringBuilder stringBuilder = new StringBuilder();
    generate(n, 0, 0, stringBuilder, strs);

    return strs;
  }

  public static void main(String[] args) {
    // "((()))","(()())","(())()","()(())","()()()"
    // "000111", "001001", "001101", "010011", "010101"
    // "(())", "()()"

    // 0 -> 0 | 1
    // -> 0 ->
    // -> 1 ->
    int n = 3;
    Printer.print(generateParenthesis(n));
  }
}
