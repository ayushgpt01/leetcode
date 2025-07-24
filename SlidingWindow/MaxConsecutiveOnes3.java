package SlidingWindow;

public class MaxConsecutiveOnes3 {
  public static int longestOnes(int[] nums, int k) {
    // Initialise a window
    int left = 0;
    int result = 0;
    int freq = 0;

    // We start moving from left to right to find a window with k flipable 0's
    for (int right = 0; right < nums.length; right++) {
      // If we find a 0 we update the frequency to track the number of 0's in current
      // window
      if (nums[right] == 0) {
        freq++;
      }

      // If frequency is more than required amount of flips, then we need to shrink
      // the window
      while (freq > k) {
        // Store the current number so we can check it's condition later
        int currentNum = nums[left];
        // Update the left first
        left++;

        // Then check if current number was 0 and update the frequency
        if (currentNum == 0) {
          freq--;
          // We break out of the loop since as soon as we remove one 0 we have a valid
          // window again
          break;
        }
      }

      // Store the longest window encountered yet
      result = Math.max(result, right - left + 1);
    }

    return result;
  }

  public static void main(String[] args) {
    int[] nums = { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
    int k = 2;
    System.out.println(longestOnes(nums, k));

    int[] nums2 = { 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 };
    int k2 = 3;
    System.out.println(longestOnes(nums2, k2));

  }
}
