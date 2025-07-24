package SlidingWindow;

import java.util.HashMap;

/**
 * Intuition - This is also a sliding window problem where we can figure out the
 * distinct k in a window.
 * How it needs to work is that, I will start increasing the window until i have
 * k exact numbers. Once i have that i can look at next character in array to
 * see if it increases the distinct integers count. If it does we start
 * shrinking until left reaches right. Then we start exapanding again and so
 * on.. Throughout all these increment and decrement we maintain a set of all
 * numbers encountered yet. If that's equal to k we increment the result count
 * by 1. Since each of those is a valid sub array.
 * 
 * Okay, I cannot just do shrinking like that since let's say there are
 * consecutive characters after the new distinct character that are also valid,
 * this approach doesn't considers them. So.. we cannot really directly figure
 * out the exact distinct window in a single look of the array.
 * 
 * So, to resolve this stuck with not being able to determine where to move the
 * pointer, we need to look at the problem again. Since, it's asking k distinct
 * sub arrays, we can instead get count of at most k elements. And all the sub
 * array with k most will contain k exact and k - 1 exact as well. So, we can
 * find lessEqK = k + lessEqKminus. Rephrasing it, we get k = lessEqK -
 * lessEqKminus.
 */
public class SubArrayWithKDistinct {
  public static int countSubArrayWithAtMostKDistinct(int[] nums, int k) {
    int left = 0;
    HashMap<Integer, Integer> distinctIntegers = new HashMap<>();
    int result = 0;

    for (int right = 0; right < nums.length; right++) {
      distinctIntegers.put(nums[right], distinctIntegers.getOrDefault(nums[right], 0) + 1);

      while (distinctIntegers.size() > k) {
        distinctIntegers.put(nums[left], distinctIntegers.get(nums[left]) - 1);

        if (distinctIntegers.get(nums[left]) == 0) {
          distinctIntegers.remove(nums[left]);
        }

        left++;
      }

      result += (right - left + 1);
    }

    return result;
  }

  public static int subarraysWithKDistinct(int[] nums, int k) {
    int kMost = countSubArrayWithAtMostKDistinct(nums, k);
    int kMinusMost = countSubArrayWithAtMostKDistinct(nums, k - 1);

    return kMost - kMinusMost;
  }

  public static void main(String[] args) {
    int[] nums = { 1, 2, 1, 2, 3 };
    int k = 2;
    System.out.println(subarraysWithKDistinct(nums, k));

    int[] nums2 = { 1, 2, 1, 3, 4 };
    int k2 = 3;
    System.out.println(subarraysWithKDistinct(nums2, k2));
  }
}
