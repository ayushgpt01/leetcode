package DataStructure;

import java.util.LinkedList;
import java.util.List;

class MinStack {
  private final List<Integer> stack;
  private final List<Integer> minimum;

  public MinStack() {
    stack = new LinkedList<>();
    minimum = new LinkedList<>();
  }

  public void push(int val) {
    stack.add(val);
    int bestMin = Math.min(minimum.size() > 0 ? minimum.getLast() : Integer.MAX_VALUE, val);
    minimum.add(bestMin);
  }

  public void pop() {
    stack.removeLast();
    minimum.removeLast();
  }

  public int top() {
    return stack.getLast();
  }

  public int getMin() {
    return minimum.getLast();
  }

  public static void main(String[] args) {
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    System.out.println(minStack.getMin()); // return -3
    minStack.pop();
    System.out.println(minStack.top()); // return 0
    System.out.println(minStack.getMin()); // return -2
  }
}
