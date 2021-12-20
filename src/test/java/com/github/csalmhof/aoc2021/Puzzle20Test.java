package com.github.csalmhof.aoc2021;

public class Puzzle20Test extends AbstractPuzzleTest {

  @Override
  public AbstractPuzzle createPuzzle() {
    return new Puzzle20();
  }

  @Override
  public long getExpectedExampleResultPart1() {
    return 35;
  }

  @Override
  public long getExpectedExampleResultPart2() {
    return 3351;
  }
}
