package PrefixSuffix;

import java.util.HashMap;

public class SubArraySumEqualsK {
  public static int subarraySum(int[] nums, int k) {
    int count = 0;
    int current = 0;
    HashMap<Integer, Integer> freqMap = new HashMap<>();
    freqMap.put(0, 1);

    for (int i = 0; i < nums.length; i++) {
      current += nums[i];

      if (freqMap.containsKey(current - k)) {
        count += freqMap.get(current - k);
      }

      freqMap.put(current, freqMap.getOrDefault(current, 0) + 1);
    }

    return count;
  }

  public static void main(String[] args) {
    System.out.println(subarraySum(new int[] { 1, 1, 1 }, 2));
  }
}
