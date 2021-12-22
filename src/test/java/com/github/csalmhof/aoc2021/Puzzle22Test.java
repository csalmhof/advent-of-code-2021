package com.github.csalmhof.aoc2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Puzzle22Test extends AbstractPuzzleTest {

  @Override
  public AbstractPuzzle createPuzzle() {
    return new Puzzle22();
  }

  @Override
  public long getExpectedExampleResultPart1() {
    return 590784;
  }

  @Override
  public long getExpectedExampleResultPart2() {
    return 2758514936282235L;
  }

  @Test
  @Override
  public void part2Test() {
    Assertions.assertEquals(getPuzzle().getPart2Result(buildInputPath(getPuzzle().getDay(), true, 2)), getExpectedExampleResultPart2());
  }
}
