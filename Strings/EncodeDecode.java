package Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeDecode {
  public static String encode(List<String> strs) {
    StringBuilder builder = new StringBuilder();
    for (String string : strs) {
      // This is important. If we add # then length, we don't know when to stop the
      // length. So we do opposite we start with length then add # which will let us
      // know when to stop and then we skip next i characters leading to next length.
      // Hence this one is robust.
      builder.append(string.length());
      builder.append("#");
      builder.append(string);
    }

    return builder.toString();
  }

  public static List<String> decode(String str) {
    List<String> list = new ArrayList<>();
    int i = 0;
    while (i < str.length()) {
      Character lengthCharacter = str.charAt(i);
      StringBuilder lengthBuilder = new StringBuilder();

      while (lengthCharacter != '#') {
        lengthBuilder.append(lengthCharacter);
        i++;
        lengthCharacter = str.charAt(i);
      }
      i++;

      int length = Integer.parseInt(lengthBuilder.toString());
      int startIndex = i;

      String s = str.substring(startIndex, startIndex + length);

      list.add(s);
      i = startIndex + length;
    }

    return list;
  }

  public static void main(String[] args) {
    String[] input = { "123", "code", "9kks", "#2@!@13ca" };
    String encoded = encode(Arrays.asList(input));
    System.out.println("encoded : " + encoded);
    List<String> decoded = decode(encoded);
    System.out.println("decoded : ");
    for (String string : decoded) {
      System.out.println(string);
    }
  }
}
