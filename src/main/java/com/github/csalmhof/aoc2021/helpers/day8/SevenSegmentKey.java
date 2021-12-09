package com.github.csalmhof.aoc2021.helpers.day8;

import java.util.List;
import java.util.stream.Collectors;

public class SevenSegmentKey {
  private final int value;
  private final List<Character> chars;

  public int getValue() {
    return value;
  }

  public SevenSegmentKey(int value, String s) {
    this.value = value;
    this.chars = stringToCharList(s);
  }

  public boolean contains(String other) {
    return this.chars.containsAll(stringToCharList(other));
  }

  public boolean isContainedIn(String other) {
    return stringToCharList(other).containsAll(this.chars);
  }

  public boolean isSame(String other) {
    return this.chars.size() == other.length()
        && this.isContainedIn(other);
  }

  private List<Character> stringToCharList(String s) {
    return s.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
  }
}
