package Sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TopKFrequent {
  public static int[] topKFrequent(int[] nums, int k) {
    // Problem is straight forward with storing frequencies and picking the largest
    // k but the data structure is the issue. Max heap can give the biggest value,
    // so i am thinking of storing the a pair in it with value:count. In java i can
    // store that with a array. But how do i assign the frequencies in optimal way?

    // But that won't work as well. Okay let's say i store everything in a map for
    // frequencies, now how do i get the largest k elements from this map? Or wait,
    // i need to find largest k frequencies not elements themselves. So.. If i find
    // the largest frequencies I can figure out the numbers as well (in a n length
    // loop over entires of map) and return those. But the space complexity is
    // getting out of hands for this one.

    // Okay, then if i store everything in a map first along with a min-heap of k
    // elements, i can update this min heap each time a new element is iterated upon
    // by checking if the min kth element has less frequency then current, if it
    // does then we replace them. This way i can go through the array once for
    // building frequencies, then once more to built out the priority queue. Then to
    // build out the final result array. That's 3 * O(n) time and 2 * O(n) space.

    // So, we can also use bucket sort for this since while initalizing the map we
    // can store the max frequency and then intitalize an array of int arrays with
    // length max frequency, then we store each item in map using frequncy as index
    // and then just go from reverse of the array to get first k elements.
    HashMap<Integer, Integer> freqMap = new HashMap<>();
    int maxFreq = 0;
    for (int i : nums) {
      freqMap.put(i, freqMap.getOrDefault(i, 0) + 1);
      maxFreq = Math.max(maxFreq, freqMap.get(i));
    }

    ArrayList<ArrayList<Integer>> bucket = new ArrayList<>(maxFreq + 1);
    for (int i = 0; i < maxFreq + 1; i++) {
      bucket.add(null);
    }

    for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
      int num = entry.getKey();
      int freq = entry.getValue();

      if (bucket.get(freq) == null) {
        bucket.set(freq, new ArrayList<>());
      }

      bucket.get(freq).add(num);
    }

    int[] result = new int[k];
    int j = 0;
    for (int i = bucket.size() - 1; i >= 0; i--) {
      ArrayList<Integer> list2 = bucket.get(i);

      if (list2 != null) {
        for (Integer integer : list2) {
          result[j] = integer;
          j++;

          if (j >= k) {
            break;
          }
        }

      }

      if (j >= k) {
        break;
      }
    }

    return result;
  }

  public static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print((i == arr.length - 1) ? arr[i] + "\n" : arr[i] + ",");
    }
  }

  public static void main(String[] args) {
    int[] nums = { 5, 2, 5, 3, 5, 3, 1, 1, 3 };
    int k = 2;
    printArray(topKFrequent(nums, k));
  }
}
