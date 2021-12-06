package com.github.csalmhof.aoc2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle06 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 6;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    Map<Integer, Long> fishCnt = transformInput(input);

    for (int i = 0; i < 80; i++) {
      fishCnt = processDay(fishCnt);
    }

    return fishCnt.values().stream().reduce(0L, Long::sum);
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    Map<Integer, Long> fishCnt = transformInput(input);

    for (int i = 0; i < 256; i++) {
      fishCnt = processDay(fishCnt);
    }

    return fishCnt.values().stream().reduce(0L, Long::sum);
  }

  private Map<Integer, Long> transformInput(List<String> input) {
    return Stream.of(input.get(0).split(","))
        .map(Integer::parseInt)
        .collect(Collectors.groupingBy(Function.identity()))
        .entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> (long) e.getValue().size()));
  }

  private Map<Integer, Long> processDay(Map<Integer, Long> fishCnt) {
    Map<Integer, Long> result = new HashMap<>();

    fishCnt.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .forEach(e -> {
          if(e.getKey() == 0) {
            result.put(6, e.getValue());
            result.put(8, e.getValue());
          } else {
            result.putIfAbsent(e.getKey()-1, 0L);
            result.put(e.getKey()-1, result.get(e.getKey()-1) + e.getValue());
          }
        });

    return result;
  }



}
