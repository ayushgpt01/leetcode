package SystemDesign.LRUCache;

import java.util.HashMap;

class DoublyLinkedNode {
  int value;
  int key;
  DoublyLinkedNode next;
  DoublyLinkedNode prev;

  DoublyLinkedNode() {
  }

  DoublyLinkedNode(int key, int value) {
    this.key = key;
    this.value = value;
    this.next = null;
    this.prev = null;
  }
}

public class LRUCache {
  private final HashMap<Integer, DoublyLinkedNode> cache;
  private final int capacity;
  private DoublyLinkedNode head;
  private DoublyLinkedNode tail;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.cache = new HashMap<>();
    this.head = new DoublyLinkedNode();
    this.tail = new DoublyLinkedNode();
    this.head.next = this.tail;
    this.tail.prev = this.head;
  }

  private void addToHead(DoublyLinkedNode node) {
    DoublyLinkedNode next = head.next;
    head.next = node;
    node.prev = head;
    node.next = next;
    next.prev = node;
  }

  private void removeNode(DoublyLinkedNode node) {
    if (node.next != null)
      node.next.prev = node.prev;
    if (node.prev != null)
      node.prev.next = node.next;
  }

  private void removeLRU() {
    DoublyLinkedNode prev = tail.prev;
    removeNode(prev);
    cache.remove(prev.key);
  }

  private void moveToFront(DoublyLinkedNode node) {
    removeNode(node);
    addToHead(node);
  }

  public int get(int key) {
    DoublyLinkedNode node = cache.get(key);
    if (node != null) {
      // Move the key to end of list
      moveToFront(node);
      return node.value;
    }

    return -1;
  }

  public void put(int key, int value) {
    // If key is not present add to the cache
    if (!cache.containsKey(key)) {
      DoublyLinkedNode node = new DoublyLinkedNode(key, value);
      cache.put(key, node);
      addToHead(node);

      // If adding another element results in overflow we remove LRU
      if (cache.size() > capacity) {
        removeLRU();
      }

      return;
    }

    DoublyLinkedNode node = cache.get(key);
    node.value = value;
    moveToFront(node);
  }

  public static void main(String[] args) {
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1); // cache is {1=1}
    lRUCache.put(2, 2); // cache is {1=1, 2=2}
    System.out.println(lRUCache.get(1)); // return 1
    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    System.out.println(lRUCache.get(2)); // returns -1 (not found)
    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    System.out.println(lRUCache.get(1)); // return -1 (not found)
    System.out.println(lRUCache.get(3)); // return 3
    System.out.println(lRUCache.get(4)); // return 4
  }
}
