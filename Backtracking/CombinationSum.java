package Backtracking;

import java.util.ArrayList;
import java.util.List;

import Utils.Printer;

public class CombinationSum {
  public static void performSum(List<List<Integer>> result, List<Integer> values, int[] candidates, int target,
      int index) {
    // Base condition - We overshoot the target
    if (target == 0) {
      result.add(new ArrayList<>(values));
      return;
    }

    if (index == candidates.length || target < 0) {
      return;
    }

    // Add the number itself
    values.add(candidates[index]);
    performSum(result, values, candidates, target - candidates[index], index);
    values.remove(values.size() - 1);

    // Add the next number
    performSum(result, values, candidates, target, index + 1);
  }

  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();

    // Go through each candidate.
    performSum(result, new ArrayList<>(), candidates, target, 0);

    return result;
  }

  public static void main(String[] args) {
    int[] candidates = { 2, 3, 6, 7 };
    int target = 7;
    Printer.print(combinationSum(candidates, target));
  }
}
