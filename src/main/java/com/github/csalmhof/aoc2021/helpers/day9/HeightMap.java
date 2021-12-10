package com.github.csalmhof.aoc2021.helpers.day9;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HeightMap {

  private static final int TOP_VALUE = 9;

  private final Map<Coordinate, Integer> map;
  private final int maxX;
  private final int maxY;

  private HeightMap(Map<Coordinate, Integer> map, int maxX, int maxY) {
    this.map = map;
    this.maxX = maxX;
    this.maxY = maxY;
  }

  public static HeightMap of(List<String> input) {
    HashMap<Coordinate, Integer> map = new HashMap<>();

    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length(); j++) {
        map.put(new Coordinate(i, j), Integer.parseInt(input.get(i).charAt(j) + ""));
      }
    }

    return new HeightMap(map, input.size()-1, input.get(0).length() -1);
  }

  public Map<Coordinate, Integer> findLowPoints() {
    return map.entrySet().stream()
        .filter(e -> isMinPointOfMap(e.getKey()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  private boolean isMinPointOfMap(Coordinate point) {
    return point.getAdjacentCoordinates(maxX, maxY).stream()
        .noneMatch(adjCoordinate -> map.get(adjCoordinate) <= map.get(point));
  }

  public List<List<Coordinate>> findBasins() {
    Map<Coordinate, Boolean> markedCoordinates = map.keySet().stream()
        .collect(Collectors.toMap(Function.identity(), (e) -> Boolean.FALSE));

    return findLowPoints().keySet().stream()
        .map(point -> evaluateBasinRecursive(point, markedCoordinates))
        .filter(basin -> !basin.isEmpty())
        .collect(Collectors.toList());
  }

  private List<Coordinate> evaluateBasinRecursive(Coordinate point, Map<Coordinate, Boolean> markedCoordinates) {
    if(Boolean.TRUE.equals(markedCoordinates.get(point)) || map.get(point) == TOP_VALUE) {
      return Collections.emptyList();
    }
    List<Coordinate> basin = new ArrayList<>();

    markedCoordinates.put(point, true);
    basin.add(point);

    point.getAdjacentCoordinates(maxX, maxY).forEach(adjPoint -> basin.addAll(evaluateBasinRecursive(adjPoint, markedCoordinates)));

    return basin;
  }
}
