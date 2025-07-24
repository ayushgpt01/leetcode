package LinkedList;

import Utils.Printer;

public class FindDuplicateNumber {
  public static int findDuplicate(int[] nums) {
    int fast = nums[0];
    int slow = nums[0];

    do {
      fast = nums[nums[fast]];
      slow = nums[slow];
    } while (fast != slow);

    int finder = nums[0];
    while (finder != slow) {
      finder = nums[finder];
      slow = nums[slow];
    }

    return finder;
  }

  public static void main(String[] args) {
    int[] nums = { 1, 3, 4, 2, 2 };
    Printer.print(findDuplicate(nums));
  }
}
