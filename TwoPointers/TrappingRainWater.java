package TwoPointers;

public class TrappingRainWater {
  /**
   * In this problem, we have to find the total area of trapped water.
   * 
   * Observation - So, the way water is trapped is if it has boundary on both
   * sides, that means if a block doesn't have any other boundary on either the
   * right, or left it's not trapped.
   * 
   * Also, the height 0 shows a base level.
   * 
   * Thought Process - So, if we start at right we basically need to find patches
   * where water can be stored. So, if height is [0, 1, 0, 2] then we can store
   * water in [1, 0, 2] patch and the amount stored is (right wall - left wall) +
   * base for each middle value, so in this case 2 - 1 + 0 = 1. So we can store 1
   * unit in this patch. Similarly if we continue onwards we can continue to get
   * patches like these and counting the middle quantity of water.
   * - Find a patch with walls
   * - Calculate quantity of water in the patch
   * - Repeat until no patches left
   * 
   * Additional Observation - There's a down ladder in case of last numbers if the
   * array ends before we encounter a smaller number for patch
   * 
   * Time complexity - O(n^2)
   * Space complexity - O(1)
   */
  public static int bruteForce(int[] height) {
    if (height.length < 3) {
      return 0;
    }

    int count = 0;

    for (int left = 0; left < height.length - 2; left++) {
      if (height[left] <= height[left + 1]) {
        continue;
      }

      int right = left + 2;
      boolean downward = true;
      int maxIndex = right;
      int currentMax = height[right];

      while (right < height.length) {
        int rightWall = height[right];
        int leftWall = height[left];
        if (rightWall >= leftWall) {
          downward = false;
          int maxHeightOfWall = Math.min(leftWall, rightWall);
          // Found a patch
          for (int i = left + 1; i < right; i++) {
            int base = height[i];
            count += (maxHeightOfWall - base);
          }

          left = right - 1;
          break;
        }

        if (rightWall > currentMax) {
          currentMax = rightWall;
          maxIndex = right;
        }

        right++;
      }

      if (downward) {
        while (left <= maxIndex - 2 && height[left + 1] > currentMax) {
          left++;
        }
        int leftWall = height[left];
        int maxHeightOfWall = Math.min(leftWall, currentMax);
        // Found a patch
        for (int i = left + 1; i < maxIndex; i++) {
          int base = height[i];
          count += (maxHeightOfWall - base);
        }

        left = maxIndex - 1;
      }
    }

    return count;
  }

  /**
   * Main intuition for optimisation over above thing is how can i figure out the
   * walls or the current water value in linear time. So, linear time complexity
   * remains even if we run consecutive loops. And looking at problem I was
   * basically finding the right and left wall while going through the array and
   * then counting the sum in patches.
   * Better approach is to pre-calculate the left and right walls for each value.
   * This is prefix calculation and what we do is we first go through the array
   * from 0 to n and calculate all left walls for each surface. Then we go through
   * array again from n to 0 and calculate right wall. Then since we already have
   * the walls we don't need to calculate it while counting the patches and we can
   * just easily calculate using same formula.
   * Though there are edge cases like.. let's say first and last elements don't
   * have any left or right walls and shouldn't be included for counts and also if
   * any of the walls are lower than current height, then also we shouldn't count
   * as these are cases where we have consecutive large walls followed by some
   * smaller walls and the patch's wall is actually the inner most largest wall
   * 
   * Time complexity - O(n)
   * Space complexity - O(n)
   */
  public static int semiOptimal(int[] height) {
    if (height.length < 3) {
      return 0;
    }

    int count = 0;
    int[] leftMax = new int[height.length];
    int[] rightMax = new int[height.length];

    int max = -1;
    for (int i = 0; i < height.length; i++) {
      leftMax[i] = max;
      max = Math.max(max, height[i]);
    }

    max = -1;
    for (int i = height.length - 1; i >= 0; i--) {
      rightMax[i] = max;
      max = Math.max(max, height[i]);
    }

    for (int i = 0; i < height.length; i++) {
      if (leftMax[i] == -1 || rightMax[i] == -1 || leftMax[i] < height[i] || rightMax[i] < height[i]) {
        continue;
      }

      count += (Math.min(leftMax[i], rightMax[i]) - height[i]);
    }

    return count;
  }

  /**
   * Okay, so we figured out a way to get the water in linear time but I was
   * storing two arrays with precomputations which is not good. What i need to
   * figure out is a way to find the max left and right for each value as I
   * transverse through them.
   * Thought - I was calculating the left and right by going through the array on
   * opposite ends and it was always the correct max for that point then I can use
   * two pointers on opposite ends and it can work the same way. Also, seeing the
   * arrays a lot of elements have same high points.
   * 
   * For this, I will need to figure out a few things -
   * - First, I will move the pointers based on their values, whichever is
   * smaller moves.
   * - Second, when moving I will store the max left and right seen until now.
   * - When I reach a new point, I will update it's count if it is smaller than
   * current wall heights (previous solution's height less than condition)
   * - Continue with the loop until we have a collison of pointers
   * 
   * Time complexity - O(n)
   * Space complexity - O(1)
   */
  public static int trap(int[] height) {
    if (height.length < 3) {
      return 0;
    }

    int count = 0;
    int left = 0;
    int right = height.length - 1;
    int leftMax = height[left];
    int rightMax = height[right];

    while (left < right) {
      if (height[left] <= height[right]) {
        if (height[left] < leftMax) {
          count += (leftMax - height[left]);
        }

        leftMax = Math.max(leftMax, height[left]);
        left++;
      } else {
        if (height[right] < rightMax) {
          count += (rightMax - height[right]);
        }

        rightMax = Math.max(rightMax, height[right]);
        right--;
      }
    }

    return count;
  }

  public static void main(String[] args) {
    int[] height1 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
    System.out.println(trap(height1));
    int[] height2 = { 4, 2, 0, 3, 2, 5 };
    System.out.println(trap(height2));
    int[] height3 = { 5, 4, 1, 2 };
    System.out.println(trap(height3));
  }
}
