package com.github.csalmhof.aoc2021.helpers.day11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OctopusMap {

  private static final int AFTER_FLASH_VALUE = 0;

  private final Map<Coordinate, Integer> map;
  private final int maxX;
  private final int maxY;

  private OctopusMap(Map<Coordinate, Integer> map, int maxX, int maxY) {
    this.map = map;
    this.maxX = maxX;
    this.maxY = maxY;
  }

  public static OctopusMap of(List<String> input) {
    HashMap<Coordinate, Integer> map = new HashMap<>();

    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length(); j++) {
        map.put(new Coordinate(i, j), Integer.parseInt(input.get(i).charAt(j) + ""));
      }
    }

    return new OctopusMap(map, input.size() - 1, input.get(0).length() - 1);
  }

  public Set<Coordinate> findAfterFlashPoints() {
    return map.entrySet().stream()
        .filter(e -> e.getValue().equals(AFTER_FLASH_VALUE))
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());
  }

  public int findSyncFlash() {
    int i = 0;
    while (findAfterFlashPoints().size() != 100) {
      map.keySet().forEach(this::increasePoint);
      flashRecursive(findAfterFlashPoints());
      i++;
    }

    return i;
  }

  public long flash(int num) {
    long flashes = 0;
    for (int i = 0; i < num; i++) {
      map.keySet().forEach(this::increasePoint);
      flashes += findAfterFlashPoints().size() + flashRecursive(findAfterFlashPoints());
    }
    return flashes;
  }

  private void increasePoint(Coordinate k) {
    if (map.get(k).equals(9)) {
      map.put(k, 0);
    } else {
      map.put(k, map.get(k) + 1);
    }
  }

  private long flashRecursive(Set<Coordinate> flashedPoints) {
    if (flashedPoints.isEmpty()) {
      return 0;
    }

    Set<Coordinate> newlyFlashed = flashedPoints.stream()
        .flatMap(p -> p.getAdjacentCoordinates(maxX, maxY).stream())
        .filter(adjP -> !map.get(adjP).equals(AFTER_FLASH_VALUE))
        .peek(this::increasePoint)
        .filter(adjP -> map.get(adjP).equals(AFTER_FLASH_VALUE))
        .collect(Collectors.toSet());


    return newlyFlashed.size() + flashRecursive(newlyFlashed);
  }
}

