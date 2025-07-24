package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * We need to find triplets in an array that are different numbers and their sum
 * is zero (0). To approach this,
 * 
 * 1. Brute force - We can find all the unique triplets in the array and then
 * sum each of them to see which ones have 0 submission, then return them. Time
 * complexity of this will be O(n^3) for each number in triplet. Space
 * complexity will be O(n^3) for the hashmap to deduplicate
 * 
 * 2. Optimal approach - For the optimal approach, one thing to note is that we
 * need unique triplets and order doesn't matter. So, if we sort the array we
 * can utlise something like a sliding window to do it. Basically, triplets sum
 * will result in 0, which is doable if from triplets we have some negative,
 * zero and some positive numbers. This means if i take numbers from left of
 * sorted array it will be minimum while on right will be maximum then the best
 * chance of getting a triplet that results in a zero is submission of left and
 * right items. Now, for pointers we need three. What we can do is have a
 * pointer go through entire array while we keep one on left and one on right.
 * 
 * How it happens is this -
 * - We start a pointer i from 0 to n-2 since for last two items we can't have
 * any new triplets.
 * - Then we take a left point after i (i+1) and right point on last element.
 * These will be used to check the triplets for sum.
 * - Then we start moving left and right until they cross each other and check
 * if any values are summing to zero. Here the sorted array comes in handy
 * -- If sum < 0, then we need to add bigger digits and we move left one ahead
 * -- If sum > 0, then we need to add smaller digits and we move right one
 * behind
 * -- If sum == 0, then we found the triplet and we add the value to result. But
 * there are some considerations to make for duplicates -
 * --- First we have to skip over same values for left and right when we find an
 * answer because they will have same triplets as current left and right.
 * --- Second we need to skip over duplicates in i as well since they will also
 * have same triplets.
 */
public class ThreeSum {
  public static List<List<Integer>> bruteForce(int[] sums) {
    HashMap<String, List<Integer>> triplets = new HashMap<>();

    for (int i = 0; i < sums.length; i++) {
      for (int j = i + 1; j < sums.length; j++) {
        for (int k = j + 1; k < sums.length; k++) {
          if (sums[i] + sums[j] + sums[k] == 0) {
            List<Integer> list = new ArrayList<>(Arrays.asList(sums[i], sums[j], sums[k]));
            Collections.sort(list);

            StringBuilder keyBuilder = new StringBuilder();
            keyBuilder.append(list.get(0));
            keyBuilder.append(list.get(1));
            keyBuilder.append(list.get(2));

            String key = keyBuilder.toString();
            if (!triplets.containsKey(key)) {
              triplets.put(key, list);
            }
          }
        }
      }
    }

    return new ArrayList<>(triplets.values());
  }

  public static List<List<Integer>> solution(int[] nums) {
    // Answer space
    List<List<Integer>> returnList = new ArrayList<>();

    // If we don't have enough elements for making a triplet then return
    if (nums.length < 3) {
      return returnList;
    }

    // Sort the array so that we can utilize a double pointer approach
    Arrays.sort(nums);

    // Go through the entire array once
    for (int i = 0; i < nums.length - 2; i++) {
      // If current and previous elements are same we skip them since first one
      // is already calculated for and we don't want duplicates in triplets.
      // This is possible only because array was sorted earlier and all the
      // duplicates are in same place
      if (i > 0 && nums[i] == nums[i - 1])
        continue;

      // Pointers for next element and last element
      int left = i + 1;
      int right = nums.length - 1;

      // Until we collide on pointers
      while (left < right) {
        // Calculate the triplet between current element, and pointers
        int triplet = nums[i] + nums[left] + nums[right];
        // If we found a valid result
        if (triplet == 0) {
          // Add it to the list
          returnList.add(List.of(nums[i], nums[left], nums[right]));

          // Now we start removing the duplicates if any from left
          while (left < right && nums[left] == nums[left + 1]) {
            left++;
          }

          // We remove duplicates if any from right
          while (left < right && nums[right] == nums[right - 1]) {
            right--;
          }

          // We move the window inside
          left++;
          right--;
        } else if (triplet < 0) {
          // If we didn't find a valid triplet and value is less then 0,
          // we need to increase the window size. This is possible because of
          // sorted array. Next element will be bigger and take triplet to closer
          // to 0 from negative
          left++;
        } else {
          // If value is greater than 0 then we need to reduce the windiw size
          // from high value to 0
          right--;
        }
      }
    }

    return returnList;
  }

  public static void main(String[] args) {
    int[] nums = { -1, 0, 1, 2, -1, -4 };

    System.out.println(solution(nums).toString());
  }
}
