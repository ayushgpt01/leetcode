package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * So, parenthesis are solved Last In First Out way. And for this we can utlise
 * a stack. A set of parenthesis is valid if you push them into stack and pop
 * them from stack without any problems.
 */
public class ValidParenthesis {
  public static boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (int i = 0; i < s.length(); i++) {
      Character character = s.charAt(i);
      if (character == '(' || character == '[' || character == '{') {
        stack.push(character);
      } else {
        Character current = stack.peek();
        if (current == null) {
          return false;
        }

        if ((character == ')' && current != '(') || (character == ']' && current != '[')
            || (character == '}' && current != '{')) {
          return false;
        }
        stack.pop();
      }
    }

    return stack.isEmpty();
  }

  public static void main(String[] args) {
    String s1 = "()";
    System.out.println(isValid(s1));

    String s2 = "([])";
    System.out.println(isValid(s2));

    String s3 = "(]";
    System.out.println(isValid(s3));
  }
}
