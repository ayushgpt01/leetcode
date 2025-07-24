package SystemDesign.TimeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TimeStamp {
  private Integer timestamp;
  private String value;

  TimeStamp(Integer timestamp, String value) {
    this.timestamp = timestamp;
    this.value = value;
  }

  public Integer getTimestamp() {
    return timestamp;
  }

  public String getValue() {
    return value;
  }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
public class TimeMap {
  private final Map<String, List<TimeStamp>> map;

  public TimeMap() {
    map = new HashMap<>();
  }

  public void set(String key, String value, int timestamp) {
    // We can do this since
    map.computeIfAbsent(key, k -> new ArrayList<>())
        .add(new TimeStamp(timestamp, value));
  }

  public String get(String key, int timestamp) {
    List<TimeStamp> list = map.get(key);
    if (list == null) {
      return "";
    }

    int left = 0;
    int right = list.size() - 1;
    int largestTS = -1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (list.get(mid).getTimestamp() == timestamp) {
        return list.get(mid).getValue();
      } else if (list.get(mid).getTimestamp() < timestamp) {
        left = mid + 1;
        largestTS = Math.max(largestTS, mid);
      } else {
        right = mid - 1;
      }
    }

    if (largestTS == -1) {
      return "";
    }

    return list.get(largestTS).getValue();
  }

  public static void main(String[] args) {
    TimeMap timeMap = new TimeMap();
    timeMap.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1.
    System.out.println(timeMap.get("foo", 1)); // return "bar"
    System.out.println(timeMap.get("foo", 3)); // return "bar", since there is no value corresponding to foo at
                                               // timestamp 3 and
    // timestamp 2, then the only value is at timestamp 1 is "bar".
    timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
    System.out.println(timeMap.get("foo", 4)); // return "bar2"
    System.out.println(timeMap.get("foo", 5)); // return "bar2"
  }
}
