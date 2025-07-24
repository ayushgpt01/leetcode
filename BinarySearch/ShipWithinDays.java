package BinarySearch;

import java.util.Arrays;

public class ShipWithinDays {
  // Actual changed logic depending on question
  public static long loadShipWithCapacity(int[] weights, int capacity) {
    // Here, we needed to load the ship with a particular max capacity per day
    // So, we start with days (1 as on start we are on day 1 to load) and load (ship
    // is empty initially)
    long days = 1;
    int load = 0;

    // Put all weights onto the ship
    for (int weight : weights) {
      // Check if the current weight will exceed days capacity
      if (load + weight > capacity) {
        // If yes then increase the day and then load the weight (reset for next day)
        days++;
        load = weight;
      } else {
        // If no, then we just load the weight easily
        load += weight;
      }
    }

    return days;
  }

  public static int shipWithinDays(int[] weights, int days) {
    // Define answer space
    int low = Arrays.stream(weights).max().getAsInt();
    int high = Arrays.stream(weights).sum();

    // Start binary search - Same format for all just the mid point condition
    // changes
    while (low <= high) {
      // Small overflow optimization
      int mid = low + (high - low) / 2;

      long totalDays = loadShipWithCapacity(weights, mid);

      // If ship is loaded in totalDays with mid capacity which is
      // less than or equal to required days then we might have an answer
      // that might take even more days with lesser capacity
      if (totalDays <= days) {
        // So, we reduce the capacity
        high = mid - 1;
      } else {
        // If we cannot load within days then we need to increase the capacity
        low = mid + 1;
      }
    }

    // Small optimization using opposite polarity
    // Since, we are searching in opposite directions
    // low starts on least favourable answer and ends up on most
    // while high starts on most favourable answer and ends up on least
    return low;
  }

  public static void main(String[] args) {
    int[] weights = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println(shipWithinDays(weights, 5));
  }
}
