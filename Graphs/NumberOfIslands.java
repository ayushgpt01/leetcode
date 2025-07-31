package Graphs;

import Utils.Printer;

/**
 * Problem - https://leetcode.com/problems/number-of-islands/
 * So, the crux of this problem lies in how we can start at any point in an
 * island and visit all adjacent nodes with a simple graph traversal.
 * So in this we visit all the nodes in the grid and whenever we arrive on a 1
 * we know it's start of an island. So, we increment the island count and then
 * mark the entire island as visited. Then we continue doing this for all nodes
 * and we'll have the final number of islands.
 */
public class NumberOfIslands {
  private static void markIslandVisited(char[][] grid, int row, int col) {
    if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
      return;
    }

    // If we arrive at water return
    if (grid[row][col] != '1') {
      return;
    }

    // We're basically marking current node as water to make sure it isn't counted
    // again
    grid[row][col] = '0';

    // Mark the four directions as visited as well
    markIslandVisited(grid, row + 1, col);
    markIslandVisited(grid, row - 1, col);
    markIslandVisited(grid, row, col + 1);
    markIslandVisited(grid, row, col - 1);
  }

  public static int numIslands(char[][] grid) {
    int m = grid.length;
    if (m == 0)
      return 0;

    int n = grid[0].length;
    int numIslands = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          numIslands++;
          markIslandVisited(grid, i, j);
        }
      }
    }

    return numIslands;
  }

  public static void main(String[] args) {
    char[][] grid = {
        { '1', '1', '0', '0', '0' },
        { '1', '1', '0', '1', '0' },
        { '0', '0', '1', '0', '0' },
        { '0', '0', '0', '1', '1' }
    };
    Printer.print(numIslands(grid));
  }
}
