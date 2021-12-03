package com.github.csalmhof.aoc2021.helpers.day2;

public enum Instruction {
  FORWARD,
  DOWN,
  UP;

  public static Instruction fromString(String s) {
    if ("forward".equals(s)) {
      return FORWARD;
    }
    if ("down".equals(s)) {
      return DOWN;
    }
    return UP;
  }
}
