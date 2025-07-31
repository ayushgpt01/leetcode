package Graphs;

import Utils.Printer;

/**
 * Problem - https://leetcode.com/problems/max-area-of-island
 * So, for this one it's quite simple once we know what's an island. An island
 * can be found by looking at each node until we find 1. Then we visit the
 * island by doing a dfs on all connected routes and marking them as visited.
 * Fo this problem all we need to do is calculate the area of island alongside
 * the traversal and then return the maximum area encountered so far.
 */
public class MaxAreaOfIslands {
  private static int getIslandArea(int[][] grid, int row, int col) {
    if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
      return 0;
    }

    if (grid[row][col] == 0) {
      return 0;
    }

    grid[row][col] = 0;

    int area = 1;
    area += getIslandArea(grid, row + 1, col);
    area += getIslandArea(grid, row, col + 1);
    area += getIslandArea(grid, row - 1, col);
    area += getIslandArea(grid, row, col - 1);

    return area;
  }

  public static int maxAreaOfIsland(int[][] grid) {
    int m = grid.length;
    if (m == 0)
      return 0;
    int n = grid[0].length;
    int maxAreaOfIslands = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // Found an island
        if (grid[i][j] == 1) {
          maxAreaOfIslands = Math.max(maxAreaOfIslands, getIslandArea(grid, i, j));
        }
      }
    }

    return maxAreaOfIslands;
  }

  public static void main(String[] args) {
    int[][] grid = {
        { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
        { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
        { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } };

    Printer.print(maxAreaOfIsland(grid));
  }
}