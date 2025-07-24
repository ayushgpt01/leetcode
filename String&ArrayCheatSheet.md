## Java Strings & Arrays LeetCode Cheat Sheet

### I. Java Strings

**Key Concept:** Strings in Java are **immutable**. Any operation that seems to modify a String actually creates a new String object.

**1. Initialization:**

- `String s = "hello";`
- `String s2 = new String("world");`
- `char[] charArray = {'h', 'e', 'l', 'l', 'o'}; String s3 = new String(charArray);`

**2. Basic Operations:**

- **Length:** `s.length()`
- **Access Char:** `s.charAt(index)` (0-indexed)
- **Concatenation:** `s1 + s2` or `s1.concat(s2)`
- **Substring:** `s.substring(beginIndex)` (inclusive to end)
  `s.substring(beginIndex, endIndex)` (beginIndex inclusive, endIndex exclusive)
- **Equality:** `s1.equals(s2)` (**NEVER** use `==` for String content comparison)
- **Case Conversion:** `s.toLowerCase()`, `s.toUpperCase()`
- **Check Empty:** `s.isEmpty()` (returns true if length is 0)
- **Check Blank:** `s.isBlank()` (returns true if empty or contains only whitespace)

**3. Searching & Replacing:**

- **Contains:** `s.contains("sub")`
- **Index of:** `s.indexOf("char_or_sub")` (first occurrence, returns -1 if not found)
  `s.indexOf("char_or_sub", fromIndex)`
  `s.lastIndexOf("char_or_sub")` (last occurrence)
- **Starts/Ends With:** `s.startsWith("prefix")`, `s.endsWith("suffix")`
- **Replace:** `s.replace('oldChar', 'newChar')` (all occurrences)
  `s.replaceAll("regex", "replacement")`
  `s.replaceFirst("regex", "replacement")`

**4. Splitting & Joining:**

- **Split:** `String[] parts = s.split("delimiter")`
  - Example: `String[] words = "hello world".split(" ");`
  - Be careful with regex: `s.split("\\.")` for dot.
- **Join:** `String.join("delimiter", array_of_strings)`
  - Example: `String combined = String.join("-", new String[]{"a", "b", "c"});` // "a-b-c"

**5. String to Character Array/Byte Array:**

- `char[] chars = s.toCharArray();`
- `byte[] bytes = s.getBytes();`

**6. Building Strings Efficiently:**

- **`StringBuilder` (for mutable strings):** Use this for building strings in loops or when many modifications are needed to avoid creating many intermediate String objects.
  - `StringBuilder sb = new StringBuilder();`
  - `sb.append("char or string");`
  - `sb.insert(index, "char or string");`
  - `sb.deleteCharAt(index);`
  - `sb.delete(startIndex, endIndex);`
  - `sb.reverse();`
  - `sb.toString();` (convert back to `String` at the end)

**7. Common LeetCode String Patterns:**

- **Two Pointers:** For palindrome checks, reversing strings in-place (if using char array), etc.
- **Sliding Window:** For finding longest substring without repeating characters, fixed-size window problems.
- **Frequency Map/Array:** Counting character frequencies (e.g., `int[] freq = new int[26];` for lowercase English letters).
- **Character.isLetter(), Character.isDigit(), Character.isWhitespace(), Character.isLetterOrDigit(), Character.toLowerCase(), Character.toUpperCase()** (useful for validation/parsing).

---

### II. Java Arrays

**Key Concept:** Arrays in Java are fixed-size once declared.

**1. Initialization:**

- **Declare & Initialize:**
  - `int[] arr = {1, 2, 3};`
  - `String[] strArr = {"a", "b"};`
- **Declare & Allocate:**
  - `int[] arr2 = new int[5];` (all elements initialized to default value, e.g., 0 for int, null for objects)
- **Multi-dimensional Arrays:**
  - `int[][] matrix = new int[3][4];`
  - `int[][] jagged = new int[3][];`
    `jagged[0] = new int[2];`
    `jagged[1] = new int[5];`
  - `int[][] matrix2 = {{1,2},{3,4,5}};`

**2. Basic Operations:**

- **Length:** `arr.length` (for 1D), `matrix.length` (number of rows), `matrix[0].length` (number of columns in first row)
- **Access Element:** `arr[index]`, `matrix[row][col]`
- **Iterating:**
  ```java
  // Standard for loop
  for (int i = 0; i < arr.length; i++) {
      // arr[i]
  }
  // Enhanced for loop (read-only)
  for (int num : arr) {
      // num
  }
  ```

**3. `java.util.Arrays` Class (Very Useful\!):**

- **Sorting:**
  - `Arrays.sort(arr);` (ascending for primitives)
  - `Arrays.sort(arr, Collections.reverseOrder());` (for wrapper types like `Integer[]`)
  - `Arrays.sort(arr, fromIndex, toIndex);`
  - `Arrays.sort(objectsArr, (a, b) -> a.property - b.property);` (custom comparator for objects)
- **Searching:**
  - `Arrays.binarySearch(arr, key);` (requires sorted array, returns index or `-(insertionPoint + 1)`)
- **Copying:**
  - `int[] copy = Arrays.copyOf(arr, arr.length);`
  - `int[] partialCopy = Arrays.copyOfRange(arr, from, to);`
  - `System.arraycopy(src, srcPos, dest, destPos, length);` (more flexible, low-level)
- **Filling:** `Arrays.fill(arr, value);`
  `Arrays.fill(arr, fromIndex, toIndex, value);`
- **Comparing Content:** `Arrays.equals(arr1, arr2);` (deep comparison for multi-dimensional: `Arrays.deepEquals(arr1, arr2)`)
- **String Representation:** `Arrays.toString(arr);` (for 1D array, e.g., "[1, 2, 3]")
  `Arrays.deepToString(matrix);` (for multi-dimensional)

**4. Converting between Array and List:**

- **Array to List:**
  - `List<Integer> list = Arrays.asList(1, 2, 3);` (fixed-size list, backed by array)
  - `List<Integer> mutableList = new ArrayList<>(Arrays.asList(1, 2, 3));` (mutable)
  - `List<String> list = new ArrayList<>(); Collections.addAll(list, strArray);`
- **List to Array:**
  - `Integer[] arr = list.toArray(new Integer[0]);` (preferred for object arrays)
  - `int[] primitiveArr = list.stream().mapToInt(Integer::intValue).toArray();` (for converting `List<Integer>` to `int[]`)

**5. Common LeetCode Array Patterns:**

- **Two Pointers:**
  - **Same Direction:** Sliding window, finding subarrays.
  - **Opposite Direction:** Palindrome checks, sorting (e.g., Dutch National Flag), merging sorted arrays.
- **Prefix Sums:** For quickly calculating sum of subarrays/ranges.
  - `prefixSum[i] = arr[0] + ... + arr[i]`
  - `sum(i, j) = prefixSum[j] - prefixSum[i-1]`
- **Sliding Window:** Max/min subarray sum, longest subarray with condition.
- **Hashing/Frequency Map:** Counting occurrences, checking duplicates, finding missing numbers.
- **Sorting:** Many problems become easier after sorting (e.g., finding k-th smallest/largest, two-sum variations).
- **Binary Search:** On a sorted array or on the answer space.
- **Monotonic Stack/Queue:** For problems involving next greater/smaller element, ranges.
- **In-place Modification:** When space complexity is a concern (e.g., removing duplicates, rotating arrays).

# Credits -

Someone else - I don't remember who.
