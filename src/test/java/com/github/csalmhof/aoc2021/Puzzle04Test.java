package com.github.csalmhof.aoc2021;

public class Puzzle04Test extends AbstractPuzzleTest {

  @Override
  public AbstractPuzzle createPuzzle() {
    return new Puzzle04();
  }

  @Override
  public long getExpectedExampleResultPart1() {
    return 4512;
  }

  @Override
  public long getExpectedExampleResultPart2() {
    return 1924;
  }
}
