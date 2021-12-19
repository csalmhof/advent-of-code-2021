package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day18.SnailfishNumber;

import java.util.List;

public class Puzzle18 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 18;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    SnailfishNumber first = SnailfishNumber.parse(input.get(0));

    SnailfishNumber sum = input.stream().skip(1)
        .map(SnailfishNumber::parse)
        .reduce(first, SnailfishNumber::add);

    return sum.getMagnitude();
  }

  @Override
  public long calculatePart2Result(List<String> input) {

    long maxProductMagnitude = 0;

    for(int i = 0; i < input.size(); i++) {
      for(int j = 0; j < input.size(); j++) {
        if(i == j) {
          continue;
        }
        long mag1 = SnailfishNumber.parse(input.get(i)).add(SnailfishNumber.parse(input.get(j))).getMagnitude();
        long mag2 = SnailfishNumber.parse(input.get(j)).add(SnailfishNumber.parse(input.get(i))).getMagnitude();

        long localMaxMag = Math.max(mag1, mag2);

        maxProductMagnitude = Math.max(maxProductMagnitude, localMaxMag);
      }
    }

    return maxProductMagnitude;
  }
}

