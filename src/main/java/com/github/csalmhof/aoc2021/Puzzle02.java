package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day2.Command;
import com.github.csalmhof.aoc2021.helpers.day2.Puzzle02Part1Result;
import com.github.csalmhof.aoc2021.helpers.day2.Puzzle02Part2Result;

import java.util.List;

public class Puzzle02 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 2;
  }

  @Override
  public int calculatePart1Result(List<String> input) {
    Puzzle02Part1Result result = new Puzzle02Part1Result();
    result.processCommands(Command.toCommands(input));
    return result.calculate();
  }

  @Override
  public int calculatePart2Result(List<String> input) {
    Puzzle02Part2Result result = new Puzzle02Part2Result();
    result.processCommands(Command.toCommands(input));
    return result.calculate();
  }


}
