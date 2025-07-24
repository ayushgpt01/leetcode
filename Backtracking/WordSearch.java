package Backtracking;

import Utils.Printer;

public class WordSearch {
  public static boolean traverse(char[][] board, int i, int j, String word, int k) {
    if (i >= board.length || i < 0 ||
        j < 0 || j >= board[0].length ||
        word.charAt(k) != board[i][j]) {
      return false;
    }

    if (word.length() - 1 == k) {
      return true;
    }

    char temp = board[i][j];
    board[i][j] = '#';

    // Check left
    boolean exists = traverse(board, i, j + 1, word, k + 1) ||
        traverse(board, i, j - 1, word, k + 1) ||
        traverse(board, i + 1, j, word, k + 1) ||
        traverse(board, i - 1, j, word, k + 1);

    if (exists)
      return true;

    board[i][j] = temp;
    return false;
  }

  public static boolean exist(char[][] board, String word) {
    char first = word.charAt(0);
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == first) {
          if (traverse(board, i, j, word, 0)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    // char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A',
    // 'D', 'E', 'E' } };
    // String word = "SEE";
    char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'E', 'S' }, { 'A', 'D', 'E', 'E' } };
    String word = "ABCESEEEFS";
    Printer.print(exist(board, word));
  }
}
