package com.github.csalmhof.aoc2021;

public class Puzzle02Test extends AbstractPuzzleTest {

  @Override
  public AbstractPuzzle getPuzzleInstance() {
    return new Puzzle02();
  }

  @Override
  public int getExpectedExampleResultPart1() {
    return 150;
  }

  @Override
  public int getExpectedExampleResultPart2() {
    return 900;
  }
}
