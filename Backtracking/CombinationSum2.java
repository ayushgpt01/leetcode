package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Utils.Printer;

public class CombinationSum2 {
  public static void performSum(List<List<Integer>> result, List<Integer> current,
      int[] candidates, int target, int index) {
    // Found a possible solution so return
    if (target == 0) {
      // Need to remove duplicates here.
      result.add(new ArrayList<>(current));
      return;
    }

    // Backtrack on we go past target or bounds
    if (target < 0 || index == candidates.length) {
      return;
    }

    // Check all elements after this to see if it reaches target
    current.add(candidates[index]);
    performSum(result, current, candidates, target - candidates[index], index + 1);
    current.remove(current.size() - 1);

    int nextIndex = index + 1;
    while (nextIndex < candidates.length && candidates[nextIndex] == candidates[index]) {
      nextIndex++;
    }

    // Move to next index
    performSum(result, current, candidates, target, nextIndex);
  }

  public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates);
    performSum(result, new ArrayList<>(), candidates, target, 0);

    return result;
  }

  public static void main(String[] args) {
    int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
    int target = 8;

    Printer.print(combinationSum2(candidates, target));
  }
}
