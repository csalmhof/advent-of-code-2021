package com.github.csalmhof.aoc2021.helpers.day2;

public abstract class Puzzle02Result {
  protected int horizontalPosition;
  protected int depth;

  public int calculate() {
    return horizontalPosition * depth;
  }

  public abstract void processCommand(Command command);
}
