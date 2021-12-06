package com.github.csalmhof.aoc2021;

public class Puzzle02Test extends AbstractPuzzleTest {

  @Override
  public AbstractPuzzle getPuzzleInstance() {
    return new Puzzle02();
  }

  @Override
  public long getExpectedExampleResultPart1() {
    return 150;
  }

  @Override
  public long getExpectedExampleResultPart2() {
    return 900;
  }
}
