package DataStructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Design a data structure that supports the following operations:
// void add(int number);
// boolean find(int value);

// Behavior
// add(number): Stores the number into an internal data structure.
// find(value): Returns true if there exists any pair of numbers in the structure which sum up to value.
// The same number can be added multiple times and can be used once per pair, unless you store it twice.

// Constraints
// - Up to 10^4 calls to add() and find() combined
// - Input numbers: -10^9 <= number <= 10^9
// - Input to find(value): -2*10^9 <= value <= 2*10^9
// - Duplicate numbers are allowed
// - Optimize for efficient find() (unless stated otherwise)

public class TwoSum {
  private final List<Integer> list;
  private final Set<Integer> set;

  TwoSum() {
    list = new ArrayList<>();
    set = new HashSet<>();
  }

  void add(int number) {
    for (int num : list) {
      set.add(num + number);
    }

    list.add(number);
  }

  boolean find(int value) {
    return set.contains(value);
  }

  public static void main(String[] args) {
    TwoSum obj = new TwoSum();
    obj.add(1);
    obj.add(3);
    obj.add(5);

    System.out.println(obj.find(4)); // returns true (1 + 3)
    System.out.println(obj.find(7)); // returns false

    Set<String> set = new HashSet<>();
    set.add("12" + 12 + "2");
    set.contains("12" + 12 + "2");

    // obj.add(3);
    // obj.add(3);

    // obj.find(6); // returns true (3 + 3)
    // obj.find(7); // returns false

  }
}
