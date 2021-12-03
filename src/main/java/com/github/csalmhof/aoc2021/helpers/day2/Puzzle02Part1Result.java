package com.github.csalmhof.aoc2021.helpers.day2;

public class Puzzle02Part1Result extends Puzzle02Result{

  public int calculate() {
    return this.horizontalPosition * depth;
  }

  @Override
  public void processCommand(Command command) {
    switch (command.getInstruction()) {
      case FORWARD:
        horizontalPosition += command.getValue();
        break;
      case DOWN:
        depth += command.getValue();
        break;
      case UP:
        depth -= command.getValue();
        break;
    }
  }
}
