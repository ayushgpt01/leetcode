package DAG;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class TopologicalSort {
  public static void dfs(int node, int[] visited, ArrayList<ArrayList<Integer>> adjList, Deque<Integer> stack) {
    visited[node] = 1;
    for (int i : adjList.get(node)) {
      if (visited[i] == 0) {
        dfs(i, visited, adjList, stack);
      }
    }

    stack.push(node);
  }

  public static List<Integer> topoSort(int v, ArrayList<ArrayList<Integer>> adjList) {
    int[] visited = new int[v];
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < v; i++) {
      if (visited[i] == 0) {
        dfs(i, visited, adjList, stack);
      }
    }

    List<Integer> ans = new ArrayList<>();
    while (!stack.isEmpty()) {
      ans.add(stack.pop());
    }

    return ans;
  }

  public static void main(String[] args) {
    // Declare and initialize the 2D ArrayList
    ArrayList<ArrayList<Integer>> twoDArrayList = new ArrayList<>();
    // First row: empty list []
    twoDArrayList.add(new ArrayList<>());
    // Second row: empty list []
    twoDArrayList.add(new ArrayList<>());
    // Third row: [3]
    twoDArrayList.add(new ArrayList<>(Arrays.asList(3)));
    // Fourth row: [1]
    twoDArrayList.add(new ArrayList<>(Arrays.asList(1)));
    // Fifth row: [0, 1]
    twoDArrayList.add(new ArrayList<>(Arrays.asList(0, 1)));
    // Sixth row: [0, 2]
    twoDArrayList.add(new ArrayList<>(Arrays.asList(0, 2)));

    System.out.println(topoSort(6, twoDArrayList));

    List<List<Integer>> result = new ArrayList<>();
    result.add(new ArrayList<>(Arrays.asList(1)));

    for (int i = 1; i < 5; i++) {
      List<Integer> list = new ArrayList<>();
      for (int j = 0; j <= i; j++) {
        if (j == 0 || j == i)
          list.add(1);
        list.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
      }
    }

  }
}
