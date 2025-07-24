package HashMapQuestions;

import java.util.Arrays;

/**
 * So, my first intuition is to do 3 checks one after another with first row
 * check across the board, then column check across the board and lastly grid
 * checks for each of the 3*3 grids with a freq set that calculates if we have
 * seen a character before of not. If not we return false. Since each board is
 * 9*9 the time and space complexity is O(1).
 */
public class ValidSudoku {
  public static boolean isValidSudoku(char[][] board) {
    // Row check
    int[] set = new int[9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] != '.') {
          if (set[board[i][j] - '1'] > 0) {
            return false;
          }

          set[board[i][j] - '1']++;
        }
      }

      Arrays.fill(set, 0);
    }

    // Column check
    int[] set2 = new int[9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[j][i] != '.') {
          if (set2[board[j][i] - '1'] > 0) {
            return false;
          }

          set2[board[j][i] - '1']++;
        }
      }

      Arrays.fill(set2, 0);

    }

    // 3 X 3 check
    int[] set3 = new int[9];
    for (int blockRow = 0; blockRow < 9; blockRow += 3) {
      for (int blockCol = 0; blockCol < 9; blockCol += 3) {
        for (int i = blockRow; i < blockRow + 3; i++) {
          for (int j = blockCol; j < blockCol + 3; j++) {
            if (board[i][j] != '.') {
              if (set3[board[i][j] - '1'] > 0) {
                return false;
              }

              set3[board[i][j] - '1']++;
            }
          }
        }

        Arrays.fill(set3, 0);
      }
    }

    return true;
  }

  public static void main(String[] args) {
    char[][] board = {
        { '8', '3', '.', '.', '7', '.', '.', '.', '.' },
        { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
        { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
        { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
        { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
        { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
        { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
        { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
        { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
    };

    System.out.println(isValidSudoku(board));
  }
}
