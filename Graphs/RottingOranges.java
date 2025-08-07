package Graphs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class RottingOranges {
  public static int orangesRotting(int[][] grid) {
    if (grid.length == 0) {
      return -1;
    }
    int time = -1;

    int[][] rotDirections = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    while (true) {
      Deque<List<Integer>> queue = new ArrayDeque<>();

      // Get all the oranges that will rot in this unit time
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          if (grid[i][j] == 2) {
            for (int k = 0; k < rotDirections.length; k++) {
              int rowD = i + rotDirections[k][0];
              int colD = j + rotDirections[k][1];

              if (rowD >= 0 && colD >= 0 && rowD < grid.length && colD < grid[0].length && grid[rowD][colD] == 1) {
                queue.offer(List.of(rowD, colD));
              }
            }
          }
        }
      }

      time++;

      // Nothing rotted this time
      if (queue.isEmpty()) {
        break;
      }

      // Rot the current fresh oranges
      while (!queue.isEmpty()) {
        List<Integer> indexes = queue.poll();
        grid[indexes.get(0)][indexes.get(1)] = 2;
      }
    }

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          return -1;
        }
      }
    }

    return time;
  }

  public static void main(String[] args) {
    int[][] grid = { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
    System.out.println(orangesRotting(grid));
  }
}
