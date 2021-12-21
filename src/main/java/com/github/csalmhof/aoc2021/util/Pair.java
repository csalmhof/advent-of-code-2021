package com.github.csalmhof.aoc2021.util;

import java.util.Map;

public class Pair<T, V>{
  private final Map.Entry<T, V> value;

  private Pair(T left, V right) {
    this.value = Map.entry(left, right);
  }

  public static <T,V> Pair<T,V> of(T left, V right) {
    return new Pair<>(left, right);
  }

  public T getLeft() {
    return value.getKey();
  }

  public V getRight() {
    return value.getValue();
  }
}
