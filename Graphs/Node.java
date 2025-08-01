package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Node {
  public int val;
  public List<Node> neighbors;

  public Node() {
    val = 0;
    neighbors = new ArrayList<Node>();
  }

  public Node(int _val) {
    val = _val;
    neighbors = new ArrayList<Node>();
  }

  public Node(int _val, ArrayList<Node> _neighbors) {
    val = _val;
    neighbors = _neighbors;
  }

  public static Node createNode(List<List<Integer>> adjList) {
    if (adjList == null || adjList.isEmpty()) {
      return null;
    }

    HashMap<Integer, Node> nodes = new HashMap<>();

    for (int i = 0; i < adjList.size(); i++) {
      nodes.put(i + 1, new Node(i + 1));
    }

    for (int i = 0; i < adjList.size(); i++) {
      Node currentNode = nodes.get(i + 1);
      if (currentNode != null) {
        for (Integer neighborVal : adjList.get(i)) {
          Node neighborNode = nodes.get(neighborVal);
          if (neighborNode != null) {
            currentNode.neighbors.add(neighborNode);
          }
        }
      }
    }

    return nodes.get(1);
  }

  public static void printGraph(Node node) {
    if (node == null) {
      System.out.println("Graph is empty.");
      return;
    }

    Queue<Node> queue = new LinkedList<>();
    Set<Node> visited = new HashSet<>();

    queue.offer(node);
    visited.add(node);

    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();

      System.out.print("Node " + currentNode.val + " -> Neighbors: [");
      for (int i = 0; i < currentNode.neighbors.size(); i++) {
        System.out.print(currentNode.neighbors.get(i).val);
        if (i < currentNode.neighbors.size() - 1) {
          System.out.print(", ");
        }
      }
      System.out.println("]");

      for (Node neighbor : currentNode.neighbors) {
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.offer(neighbor);
        }
      }
    }
  }
}
