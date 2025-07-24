package DAG;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KahnAlgorithm {
  public static List<Integer> kahnAlgo(int v, ArrayList<ArrayList<Integer>> adjList) {
    // Calculate indegree of each node in the adjacency list
    int[] indegree = new int[v];
    for (int i = 0; i < v; i++) {
      for (int node : adjList.get(i)) {
        indegree[node]++;
      }
    }

    // Push all the nodes with no incoming edges (indegree[i] == 0) to queue
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < v; i++) {
      if (indegree[i] == 0) {
        queue.add(i);
      }
    }

    // Create a list to store the topological order
    List<Integer> topo = new ArrayList<>(v);
    // Do BFS Transversal until queue is empty
    while (!queue.isEmpty()) {
      // Get current node and remove it from queue
      int node = queue.poll();
      // Add the current node to topological sort order since this node is transversed
      topo.add(node);

      // Get all adjacent nodes
      for (int it : adjList.get(node)) {
        // Decrease the indegree of adjacent node since current node is transversed
        indegree[it]--;
        // Check if after updating indegree it became 0
        if (indegree[it] == 0) {
          // then mark this node for transversal as well
          queue.add(it);
        }
      }
    }

    // This can be used to convert to int[] from ArrayList<Integer>
    // int[] a = topo.size() == v ? topo.stream()
    // .mapToInt(Integer::intValue)
    // .toArray() : new int[0];

    // If topo.length == v then it's a DAG otherwise not!!
    return topo;
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

    System.out.println(kahnAlgo(6, twoDArrayList));
  }
}
