package com.github.csalmhof.aoc2021;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle12Recursive extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 12;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    Map<String, Set<String>> caveMap = inputToMap(input);

    return findPathsRecursive(caveMap, "start", new HashSet<>(), false, false);
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    Map<String, Set<String>> caveMap = inputToMap(input);

    return findPathsRecursive(caveMap, "start", new HashSet<>(), true, false);
  }

  private long findPathsRecursive(Map<String, Set<String>> caveMap, String position, Set<String> visitedSmalls, boolean allowTwiceVisiting, boolean twiceVisited) {
    Set<String> nowVisitedSmalls = new HashSet<>(visitedSmalls);

    boolean nowTwiceVisited = twiceVisited || nowVisitedSmalls.contains(position);

    if (position.toLowerCase().equals(position)) {
      nowVisitedSmalls.add(position);
    }

    if ("end".equals(position)) {
      return 1;
    }

    return caveMap.get(position).stream()
        .filter(next -> !"start".equals(next))
        .filter(next -> {
          if(allowTwiceVisiting && !nowTwiceVisited) {
            return true;
          }
          return !nowVisitedSmalls.contains(next);
        })
        .map(next -> findPathsRecursive(caveMap, next, nowVisitedSmalls, allowTwiceVisiting, nowTwiceVisited))
        .reduce(0L, Long::sum);
  }

  private Map<String, Set<String>> inputToMap(List<String> input) {
    return input.stream()
        .map(l -> l.split("-"))
        .flatMap(l -> {
          String[] dir1 = {l[0], l[1]};
          String[] dir2 = {l[1], l[0]};

          return Stream.of(dir1, dir2);
        })
        .collect(Collectors.groupingBy(l -> l[0]))
        .entrySet()
        .stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e ->
            e.getValue().stream()
                .map(l -> l[1])
                .collect(Collectors.toSet())));
  }
}
