package BinarySearch;

import Utils.Printer;

/**
 * This one can be used as a general for solving rotated sorted arrays.
 * The core intuition behind it is when searching there's a pivot (min element),
 * left sorted array, right sorted array. In a regular sorted array the left
 * array is of length 0, pivot is at index 0 and right is of length n.
 * 
 * Using this, the conditions that come up in binary search are left most point
 * is smaller or equal to mid, that means items are in increasing order for left
 * to mid. Then mid is in the left array. And special case is when left == mid
 * then it's normal array (Pivot at 0).
 * 
 * Second is that if above condition is not true then left item is greater then
 * mid that means the right array is sorted and we are on of these items
 */
public class SearchInRotatedSortedArray {
  public static int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] == target) {
        return mid;
      }

      if (nums[left] <= nums[mid]) {
        if (nums[left] <= target && nums[mid] > target) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else {
        if (nums[right] >= target && nums[mid] < target) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    int[] nums = { 8, 1, 2, 3, 4, 5, 6, 7 };
    int target = 5;

    Printer.print(search(nums, target));
  }
}
