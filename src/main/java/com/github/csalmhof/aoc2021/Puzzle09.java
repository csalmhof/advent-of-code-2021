package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day9.Coordinate;
import com.github.csalmhof.aoc2021.helpers.day9.HeightMap;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Puzzle09 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 9;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    HeightMap heightMap = HeightMap.of(input);

    Map<Coordinate, Integer> lowPoints = heightMap.findLowPoints();

    return lowPoints.values().stream().reduce(0, (i1, i2) -> i1 + i2 + 1);
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    HeightMap heightMap = HeightMap.of(input);

    List<List<Coordinate>> basins = heightMap.findBasins();

    return basins.stream()
        .map(List::size)
        .sorted(Comparator.comparing(i -> -i))
        .limit(3)
        .reduce(1, (i1, i2) -> i1*i2);
  }
}
