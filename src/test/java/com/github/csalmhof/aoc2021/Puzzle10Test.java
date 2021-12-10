package com.github.csalmhof.aoc2021;

public class Puzzle10Test extends AbstractPuzzleTest {

  @Override
  public AbstractPuzzle createPuzzle() {
    return new Puzzle10();
  }

  @Override
  public long getExpectedExampleResultPart1() {
    return 26397;
  }

  @Override
  public long getExpectedExampleResultPart2() {
    return 288957;
  }
}
