package BinarySearch;

import Utils.Printer;

public class Search2DMatrix {
  public static boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length;
    int n = matrix[0].length;
    int totalLength = m * n;
    int left = 0;
    int right = totalLength - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      // This number we have to figure out in 2d matrix. So i need to map out linear
      // index with m * n
      int i = mid >= n ? mid / n : 0;
      int j = mid >= n ? mid % n : mid;

      int num = matrix[i][j];

      if (target == num) {
        return true;
      } else if (target < num) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    int[][] matrix = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 } };
    int target = 3;

    Printer.print(searchMatrix(matrix, target));
  }
}
