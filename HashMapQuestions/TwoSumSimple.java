package HashMapQuestions;

import java.util.HashMap;

public class TwoSumSimple {
  public static int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> complementIndex = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];

      if (complementIndex.containsKey(complement)) {
        return new int[] { complementIndex.get(complement), i };
      }

      complementIndex.put(nums[i], i);
    }

    return new int[2];
  }

  public static void main(String[] args) {
    int[] nums = { 3, 2, 4 };
    int target = 6;
    int[] ans = twoSum(nums, target);
    System.out.println(ans[0] + " " + ans[1]);
  }
}
