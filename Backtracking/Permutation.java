package Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Utils.Printer;

public class Permutation {
  public static void getPermutation(List<List<Integer>> result, List<Integer> current,
      HashSet<Integer> visited, int[] nums) {
    // Base Case
    if (current.size() == nums.length) {
      result.add(new ArrayList<>(current));
      return;
    }

    // Choices
    for (int i = 0; i < nums.length; i++) {
      // Constraint
      if (!visited.contains(i)) {
        // Explore
        current.add(nums[i]);
        visited.add(i);
        getPermutation(result, current, visited, nums);

        // Backtrack
        current.remove(current.size() - 1);
        visited.remove(i);
      }
    }
  }

  public static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    getPermutation(result, new ArrayList<>(), new HashSet<>(), nums);
    return result;
  }

  public static void main(String[] args) {
    int[] nums = { 1, 2, 3 };
    Printer.print(permute(nums));
  }
}
