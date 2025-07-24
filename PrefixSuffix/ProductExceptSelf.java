package PrefixSuffix;

import Utils.Printer;

/**
 * So, used the hint for prefix and suffix but got this solution by creating
 * suffix and prefix arrays for each i, then i build up the answer space by
 * multiplying each prefix product with suffix product
 * Time Complexity - O(n) and space complexity - O(n)
 * 
 * However, I can solve it in O(1) as well if I consider answer space as my
 * suffix and nums as my prefix array. Since the problem states that answer
 * isn't considered for space complexity then it's a valid O(1) solution.
 * 
 * Note - I won't do this way in actual production environments. This has
 * multiple problems such as -
 * - Updating input
 * - Complicated logic when this can be easily solved by using division
 * - int[] is not good for products as it may exceed max length, and we should
 * use BigInteger
 */
public class ProductExceptSelf {
  public static int[] productExceptSelf(int[] nums) {
    int[] answer = new int[nums.length];

    int currentPrefix = 1;
    for (int i = 0; i < nums.length; i++) {
      answer[i] = currentPrefix;
      currentPrefix = nums[i] * currentPrefix;
    }

    int currentSuffix = 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      int num = nums[i];
      nums[i] = currentSuffix;
      currentSuffix = num * currentSuffix;
    }

    for (int i = 0; i < answer.length; i++) {
      answer[i] = answer[i] * nums[i];
    }

    return answer;
  }

  public static void main(String[] args) {
    int[] nums = { 1, 2, 3, 4 };
    // prefix - [1, 1, 2, 6] & suffix - [24, 12, 4, 1] & answer - [24, 12, 8, 6]
    Printer.print(productExceptSelf(nums));
  }
}
