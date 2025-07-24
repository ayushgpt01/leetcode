package Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import Utils.Printer;

public class CarFleet {
  private static double getTime(int target, int speed, int position) {
    return (double) (target - position) / speed;
  }

  /**
   * Note - Don't go from farthest to closest since speed of car changes depending
   * on collisions. It's more determinstic to consider the closest car from target
   * as anything behind it that collides will be travelling at the same rate as
   * this one.
   */
  public static int carFleet(int target, int[] position, int[] speed) {
    Deque<Integer> stack = new ArrayDeque<>();
    HashMap<Integer, Integer> speedMap = new HashMap<>();

    for (int i = 0; i < position.length; i++) {
      speedMap.put(position[i], speed[i]);
    }

    // We are reversing the order of positions since no car can pass the car that is
    // ahead of it so when managing collisions it's much easier to handle this with
    // reversed positions.
    List<Integer> reversed = Arrays.stream(position).boxed().collect(Collectors.toList());
    Collections.sort(reversed, Collections.reverseOrder());

    for (int i = 0; i < reversed.size(); i++) {
      if (stack.peek() != null) {
        int topCarPos = reversed.get(stack.peek());
        int currentPos = reversed.get(i);

        int topCarSpeed = speedMap.get(topCarPos);
        int currentCarSpeed = speedMap.get(currentPos);

        double topCarTime = getTime(target, topCarSpeed, topCarPos);
        double currentCarTime = getTime(target, currentCarSpeed, currentPos);

        // Check if the farther car arrives before or at same time as closer car
        if (topCarTime >= currentCarTime) {
          // We have a collision so we move to next car and merge this with top
          continue;
        }
      }

      stack.push(i);
    }

    // Since we only push a car onto stack if it's not colliding with anything. The
    // result is the number of items in stack.
    return stack.size();
  }

  public static void main(String[] args) {
    int target = 10;
    int[] position = { 6, 8 };
    int[] speed = { 3, 2 };
    Printer.print(carFleet(target, position, speed));
  }
}
