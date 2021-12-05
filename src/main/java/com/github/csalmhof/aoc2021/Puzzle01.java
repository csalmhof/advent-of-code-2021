package com.github.csalmhof.aoc2021;

import java.util.List;
import java.util.stream.IntStream;

public class Puzzle01 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 1;
  }

  @Override
  public int calculatePart1Result(List<String> input) {
    return calculateResult(input, 1);
  }

  @Override
  public int calculatePart2Result(List<String> input) {
    return calculateResult(input, 3);
  }

  private int calculateResult(List<String> input, int slidingWindowSize) {
    return (int) IntStream.range(slidingWindowSize, input.size())
        .filter(i -> sumLastValues(input, slidingWindowSize, i) > sumLastValues(input, slidingWindowSize, i-1))
        .count();
  }

  private int sumLastValues(List<String> input, int windowSize, int position) {
    return IntStream.range(0, windowSize)
        .mapToObj(i -> Integer.parseInt(input.get(position-i)))
        .reduce(0, Integer::sum);
  }

}
