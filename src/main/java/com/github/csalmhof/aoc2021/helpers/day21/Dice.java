package com.github.csalmhof.aoc2021.helpers.day21;

public interface Dice {

  long roll();

  default long roll(int times) {
    long result = 0;
    for(int i = 0; i < times; i++) {
      result += roll();
    }
    return result;
  }

  long getRolls();
}
