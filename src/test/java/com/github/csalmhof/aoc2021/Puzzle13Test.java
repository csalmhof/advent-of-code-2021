package com.github.csalmhof.aoc2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Puzzle13Test extends AbstractPuzzleTest {

  @Override
  public AbstractPuzzle createPuzzle() {
    return new Puzzle13();
  }

  @Override
  public long getExpectedExampleResultPart1() {
    return 17;
  }

  @Override
  public long getExpectedExampleResultPart2() {
    return 195;
  }

  @Override
  @Test
  public void part2Test() {
    List<String> expected = new ArrayList<>();
    expected.add("#####");
    expected.add("#   #");
    expected.add("#   #");
    expected.add("#   #");
    expected.add("#####");

    List<String> result = ((Puzzle13) getPuzzle()).getPart2Output(buildInputPath(getPuzzle().getDay(), true));

    for(int i = 0; i < expected.size(); i++) {
      Assertions.assertEquals(result.get(i), expected.get(i));
    }
  }

  @Override
  @Test
  public void printResultPart2() {
    List<String> result = ((Puzzle13) getPuzzle()).getPart2Output(buildInputPath(getPuzzle().getDay(), false));

    for(String line : result) {
      System.out.println(line);
    }
  }
}
