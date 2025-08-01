package Utils;

import java.util.List;

public class Printer {
  // For primitive int arrays
  public static void print(int[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i < arr.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  // Overload to handle two-dimensional object arrays (e.g., String[][])
  public static void print(int[][] arr) {
    if (arr == null) {
      System.out.println("null");
      return;
    }
    System.out.println("[");
    for (int[] row : arr) {
      System.out.print("  ");
      print(row);
    }
    System.out.println("]");
  }

  // For primitive double arrays
  public static void print(double[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i < arr.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  public static void print(long[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i < arr.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  public static void print(byte[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i < arr.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  public static void print(short[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i < arr.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  public static void print(float[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i < arr.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  public static void print(char[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i < arr.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  public static void print(boolean[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i < arr.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  // For Object arrays (e.g., String[], Integer[])
  public static <T> void print(T[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i < arr.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  // Overload to handle two-dimensional object arrays (e.g., String[][])
  public static <T> void print(T[][] arr) {
    if (arr == null) {
      System.out.println("null");
      return;
    }
    System.out.println("[");
    for (T[] row : arr) {
      System.out.print("  ");
      print(row);
    }
    System.out.println("]");
  }

  // For Lists of any type
  public static <T> void print(List<T> list) {
    System.out.print("[");
    for (int i = 0; i < list.size(); i++) {
      System.out.print(list.get(i));
      if (i < list.size() - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  public static <T> void print(java.util.Collection<T> collection) {
    System.out.println(collection.toString()); // Often simplest for generic collections
  }

  public static void print(int s) {
    System.out.println(s);
  }

  public static void print(double s) {
    System.out.println(s);
  }

  public static void print(long s) {
    System.out.println(s);
  }

  public static void print(byte s) {
    System.out.println(s);
  }

  public static void print(short s) {
    System.out.println(s);
  }

  public static void print(float s) {
    System.out.println(s);
  }

  public static void print(boolean s) {
    System.out.println(s);
  }
}
