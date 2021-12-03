package com.github.csalmhof.aoc2021.util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringUtils {

  public static List<Character> findMaxOccuringCharsInString(String s) {
    return getMaxComparingChars(countCharsInString(s), Comparator.comparing(Function.identity()), 0);
  }

  public static List<Character> findMinOccuringCharsInString(String s) {
    return getMaxComparingChars(countCharsInString(s), Comparator.comparing(i -> -i), Integer.MAX_VALUE);
  }

  private static List<Character> getMaxComparingChars(Map<Character, Integer> charCountMap, Comparator<Integer> maxComparator, int elseValue) {
    Integer maxCount = charCountMap.values().stream()
        .max(maxComparator)
        .orElse(elseValue);

    return charCountMap.entrySet().stream()
        .filter(entry -> entry.getValue().equals(maxCount))
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
    return ListUtils.singletonOrPreferred(c, findMaxOccuringCharsInString(s));
  }

  public static Character findMinOccuringCharInStringWithPreferringValue(char c, String s) {
    return ListUtils.singletonOrPreferred(c, findMinOccuringCharsInString(s));
  }

  public static String invertBinaryString(String s) {
    return s.replace('0', 'x').replace('1', '0').replace('x', '1');
  }

  public static int binaryStringToInt(String s) {
    return Integer.parseInt(s, 2);
  }
}
