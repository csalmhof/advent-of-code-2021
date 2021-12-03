package com.github.csalmhof.aoc2021;

import java.util.List;

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
    int increasingValues = 0;

    for (int i = slidingWindowSize; i < input.size(); i++) {
      if (sumLastValues(input, slidingWindowSize, i) > sumLastValues(input, slidingWindowSize, i - 1)) {
        increasingValues++;
      }
    }

    return increasingValues;
  }

  private int sumLastValues(List<String> input, int windowSize, int position) {
    int sum = 0;
    int numOfCalculatedValues = 0;

    while (numOfCalculatedValues < windowSize) {
      String value = input.get(position - numOfCalculatedValues);
      sum += Integer.parseInt(value);
      numOfCalculatedValues++;
    }
    return sum;
  }

}
