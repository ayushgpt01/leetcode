package BinarySearch;

import Utils.Printer;

public class RotatedSortedArray {
  public static int findMin(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    int min = Integer.MAX_VALUE;

    while (left <= right) {
      // Array is strictly decreasing then there can't be any more minimums in between
      if (nums[left] < nums[right]) {
        min = Math.min(min, nums[left]);
        break;
      }

      int mid = left + (right - left) / 2;
      min = Math.min(min, nums[mid]);

      // Here, we are looking for the direction of pivot point (min element). Since at
      // pivot the previous element will be larger and next element smaller, if we get
      // into a situation where the mid element is greater than left and window is not
      // all increasing elements then we need to look to right
      // Also if mid and left are on same position then also we need to move the left
      // like [2,1] where mid and left are on 0 and num[left] > num[right] then, the
      // answer must be to the right as well.
      if (nums[mid] >= nums[left]) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return min;
  }

  public static void main(String[] args) {
    int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
    Printer.print(findMin(nums));
  }
}
