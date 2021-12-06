package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day5.Vent;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Puzzle05 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 5;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    return (int) parseInput(input).stream()
        .filter(v -> !v.isDiagonal())
        .flatMap(vent -> vent.points().stream())
        .collect(Collectors.groupingBy(Function.identity()))
        .values().stream()
        .collect(Collectors.toMap(Function.identity(), List::size))
        .values().stream()
        .filter(i -> i > 1)
        .count();
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    return (int) parseInput(input).stream()
        .flatMap(vent -> vent.points().stream())
        .collect(Collectors.groupingBy(Function.identity()))
        .values().stream()
        .collect(Collectors.toMap(Function.identity(), List::size))
        .values().stream()
        .filter(i -> i > 1)
        .count();
  }

  public List<Vent> parseInput(List<String> input) {
    return input.stream()
        .map(Vent::new)
        .collect(Collectors.toList());
  }
}
