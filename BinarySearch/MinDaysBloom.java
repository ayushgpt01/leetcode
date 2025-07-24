package BinarySearch;

import java.util.Arrays;

public class MinDaysBloom {
  private static long calculateBouquet(int[] bloomDay, int day, int flower) {
    long count = 0;
    long current = 0;
    for (int i : bloomDay) {
      current++;

      if (i > day) {
        current = 0;
        continue;
      }

      if (current == flower) {
        count++;
        current = 0;
      }

    }

    return count;
  }

  public static int minDays(int[] bloomDay, int m, int k) {
    if ((long) m * k > bloomDay.length)
      return -1;

    int low = -1;
    int high = Arrays.stream(bloomDay).max().getAsInt();

    while (low <= high) {
      int mid = low + (high - low) / 2;
      // Count the number of bouquets that can be made in mid day
      long count = calculateBouquet(bloomDay, mid, k);

      // If we made m or more than m bouquets on mid day then
      if (count >= m) {
        // There might be a day lower than this that has better
        high = mid - 1;
      } else {
        // We couldn't create at least m bouquets then,
        // We need to increase the days count as maybe greater days
        // we might have more bloomed flowers
        low = mid + 1;
      }
    }

    return low;
  }

  public static void main(String[] args) {
    int[] bloomDay = { 7, 7, 7, 7, 12, 7, 7 };
    System.out.println(minDays(bloomDay, 2, 3));
  }
}
