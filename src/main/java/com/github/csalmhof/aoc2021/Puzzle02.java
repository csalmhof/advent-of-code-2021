package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day2.Command;
import com.github.csalmhof.aoc2021.helpers.day2.Puzzle02Part1Result;
import com.github.csalmhof.aoc2021.helpers.day2.Puzzle02Part2Result;

import java.util.List;
import java.util.function.Consumer;

public class Puzzle02 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 2;
  }

  @Override
  public int calculatePart1Result(List<String> input) {
    Puzzle02Part1Result result = new Puzzle02Part1Result();
    processCommandsOnResult(input, result::processCommand);
    return result.calculate();
  }

  @Override
  public int calculatePart2Result(List<String> input) {
    Puzzle02Part2Result result = new Puzzle02Part2Result();
    processCommandsOnResult(input, result::processCommand);
    return result.calculate();
  }

  private void processCommandsOnResult(List<String> input, Consumer<Command> processor) {
    input.stream()
        .map(Command::new)
        .forEach(processor);
  }

}
