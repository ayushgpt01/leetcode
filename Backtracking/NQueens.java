package Backtracking;

import java.util.ArrayList;
import java.util.List;

import Utils.Printer;

/**
 * Problem link - https://leetcode.com/problems/n-queens/description/
 * 
 * So, looking at the problem we can see that for n queens to be fit into n*n
 * grid, we need to make sure a few things -
 * - Each row only has one queen
 * - Each column only has one queen
 * - There are no queens to the diagonal of any other queen
 * 
 * First two points are easy to grasp. Third one we need to think of. Now since
 * this requires us to generate all possible combinations this is atleast a
 * backtracking problem. Now we can definitely generate all possible cases and
 * then see if a particular arrangement is valid and work that way. But that
 * seems redundant since, let's say we already put a queen in col 0 then we
 * cannot put another in it anyway so why check those branches.
 * 
 * Base Case - We have placed n queens
 * Choices - Going from 0 .. n-1 rows, for each row we can place it in all
 * columns except already placed ones (placed indexes).
 * Backtrack- We undo the currently placed queen and move to next column
 * 
 * Checking valid configuration -
 * We are already maintaining the row and column duplicates so let's focus on
 * diagonals, looking at any particular arbitary placement say [i][j] we can
 * check if we have placed along these combinations (check until end of board)
 * - (-1 row, +1 col) -> Top right
 * - (+1 row, +1 col) -> Bottom right
 * - (-1 row, -1 col) -> Top left
 * - (+1 row, -1 col) -> Bottom left
 * 
 * So if any indexes with these combinations exist that's an invalid arrangement
 * But to keep track of this behaviour we can keep two sets basically which will
 * have marked locations. So.. what we can do is for a particular diagonal
 * coming from bottom left and going towards top right if you sum it's indexes
 * it will always be same so.. if we mark this value as visited we can make sure
 * if we can put a queen here or not.
 * 
 * Positive Diagonal (B_L to T_R) = row + col
 * Negative Diagonal (T_L to B_R) = row - col
 */
public class NQueens {
  public static void dfs(List<List<String>> result, int n, List<String> current, int index,
      boolean[] cols, boolean[] posDiagonal, boolean[] negDiagonal) {
    // Base Case
    if (current.size() == n) {
      result.add(new ArrayList<>(current));
      return;
    }

    // Choices
    for (int i = 0; i < n; i++) {
      int posDiagIndex = index + i;
      int negDiagIndex = index - i + n - 1;
      // Check branch
      if (!cols[i] && !posDiagonal[posDiagIndex] && !negDiagonal[negDiagIndex]) {
        // Visit
        cols[i] = true;
        posDiagonal[posDiagIndex] = true;
        negDiagonal[negDiagIndex] = true;
        current.add(".".repeat(i) + "Q" + ".".repeat(n - 1 - i));
        dfs(result, n, current, index + 1, cols, posDiagonal, negDiagonal);

        // Backtrack
        cols[i] = false;
        posDiagonal[posDiagIndex] = false;
        negDiagonal[negDiagIndex] = false;
        current.remove(current.size() - 1);
      }
    }
  }

  public static List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    dfs(result, n, new ArrayList<>(), 0, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1]);
    return result;
  }

  public static void main(String[] args) {
    int n = 4;
    Printer.print(solveNQueens(n));
  }
}
