package Graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

/**
 * Problem - https://leetcode.com/problems/clone-graph/
 * Thing to note here is the way you stop infinite loops in cycles. For this we
 * need to make sure that once a node is visited we don't visit it again since
 * it will already have all the neighbors inserted in it.
 */
public class CloneGraph {
  public static Node cloneGraph(Node node) {
    if (node == null) {
      return null;
    }

    HashMap<Node, Node> map = new HashMap<>();
    Deque<Node> queue = new ArrayDeque<>();

    queue.offer(node);
    map.put(node, new Node(node.val));

    while (!queue.isEmpty()) {
      Node current = queue.poll();
      List<Node> neighbors = new ArrayList<>();

      for (int i = 0; i < current.neighbors.size(); i++) {
        Node neighbor = current.neighbors.get(i);

        if (!map.containsKey(neighbor)) {
          map.put(neighbor, new Node(neighbor.val));
          queue.offer(neighbor);
        }

        neighbors.add(map.get(neighbor));
      }

      map.get(current).neighbors = neighbors;
    }

    return map.get(node);
  }

  public static void main(String[] args) {
    List<List<Integer>> adjList = List.of(
        List.of(2, 4),
        List.of(1, 3),
        List.of(2, 4),
        List.of(1, 3));

    Node node = Node.createNode(adjList);
    Node.printGraph(node);

    Node.printGraph(cloneGraph(node));
  }
}
