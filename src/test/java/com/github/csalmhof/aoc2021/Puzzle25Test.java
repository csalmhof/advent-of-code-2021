package com.github.csalmhof.aoc2021;

public class Puzzle25Test extends AbstractPuzzleTest {

  @Override
  public AbstractPuzzle createPuzzle() {
    return new Puzzle25();
  }

  @Override
  public long getExpectedExampleResultPart1() {
    return 58;
  }

  @Override
  public long getExpectedExampleResultPart2() {
    return 44169;
  }
}
