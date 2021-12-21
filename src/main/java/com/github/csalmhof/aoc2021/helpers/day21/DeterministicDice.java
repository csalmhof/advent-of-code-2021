package com.github.csalmhof.aoc2021.helpers.day21;

public class DeterministicDice implements Dice {
  private static final long MAX_NUM = 100L;

  private long rolls;

  @Override
  public long roll() {
    rolls++;
    return (rolls -1)%MAX_NUM +1;
  }

  @Override
  public long getRolls() {
    return rolls;
  }
}
