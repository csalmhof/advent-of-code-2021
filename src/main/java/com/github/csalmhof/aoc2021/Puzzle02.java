package com.github.csalmhof.aoc2021;

import java.util.List;
import java.util.function.Consumer;

public class Puzzle02 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 2;
  }

  @Override
  public int calculatePart1Result(List<String> input) {
    Result result = new Result();
    processCommandsOnResult(input, result::processPart1Command);
    return result.calculate();
  }

  @Override
  public int calculatePart2Result(List<String> input) {
    Result result = new Result();
    processCommandsOnResult(input, result::processPart2Command);
    return result.calculate();
  }

  private void processCommandsOnResult(List<String> input, Consumer<Command> processor) {
    input.stream()
        .map(Command::new)
        .forEach(processor);
  }

  private static class Result {
    int aim;
    int horizontalPosition;
    int depth;

    public int calculate() {
      return horizontalPosition * depth;
    }

    public void processPart2Command(Command command) {
      switch (command.getInstruction()) {
        case FORWARD:
          horizontalPosition += command.getValue();
          depth += aim * command.getValue();
          break;
        case DOWN:
          aim += command.value;
          break;
        case UP:
          aim -= command.value;
          break;
      }
    }

    public void processPart1Command(Command command) {
      switch (command.getInstruction()) {
        case FORWARD:
          horizontalPosition += command.getValue();
          break;
        case DOWN:
          depth += command.value;
          break;
        case UP:
          depth -= command.value;
          break;
      }
    }
  }

  private static class Command {
    Instruction instruction;
    int value;

    public Command(String commandline) {
      String[] splitted = commandline.split(" ");
      this.instruction = Instruction.fromString(splitted[0]);
      this.value = Integer.parseInt(splitted[1]);
    }

    public Instruction getInstruction() {
      return instruction;
    }

    public int getValue() {
      return value;
    }
  }

  private enum Instruction {
    FORWARD,
    DOWN,
    UP;

    private static Instruction fromString(String s) {
      if ("forward".equals(s)) {
        return FORWARD;
      }
      if ("down".equals(s)) {
        return DOWN;
      }
      return UP;
    }
  }

}
