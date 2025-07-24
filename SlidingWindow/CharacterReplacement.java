package SlidingWindow;

/**
 * Basically we have to find the valid character that can 0 to k additional
 * other characters and has the longest length of it.
 * 
 * My plan -
 * - Start at first character index i from 0 to n - 1
 * - Move another pointer j from n - 1 to i + 1 (this is so that i can get to
 * the last of ith character fastest).
 * - When we last encounter same character as ith we remember it's index
 * lastIndex.
 * - Then track the count of non ith characters found between lastIndex and i
 * say currCount.
 * - We stop when j <= i and then length of this string is (lastIndex - i) + 1
 * tracked by curLength.
 * - If trackedCount <= k && ((k - currCount) <= i + 1 || (k - currCount) <= n -
 * lastIndex),
 * then it's a valid string and it's length is curLength + (k - currCount). This
 * is so because we can replace curLength number of characters and then we are
 * left with k - curCount character to add either before the first encountered
 * character or after last encountered character.
 * - Then we check it against current max length found and if it's greater we
 * update it and also keep a set of visited characters, else we don't update.
 * - On next iteration we first check if this character is already tracked, if
 * it is we continue to next iteration.
 * Time complexity - O(n) * O(k) where k ranges from i + 1 to n which is less
 * than O(n^2) but manageable.
 * 
 * Second approach using sliding window -
 * Here, I can utilise my other understanding and one thing to note is the final
 * answer is in a window of characters where the character count of answer is
 * windowLength - k. That means if we start with let's say a window length of 2
 * we
 * can expand the window until my character length satisfies the condition.
 * Now, there's another thing to note in this which is how do i find the
 * character for this approach in the window. For that, I can utilise a
 * frequency array that will get me the count of each character in the window
 * yet and i can track the one with longest count yet for this approach. Then we
 * keep expanding the window until this windowCount > k, then we shrink the
 * window using left until it's valid again. We track the current longest
 * encountered window as well and keep the shrinking and expanding phase going
 * until we reach end of array.
 */
public class CharacterReplacement {
  public static int characterReplacement(String s, int k) {
    int left = 0;
    int maxFrequency = 0;
    int[] frequency = new int[26];

    // This is used to move the right from left to right same as usual right 0 and
    // while loop
    for (int right = 0; right < s.length(); right++) {
      // Get the current character from the string
      Character c = s.charAt(right);
      // We immediately update the frequency of this character because we need to
      // check window condition after this and we initalize the freq map as empty
      frequency[c - 'A']++;

      // We update the max frequency based on current window since frequency changed
      // and we might have a new max
      maxFrequency = Math.max(maxFrequency, frequency[c - 'A']);

      // Now if the window cannot fit the exact k replacements, we start shrinking the
      // window
      while (right - left + 1 - maxFrequency > k) {
        // Update the frequency of current left
        frequency[s.charAt(left) - 'A']--;
        // Move left ahead and check again if window is still invalid or not
        left++;
      }
    }

    return s.length() - left;
  }

  public static void main(String[] args) {
    String s = "ABAB";
    int k = 2;
    System.out.println(characterReplacement(s, k));

    String s2 = "AABABBA";
    int k2 = 1;
    System.out.println(characterReplacement(s2, k2));
  }
}
