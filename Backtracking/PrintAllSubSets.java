package Backtracking;

import java.util.ArrayList;
import java.util.List;

import Utils.Printer;

/**
 * For this type of questions where we are finding all combinations we can use a
 * decision tree. Now to generate all subsets the decision is whether to take an
 * element or not.
 */
public class PrintAllSubSets {
  public static void dfs(int i, int[] nums, List<List<Integer>> result, List<Integer> current) {
    if (i > nums.length - 1) {
      result.add(new ArrayList<>(current));
      return;
    }

    // Add nums[i]
    current.add(nums[i]);
    dfs(i + 1, nums, result, current);

    // We don't add nums[i]
    current.removeLast();
    dfs(i + 1, nums, result, current);
  }

  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(0, nums, result, new ArrayList<>());
    return result;
  }

  public static void main(String[] args) {
    int[] nums = new int[] { 1, 2, 3 };

    Printer.print(subsets(nums));
  }
}
