package com.github.csalmhof.aoc2021;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle07 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 7;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    List<Integer> intInputs = Stream.of(input.get(0).split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());

    long minAlignmentSum = Integer.MAX_VALUE;
    for(int alignmentTo = 0; alignmentTo < maxOfList(intInputs); alignmentTo++) {
      long alignmentSum = 0;
      for(Integer num : intInputs) {
        alignmentSum += Math.abs(num - alignmentTo);
      }
      minAlignmentSum = Math.min(minAlignmentSum, alignmentSum);
    }

    return minAlignmentSum;
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    List<Integer> intInputs = Stream.of(input.get(0).split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());

    long minAlignmentSum = Integer.MAX_VALUE;
    for(int alignmentTo = 0; alignmentTo < maxOfList(intInputs); alignmentTo++) {
      long alignmentSum = 0;
      for(Integer num : intInputs) {
        alignmentSum += gaussianSum(Math.abs(num - alignmentTo));
      }
      minAlignmentSum = Math.min(minAlignmentSum, alignmentSum);
    }

    return minAlignmentSum;
  }

  public Integer maxOfList(List<Integer> l) {
    return l.stream()
        .max(Comparator.comparing(Function.identity()))
        .orElse(null);
  }

  public long gaussianSum(int n) {
    return ((long) n *(n+1))/ 2;
  }
}
