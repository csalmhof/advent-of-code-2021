package com.github.csalmhof.aoc2021.util;

import java.util.Objects;

public class Pair<T, V>{
  public final T left;
  public final V right;

  private Pair(T left, V right) {
    this.left = left;
    this.right = right;
  }

  public static <T,V> Pair<T,V> of(T left, V right) {
    return new Pair<>(left, right);
  }

  public T getLeft() {
    return left;
  }

  public V getRight() {
    return right;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pair<?, ?> pair = (Pair<?, ?>) o;
    return Objects.equals(left, pair.left) && Objects.equals(right, pair.right);
  }

  @Override
  public String toString() {
    return "[" + left + "," + right + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(left, right);
  }
}
