package SystemDesign.RateLimiter;

import java.util.ArrayDeque;
import java.util.HashMap;

// Design a class RateLimiter that enforces per-user API rate limits.
// Each user is allowed at most N requests in a sliding window of T seconds.

// - Each user is rate-limited independently.
// - allowRequest(userId, timestamp):
//   - Returns true if the request is allowed.
//   - Returns false if the request exceeds the user’s rate limit.
// - You must use a sliding window approach (not a fixed window).
// - The timestamps are given in seconds, and arrive in non-decreasing order.
// - The class will be instantiated once and used across many requests.

// Constraints:
// - 0 <= timestamp <= 1e9
// - 1 <= limit <= 1000
// - 1 <= windowSizeInSeconds <= 300
// - Up to 1 million users
// - Up to 10 million requests total
// - No external libraries
// - Must be thread-safe if used in concurrent environments (stretch goal)

public class RateLimiter {
  private final int limit;
  private final int window;
  private final HashMap<String, ArrayDeque<Integer>> userMap;

  public RateLimiter(int limit, int windowSizeInSeconds) {
    this.limit = limit;
    this.window = windowSizeInSeconds;
    this.userMap = new HashMap<>();
  }

  public boolean allowRequest(String userId, int timestamp) {
    userMap.putIfAbsent(userId, new ArrayDeque<Integer>());
    ArrayDeque<Integer> queue = userMap.get(userId);

    while (!queue.isEmpty() && queue.peek() <= timestamp - window) {
      queue.poll();
    }

    if (queue.size() < limit) {
      queue.add(timestamp);
      return true;
    }

    return false;
  }
}
