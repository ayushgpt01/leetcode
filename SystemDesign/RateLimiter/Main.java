package SystemDesign.RateLimiter;

public class Main {
  public static void main(String[] args) {
    RateLimiter rl = new RateLimiter(3, 10);
    System.out.println(rl.allowRequest("alice", 1)); // true
    System.out.println(rl.allowRequest("alice", 2)); // true
    System.out.println(rl.allowRequest("alice", 3)); // true
    System.out.println(rl.allowRequest("alice", 4)); // false
    System.out.println(rl.allowRequest("alice", 12)); // true (1 and 2 expired)
  }
}
