package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReversePolishNotation {
  public static int evalRPN(String[] tokens) {
    Deque<String> stack = new ArrayDeque<>();
    for (String string : tokens) {
      // This is required as sometimes string might have different reference
      // Hence, don't use == instead use .equals()
      if (string.equals("+") || string.equals("*") || string.equals("-") || string.equals("/")) {
        int rightOp = Integer.parseInt(stack.pop());
        int leftOp = Integer.parseInt(stack.pop());
        int result = 0;
        switch (string) {
          case "+":
            result = leftOp + rightOp;
            break;
          case "*":
            result = leftOp * rightOp;
            break;
          case "-":
            result = leftOp - rightOp;
            break;
          case "/":
            result = leftOp / rightOp;
            break;

          default:
            break;
        }

        stack.push(String.valueOf(result));
      } else {
        stack.push(string);
      }
    }

    return Integer.parseInt(stack.pop());
  }

  public static void main(String[] args) {
    String[] tokens = { "2", "1", "+", "3", "*" };
    System.out.println(evalRPN(tokens));
  }
}
