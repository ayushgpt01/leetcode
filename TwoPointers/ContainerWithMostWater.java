package TwoPointers;

/**
 * Basically checks the area of the container with most water by checking the
 * current area of window. So, since we need a maximum area which is defined by
 * height and distance between two items, the solution can be found in two ways
 * 
 * 1. Brute force - Get all container sizes and return the maximum of them.
 * However
 * it will have a time complexity of O(n^2) and space complexity of O(1)
 * 2. Optimal solution - Since we need max area, we can maximize the distance
 * first
 * and then shrink the containers until we get one that has max area. This is
 * called Sliding Window technique. We place two pointers on far ends and then,
 * check whichever has lower height will move. On each iteration, we get the
 * area and find the maximum area. Time complexity of this approach will be
 * O(n) since we transverse array once and space complexity will be O(1).
 */

public class ContainerWithMostWater {
  public static int bruteForce(int[] height) {
    int currentMax = Integer.MIN_VALUE;
    for (int i = 0; i < height.length; i++) {
      for (int j = i + 1; j < height.length; j++) {
        int length = j - i;
        int min = Math.min(height[i], height[j]);
        int area = length * min;

        currentMax = Math.max(currentMax, area);

      }
    }

    return currentMax;
  }

  public static int solution(int[] height) {
    // Start at extreme ends
    // Check the length (j - i) * min(height[i], height[j])
    // If it is max yet update it, if not break
    // If max then update the i/j depending on min(height[i], height[j])
    // Return max

    int left = 0;
    int right = height.length - 1;
    int currentMax = Integer.MIN_VALUE;

    while (left < right) {
      int length = right - left;
      int min = Math.min(height[right], height[left]);
      int area = length * min;

      currentMax = Math.max(currentMax, area);

      if (height[right] < height[left]) {
        right--;
      } else {
        left++;
      }
    }

    return currentMax;
  }

  public static void main(String[] args) {
    int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
    System.out.println(solution(height));
  }
}
