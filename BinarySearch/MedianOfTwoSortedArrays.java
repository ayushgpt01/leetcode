package BinarySearch;

import Utils.Printer;

/**
 * Intuition - Median of an array is basically the middle element right? So, we
 * can break the array taking middle element as a pivot into two parts left
 * partition and right partition. Now, since this array is sorted left partition
 * will always have elements less than or equal to pivot and right partition
 * will always have elements more than or equal to pivot.
 * 
 * Something like this - [1,2,3,4,5,6,7]
 * Here, [1,2,3] is left partition
 * 4 is median
 * [5,6,7] is right partition
 * 
 * And if we can figure out the left parition we know what median will be. How?
 * Well, median can be two things, if length of array is odd then it's always
 * left parition's next element and if length of array is even then it's (left
 * partition's last element + right partition's first element) / 2
 * 
 * Using this what we can do is in the two given arrays, we take the first
 * arrays middle element, then it's possible that it's first n/2 elements are
 * part of entire arrays left partition. How do we check this though?
 * For that, we find the total number of elements that can be in the median by
 * checking with total length of both arrays combined let's say leftLength. This
 * left length gives us length required from second array as we can do
 * leftLength - length of left partition from first array.
 * 
 * Now to check whether this left partition is valid or not. We can do this by
 * checking that last element of each array is less than or equal to last + 1
 * element in other array. This basically checks if we put the elements into one
 * array are they still in same ascending order or not. If they are then it's a
 * valid left partition and we can figure out median by checking the next
 * elements based on if the combined array length is even or odd.
 * 
 * -> Combined array length even - We will get max(lastLPA, lastLPB) +
 * min(firstRPA, firstRPB) / 2
 * -> Combined array length odd - We will get min(lastLPANext, lastLPBNext).
 */
public class MedianOfTwoSortedArrays {
  public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
      return findMedianSortedArrays(nums2, nums1);
    }

    int m = nums1.length;
    int n = nums2.length;

    int total = m + n;
    int half = (total + 1) / 2;

    int left = 0;
    int right = m;

    while (left <= right) {
      int partitionSizeA = left + (right - left) / 2;
      int paritionSizeB = half - partitionSizeA;

      // Need to handle zero length and not present index cases here
      int leftPEndA = partitionSizeA - 1 < 0 ? Integer.MIN_VALUE : nums1[partitionSizeA - 1];
      int rightPStartA = partitionSizeA >= m ? Integer.MAX_VALUE : nums1[partitionSizeA];

      int leftPEndB = paritionSizeB - 1 < 0 ? Integer.MIN_VALUE : nums2[paritionSizeB - 1];
      int rightPStartB = paritionSizeB >= n ? Integer.MAX_VALUE : nums2[paritionSizeB];

      // Check if valid partition
      if (leftPEndA <= rightPStartB && leftPEndB <= rightPStartA) {
        // We have a hit, check if total length is even or odd
        if (total % 2 == 0) {
          return (double) (Math.max(leftPEndA, leftPEndB) + Math.min(rightPStartA, rightPStartB)) / 2.0;
        }

        return (double) Math.max(leftPEndA, leftPEndB);
      } else if (rightPStartA < leftPEndB) {
        // The item at start of A's right partition is less than last element of B's
        // left partition. This means there are still elements in A that are valid in
        // left partition, so we shift the left more to add more elements in A.
        left = partitionSizeA + 1;
      } else {
        // The item at end of A's left parition is greater than first element of B's
        // right partition. That means there are still elements in B that are valid in
        // left paritition so we decrease elements from A by shifting right.
        right = partitionSizeA - 1;
      }
    }

    return 0;
  }

  public static void main(String[] args) {
    int[] nums1 = { 1, 3 };
    int[] nums2 = { 2 };
    // int[] nums1 = { 1, 2 };
    // int[] nums2 = { 3, 4 };

    Printer.print(findMedianSortedArrays(nums1, nums2));
  }
}
