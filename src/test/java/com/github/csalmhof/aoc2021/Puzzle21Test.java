package com.github.csalmhof.aoc2021;

public class Puzzle21Test extends AbstractPuzzleTest {

  @Override
  public AbstractPuzzle createPuzzle() {
    return new Puzzle21();
  }

  @Override
  public long getExpectedExampleResultPart1() {
    return 739785;
  }

  @Override
  public long getExpectedExampleResultPart2() {
    return 444356092776315L;
  }
}
