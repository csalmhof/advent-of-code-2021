package com.github.csalmhof.aoc2021.helpers.day2;

import java.util.List;

public abstract class Puzzle02Result {
  protected int horizontalPosition;
  protected int depth;

  public int calculate() {
    return horizontalPosition * depth;
  }

  public abstract void processCommand(Command command);

  public void processCommands(List<Command> command) {
    command.forEach(this::processCommand);
  }
}
