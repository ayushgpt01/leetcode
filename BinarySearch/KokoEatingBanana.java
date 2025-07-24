package BinarySearch;

import java.util.Arrays;

public class KokoEatingBanana {
  public static int minEatingSpeed(int[] piles, int h) {
    int left = 1;
    int right = Arrays.stream(piles).max().getAsInt();
    int result = right;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      long totalHours = 0;
      for (int pileCount : piles) {
        totalHours += (pileCount + mid - 1) / mid;
      }

      if (totalHours <= h) {
        result = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    int[] piles = { 805306368, 805306368, 805306368 };
    System.out.println(minEatingSpeed(piles, 1000000000));
  }
}
