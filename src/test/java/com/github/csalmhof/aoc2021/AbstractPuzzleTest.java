package com.github.csalmhof.aoc2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class AbstractPuzzleTest {

  private AbstractPuzzle puzzle;

  public AbstractPuzzleTest() {
    this.puzzle = createPuzzle();
  }

  public abstract long getExpectedExampleResultPart1();

  public abstract long getExpectedExampleResultPart2();

  public abstract AbstractPuzzle createPuzzle();

  public AbstractPuzzle getPuzzle() {
    return puzzle;
  }

  @Test
  public void part1Test() {
    Assertions.assertEquals(puzzle.getPart1Result(buildInputPath(puzzle.getDay(), true)), getExpectedExampleResultPart1());
  }

  @Test
  public void part2Test() {
    Assertions.assertEquals(puzzle.getPart2Result(buildInputPath(puzzle.getDay(), true)), getExpectedExampleResultPart2());
  }

  @Test
  public void printResultPart1() {
    puzzle.printPart1Result(buildInputPath(puzzle.getDay(), false));
  }

  @Test
  public void printResultPart2() {
    puzzle.printPart2Result(buildInputPath(puzzle.getDay(), false));
  }

  public String buildInputPath(int day, boolean example) {
    return "day" + day + "/" + (example ? "example" : "input") + ".txt";
  }

  public String buildInputPath(int day, boolean example, int num) {
    return "day" + day + "/" + (example ? "example" : "input") + num + ".txt";
  }
}
