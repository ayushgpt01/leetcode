package HashMapQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * My original intuition was to sort the list and then check the consecutive
 * elements length but that's atleast nlogn time complexity.
 * 
 * A better approach after some thinking is to make a map and store the links to
 * other members, sort of like a chain with 1 difference exactly. This will get
 * me distinct elements for each chain. However, the problem is building this
 * chain. Okay what if i store the required elements first for each i?
 * 
 * Wait, so in the sequence what's the condition for a valid inital element,
 * it's predecessor shouldn't be in the array. So, if i find all the initial
 * elements, i can create the chain from these and find the longest length
 * chain.
 */
public class LongestConsecutive {
  public static int longestConsecutive(int[] nums) {
    Set<Integer> uniqueSet = Arrays.stream(nums)
        .boxed()
        .collect(Collectors.toCollection(HashSet::new));

    ArrayList<Integer> initialElements = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      if (!uniqueSet.contains(nums[i] - 1)) {
        initialElements.add(nums[i]);
      }
    }

    int maxResult = 0;
    for (int num : initialElements) {
      int result = 1;
      uniqueSet.remove(num);
      int nextNum = num + 1;
      while (uniqueSet.contains(nextNum)) {
        uniqueSet.remove(nextNum);
        result++;
        nextNum++;
      }

      maxResult = Math.max(maxResult, result);
    }

    return maxResult;
  }

  public static void main(String[] args) {
    int[] nums = { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 };

    System.out.println(longestConsecutive(nums));
  }
}
