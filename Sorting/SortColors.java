package Sorting;

/**
 * Intuition - Looking at the question this is a classic sorting into ascending
 * order problem. The easiest way to sort the array in place is to use bubble
 * sort but that is very inefficient with O(n^2) time complexity. Hence, for
 * most optimal sorting we can use quick sort which will sort it in O(nlogn).
 * 
 * For this particular problem though there is another sorting algorithm called
 * Dutch National flag algorithm. This is most optimal with O(n) time and O(1)
 * space complexity. It requires three pointers and we break the pointers like
 * this - 0..low-1, low..mid-1, high..n-1. Then we move the mid from 0..n and
 * while moving if we get,
 * - 0 -> we swap low and mid and then move both
 * - 1 -> we don't do anything and move mid
 * - 2 -> we swap with high and move high back
 * Stop when mid is greater than high
 * 
 * However, I have solved this one using count map, which basically works like
 * in an array with only 3 distinct values we will have same number of 0, 1 and
 * 2 even after sorting so.. I can just store the frequency by looping over it
 * once, then again loop over it to assign the values decreasing the count in
 * each turn. This takes O(2N) = O(N) time and O(1) space. However, for range
 * larger than a million it will have issues with memory but that will happen
 * with DNF as well.
 */
public class SortColors {
  public static int partition(int[] nums, int low, int high) {
    int pivot = nums[low];
    int i = low - 1;
    int j = high + 1;

    while (true) {
      do {
        i++;
      } while (nums[i] < pivot);

      do {
        j--;
      } while (nums[j] > pivot);

      if (i >= j)
        return j;

      swap(nums, i, j);
    }
  }

  public static void swap(int[] nums, int index1, int index2) {
    int temp = nums[index1];
    nums[index1] = nums[index2];
    nums[index2] = temp;
  }

  public static void quickSort(int[] nums, int low, int high) {
    if (low < high) {
      int pivot = partition(nums, low, high);

      quickSort(nums, low, pivot);
      quickSort(nums, pivot + 1, high);
    }
  }

  public static void sortSolution(int[] nums) {
    quickSort(nums, 0, nums.length - 1);
  }

  public static void sortColors(int[] nums) {
    int[] counts = new int[3];
    for (int i : nums) {
      counts[i]++;
    }

    for (int i = 0; i < nums.length; i++) {
      if (counts[0] != 0) {
        nums[i] = 0;
        counts[0]--;
      } else if (counts[1] != 0) {
        nums[i] = 1;
        counts[1]--;
      } else if (counts[2] != 0) {
        nums[i] = 2;
        counts[2]--;
      }
    }
  }

  public static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print((i == arr.length - 1) ? arr[i] + "\n" : arr[i] + ",");
    }
  }

  public static void main(String[] args) {
    int[] nums = { 2, 0, 2, 1, 1, 0 };
    sortColors(nums);
    printArray(nums);

    int[] nums2 = { 2, 0, 1 };
    sortColors(nums2);
    printArray(nums2);
  }
}
