package HashMapQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.Printer;

/**
 * Problem - https://leetcode.com/problems/rearranging-fruits/
 * This is a very tricky one and hard to grasp. The core logic lies in these
 * points
 * 1. The effective freqeuncy of a fruit in both baskers should be even
 * otherwise it cannot be distributed equally in two baskets.
 * 2. When swapping we should take the cheapest swappable fruit from both
 * baskets.
 * 3. Even if we choose cheapest fruits they might still cost a lot so, we can
 * use the cheapest fruit in basket as a currency and use it to swap the fruits
 * in least cost which will be 2 * min_overall_fruit.
 * 4. To get the cheapest fruit we could use two priority queues but that would
 * be inefficient so, a better way is to store every element that needs to be
 * swapped in a list and sort it. Then we can just calculate the cost easily.
 * 5. When swapping we don't need to perform the actual swapping but instead we
 * can just calculate the cost, and in a list of sorted items that number of
 * swaps needed will be half the list always.
 */
public class RearrangingFruits {
  public static long minCost(int[] basket1, int[] basket2) {
    Map<Integer, Integer> totalCounts = new HashMap<>();
    Map<Integer, Integer> count1 = new HashMap<>();

    for (int val : basket1) {
      totalCounts.put(val, totalCounts.getOrDefault(val, 0) + 1);
      count1.put(val, count1.getOrDefault(val, 0) + 1);
    }

    for (int val : basket2) {
      totalCounts.put(val, totalCounts.getOrDefault(val, 0) + 1);
    }

    int minOverall = Integer.MAX_VALUE;
    List<Long> fruitsToSwap = new ArrayList<>();

    for (Map.Entry<Integer, Integer> entry : totalCounts.entrySet()) {
      if (entry.getValue() % 2 != 0) {
        return -1;
      }
      int fruit = entry.getKey();

      minOverall = Math.min(minOverall, fruit);
      int diff = count1.getOrDefault(fruit, 0) - (entry.getValue() / 2);
      for (int i = 0; i < Math.abs(diff); i++) {
        fruitsToSwap.add((long) fruit);
      }
    }

    Collections.sort(fruitsToSwap);
    long cost = 0;
    int swaps = fruitsToSwap.size() / 2;
    for (int i = 0; i < swaps; i++) {
      cost += Math.min(fruitsToSwap.get(i), 2 * minOverall);
    }

    return cost;
  }

  public static void main(String[] args) {
    int[] basket1 = { 4, 4, 4, 4, 3 };
    int[] basket2 = { 5, 5, 5, 5, 3 };

    Printer.print(minCost(basket1, basket2));
  }
}
