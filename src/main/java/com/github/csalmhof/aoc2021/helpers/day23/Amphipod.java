package com.github.csalmhof.aoc2021.helpers.day23;

import java.util.Arrays;

public enum Amphipod {
  AMBER('A', 1L),
  BRONZE('B', 10L),
  COPPER('C', 100L),
  DESERT('D', 1000L);

  private char shortValue;
  private long stepCost;

  Amphipod(char shortValue, long stepCost) {
    this.shortValue = shortValue;
    this.stepCost = stepCost;
  }

  public char getShortValue() {
    return shortValue;
  }

  public void setShortValue(char shortValue) {
    this.shortValue = shortValue;
  }

  public long getStepCost() {
    return stepCost;
  }

  public void setStepCost(long stepCost) {
    this.stepCost = stepCost;
  }

  public static Amphipod fromString(char c) {
    return Arrays.stream(Amphipod.values())
        .filter(amphipod -> amphipod.shortValue == c)
        .findFirst().orElse(null);
  }
}
