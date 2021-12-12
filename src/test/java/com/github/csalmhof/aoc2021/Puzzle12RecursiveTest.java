package com.github.csalmhof.aoc2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Puzzle12RecursiveTest extends AbstractPuzzleTest {

  @Override
  public AbstractPuzzle createPuzzle() {
    return new Puzzle12Recursive();
  }

  @Override
  public long getExpectedExampleResultPart1() {
    return 10;
  }

  @Override
  public long getExpectedExampleResultPart2() {
    return 36;
  }

  @Test
  public void part1Test2() {
    Assertions.assertEquals(getPuzzle().getPart1Result(buildInputPath(getPuzzle().getDay(), true, 1)), 19);
  }

  @Test
  public void part1Test3() {
    Assertions.assertEquals(getPuzzle().getPart1Result(buildInputPath(getPuzzle().getDay(), true, 2)), 226);
  }

  @Test
  public void part2Test2() {
    Assertions.assertEquals(getPuzzle().getPart2Result(buildInputPath(getPuzzle().getDay(), true, 1)), 103);
  }

  @Test
  public void part2Test3() {
    Assertions.assertEquals(getPuzzle().getPart2Result(buildInputPath(getPuzzle().getDay(), true, 2)), 3509);
  }
}
