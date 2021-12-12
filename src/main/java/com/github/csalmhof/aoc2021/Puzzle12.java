package com.github.csalmhof.aoc2021;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle12 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 12;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    Map<String, Set<String>> caveMap = inputToMap(input);

    return getNumOfPaths(caveMap, false);
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    Map<String, Set<String>> caveMap = inputToMap(input);

    return getNumOfPaths(caveMap, true);
  }

  private Map<String, Set<String>> inputToMap(List<String> input) {
    Map<String, Set<String>> cave = input.stream()
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
    return cave;
  }

  private int getNumOfPaths(Map<String, Set<String>> caveMap, boolean oneSmallTwiceAllowed) {
    int paths = 0;

    Queue<CavePath> queue = new ArrayDeque<>();
    queue.add(new CavePath("start", Stream.of("start").collect(Collectors.toSet()), null));

    while (!queue.isEmpty()) {
      CavePath p = queue.poll();

      if ("end".equals(p.position)) {
        paths++;
        continue;
      }

      for (String next : caveMap.get(p.position)) {
        if (!p.smallsVisited.contains(next)) {
          if (next.toLowerCase().equals(next)) {
            queue.add(new CavePath(next, Stream.concat(p.smallsVisited.stream(), Stream.of(next)).collect(Collectors.toSet()), p.smallVisitedTwice));
          } else {
            queue.add(new CavePath(next, p.smallsVisited, p.smallVisitedTwice));
          }
        } else if (oneSmallTwiceAllowed && !"start".equals(next)) {
          if (p.smallVisitedTwice == null) {
            queue.add(new CavePath(next, p.smallsVisited, next));
          }
        }
      }
    }

    return paths;
  }

  public static class CavePath {
    String position;
    Set<String> smallsVisited;
    String smallVisitedTwice;

    public CavePath(String position, Set<String> smallsVisited, String smallVisitedTwice) {
      this.position = position;
      this.smallsVisited = smallsVisited;
      this.smallVisitedTwice = smallVisitedTwice;
    }
  }
}
