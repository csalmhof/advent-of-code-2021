package com.github.csalmhof.aoc2021;

public class Puzzle15Test extends AbstractPuzzleTest {

  @Override
  public AbstractPuzzle createPuzzle() {
    return new Puzzle15();
  }

  @Override
  public long getExpectedExampleResultPart1() {
    return 40;
  }

  @Override
  public long getExpectedExampleResultPart2() {
    return 315;
  }
}
