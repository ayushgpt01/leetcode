package Graphs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import Utils.Printer;

/**
 * Problem - https://leetcode.com/problems/walls-and-gates/
 * The core of this problem is in knowing that we can start the BFS from
 * multiple points to find the shortest distance.
 */
public class WallandGates {
  public static void solveGrid(int[][] grid) {
    if (grid.length == 0)
      return;

    Deque<List<Integer>> indexQueue = new ArrayDeque<>();

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 0) {
          indexQueue.offer(List.of(i, j));
        }
      }
    }

    int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    while (!indexQueue.isEmpty()) {
      List<Integer> list = indexQueue.poll();
      int row = list.get(0);
      int col = list.get(1);

      for (int i = 0; i < directions.length; i++) {
        int newRow = row + directions[i][0];
        int newCol = col + directions[i][1];

        if (newCol < 0 || newRow < 0 || newCol >= grid[0].length || newRow >= grid.length
            || grid[newRow][newCol] != Integer.MAX_VALUE) {
          continue;
        }

        grid[newRow][newCol] = grid[row][col] + 1;
        indexQueue.offer(List.of(newRow, newCol));
      }
    }
  }

  public static void main(String[] args) {
    int[][] grid = {
        { Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE },
        { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1 },
        { Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1 },
        { 0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE },
    };

    Printer.print(grid);
    solveGrid(grid);
    Printer.print(grid);
  }
}
