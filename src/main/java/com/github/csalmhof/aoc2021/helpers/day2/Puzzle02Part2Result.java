package com.github.csalmhof.aoc2021.helpers.day2;

public class Puzzle02Part2Result extends Puzzle02Result{
  private int aim;

  public int calculate() {
    return horizontalPosition * depth;
  }

  @Override
  public void processCommand(Command command) {
    switch (command.getInstruction()) {
      case FORWARD:
        horizontalPosition += command.getValue();
        depth += aim * command.getValue();
        break;
      case DOWN:
        aim += command.getValue();
        break;
      case UP:
        aim -= command.getValue();
        break;
    }
  }
}
