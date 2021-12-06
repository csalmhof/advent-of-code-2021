package com.github.csalmhof.aoc2021;

public class Puzzle01Test extends AbstractPuzzleTest {

  @Override
  public AbstractPuzzle getPuzzleInstance() {
    return new Puzzle01();
  }

  @Override
  public long getExpectedExampleResultPart1() {
    return 7;
  }

  @Override
  public long getExpectedExampleResultPart2() {
    return 5;
  }
}
