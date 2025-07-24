package Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * So, basically we have to group the anagrams from original together. Now,
 * initial thought on this problem reveals a way to solve this would be to go
 * through each string and find the other strings from the array that are also
 * same anagram and then store them into an array. Then continue for every
 * element. This has a time complexity of O(n * m) which is not ideal.
 * 
 * Optimized - Since anagrams have a property that if we generate a RLE string
 * for all alphabets these have same strings we can utlise this easily. Since
 * count of each character is same in two anagrams it will always result in same
 * RLE. For this approach we can store the lists in a map with key as their RLE
 * string and value as a list. That way for each of the strings in array we just
 * have to generate the RLE and put them into map. Then later on we can just get
 * back the array of all values and that will be the answer. The time complexity
 * of this will be O(n * l) to loop through all items and space complexity will
 * be O(n * l) as well
 */
public class GroupAnagrams {
  public static List<List<String>> groupAnagrams(String[] strs) {
    HashMap<String, List<String>> map = new HashMap<>();

    for (String string : strs) {
      int[] count = new int[26];

      for (char ch : string.toCharArray()) {
        count[ch - 'a']++;
      }

      StringBuilder builder = new StringBuilder();
      for (int freq : count) {
        builder.append("#");
        builder.append(freq);
      }

      String key = builder.toString();

      map.computeIfAbsent(key, k -> new ArrayList<>()).add(string);
    }

    return new ArrayList<>(map.values());
  }

  public static void main(String[] args) {
    String[] strs = { "ddddddddddg", "dgggggggggg" };

    System.out.println(groupAnagrams(strs).toString());
  }
}
