package com.github.csalmhof.aoc2021.helpers.day15;

import java.util.*;

public class RiskMap {

  private final Map<Coordinate, Integer> map;
  private final int maxX;
  private final int maxY;

  private RiskMap(Map<Coordinate, Integer> map, int maxX, int maxY) {
    this.map = map;
    this.maxX = maxX;
    this.maxY = maxY;
  }

  public static RiskMap of(List<String> input) {
    HashMap<Coordinate, Integer> map = new HashMap<>();

    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length(); j++) {
        map.put(new Coordinate(i, j), Integer.parseInt(input.get(i).charAt(j) + ""));
      }
    }

    return new RiskMap(map, input.size() - 1, input.get(0).length() - 1);
  }

  public static RiskMap of(List<String> input, int times) {
    HashMap<Coordinate, Integer> map = new HashMap<>();

    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length(); j++) {
        for (int t = 0; t < times; t++) {
          for (int t2 = 0; t2 < times; t2++) {
            int value = Integer.parseInt(input.get(i).charAt(j) + "");
            map.put(new Coordinate(i + input.size() * t, j + input.get(i).length() * t2), (value + t + t2) < 10 ? ((value + t + t2)) : ((value + t + t2)%9));
          }
        }
      }
    }
    return new RiskMap(map, input.size() * times - 1, input.size() * times - 1);
  }

  public long getLowestPathLen() {
    Queue<RiskPath> queue = new PriorityQueue<>();
    queue.add(new RiskPath(new Coordinate(0, 0), Collections.emptySet(), 0, map));

    Map<Coordinate, Long> costMap = new HashMap<>();

    while (!queue.isEmpty()) {
      RiskPath p = queue.poll();

      if (costMap.containsKey(p.position) && p.length >= costMap.get(p.position)) {
        continue;
      }

      costMap.put(p.position, p.length);

      p.position.getAdjacentCoordinates(maxX, maxY).stream()
          .filter(np -> !p.visited.contains(np))
          .filter(np -> !costMap.containsKey(np) || costMap.get(np) >= p.length + map.get(np))
          .forEach(np -> queue.add(new RiskPath(np, p.visited, p.length, map)));
    }

    return costMap.get(new Coordinate(maxX, maxY)) - map.get(new Coordinate(0, 0));
  }

  private static class RiskPath implements Comparable<RiskPath> {
    Coordinate position;
    Set<Coordinate> visited;
    long length;

    public RiskPath(Coordinate position, Set<Coordinate> visited, long prevLen, Map<Coordinate, Integer> map) {
      this.position = position;
      this.visited = new HashSet<>(visited);
      this.visited.add(position);
      this.length = prevLen + map.get(position);
    }

    @Override
    public int compareTo(RiskPath riskPath) {
      return Long.compare(this.length, riskPath.length);
    }
  }
}

