package com.github.csalmhof.aoc2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Puzzle23Test extends AbstractPuzzleTest {

  @Override
  public AbstractPuzzle createPuzzle() {
    return new Puzzle23();
  }

  @Override
  public long getExpectedExampleResultPart1() {
    return 12521;
  }

  @Override
  public long getExpectedExampleResultPart2() {
    return 44169;
  }

  @Test
  @Override
  public void part2Test() {
    Assertions.assertEquals(getPuzzle().getPart2Result(buildInputPath(getPuzzle().getDay(), true, 2)), getExpectedExampleResultPart2());
  }

  @Test
  @Override
  public void printResultPart2() {
    System.out.println(getPuzzle().getPart2Result(buildInputPath(getPuzzle().getDay(), false, 2)));
  }
}
