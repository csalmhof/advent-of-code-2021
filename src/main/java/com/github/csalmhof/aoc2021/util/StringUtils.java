package com.github.csalmhof.aoc2021.util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringUtils {

  public static List<Character> findMaxOccuringCharsInString(String s) {

    Map<Character, Integer> charCountMap = countCharsInString(s);

    Integer maxCount = charCountMap.values().stream()
        .max(Comparator.comparing(Function.identity()))
        .orElse(0);

    return charCountMap.entrySet().stream()
        .filter(entry -> entry.getValue().equals(maxCount))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  public static List<Character> findMinOccuringCharsInString(String s) {
    Map<Character, Integer> charCountMap = countCharsInString(s);

    Integer minCount = charCountMap.values().stream()
        .min(Comparator.comparing(Function.identity()))
        .orElse(Integer.MAX_VALUE);

    return charCountMap.entrySet().stream()
        .filter(entry -> entry.getValue().equals(minCount))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  public static Map<Character, Integer> countCharsInString(String s) {
    return s.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(Function.identity()))
        .entrySet().stream()
        .map(e -> Map.entry(e.getKey(), e.getValue().size()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  public static Character findMaxOccuringCharInStringWithPreferringValue(char c, String s) {
    return returnSingletonFromListOrPreferred(c, findMaxOccuringCharsInString(s));
  }

  public static Character findMinOccuringCharInStringWithPreferringValue(char c, String s) {
    return returnSingletonFromListOrPreferred(c, findMinOccuringCharsInString(s));
  }

  private static Character returnSingletonFromListOrPreferred(char c, List<Character> maxOccuringChars) {
    if (maxOccuringChars.size() == 1) {
      return maxOccuringChars.get(0);
    } else {
      return c;
    }
  }

  public static String invertBinaryString(String s) {
    StringBuilder result = new StringBuilder();

    for (char c : s.toCharArray()) {
      if (c == '1') {
        result.append('0');
      } else {
        result.append('1');
      }
    }

    return result.toString();
  }

  public static int binaryStringToInt(String s) {
    return Integer.parseInt(s, 2);
  }
}
