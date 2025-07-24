package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Utils.Printer;

public class Subsets2 {
  public static void getSubset(List<List<Integer>> result, List<Integer> current, int[] nums, int index) {
    // Base case
    if (index == nums.length) {
      result.add(new ArrayList<>(current));
      return;
    }

    // Include current
    current.add(nums[index]);
    getSubset(result, current, nums, index + 1);

    // Backtrack
    current.remove(current.size() - 1);

    int nextIndex = index + 1;
    while (nextIndex < nums.length && nums[nextIndex] == nums[index]) {
      nextIndex++;
    }

    // Don't include current
    getSubset(result, current, nums, nextIndex);
  }

  public static List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    getSubset(result, new ArrayList<>(), nums, 0);
    return result;
  }

  public static void main(String[] args) {
    int[] nums = { 4, 4, 4, 1, 4 };
    Printer.print(subsetsWithDup(nums));
  }
}
