package SystemDesign.HitCounter;

import java.util.ArrayDeque;

public class HitCounter {
  private ArrayDeque<Integer> queue;
  private static final int WINDOW = 300;

  public HitCounter() {
    queue = new ArrayDeque<Integer>();
  }

  private void clearQueue(int timeStamp) {
    while (!queue.isEmpty() && queue.peek() <= timeStamp - WINDOW) {
      queue.poll();
    }
  }

  public void hit(int timeStamp) {
    queue.add(timeStamp);
  }

  public int getHits(int timeStamp) {
    this.clearQueue(timeStamp);

    return queue.size();
  }
}
