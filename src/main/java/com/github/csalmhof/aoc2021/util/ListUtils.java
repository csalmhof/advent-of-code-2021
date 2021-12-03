package com.github.csalmhof.aoc2021.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListUtils {

  public static List<String> transpose(List<String> input) {
    return IntStream.range(0, input.get(0).length())
        .mapToObj(i ->
            input.stream()
                .map(string -> string.charAt(i))
                .map(String::valueOf)
                .collect(Collectors.joining()))
        .collect(Collectors.toList());
  }

  public static <T> T singletonOrPreferred(T preferred, List<T> vals) {
    return vals.size() == 1 ? vals.get(0) : preferred;
  }
}
