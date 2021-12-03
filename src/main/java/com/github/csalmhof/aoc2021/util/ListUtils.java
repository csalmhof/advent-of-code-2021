package com.github.csalmhof.aoc2021.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

  public static List<String> transpose(List<String> input) {
    List<String> result = initListWithNemptyStrings(input.get(0).length());

    for (String s : input) {
      for (int i = 0; i < result.size(); i++) {
        result.set(i, result.get(i) + s.charAt(i));
      }
    }

    return result;
  }

  private static List<String> initListWithNemptyStrings(int n) {
    List<String> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list.add("");
    }
    return list;
  }
}
