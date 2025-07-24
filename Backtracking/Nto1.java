package Backtracking;

public class Nto1 {
  public static void printNum(int i, int n) {
    if (i > n) {
      return;
    }

    printNum(i + 1, n);
    System.out.println(i);
  }

  public static void main(String[] args) {
    printNum(1, 3);
  }
}
