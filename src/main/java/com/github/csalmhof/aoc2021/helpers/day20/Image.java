package com.github.csalmhof.aoc2021.helpers.day20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Image {

  private final String decode;
  private Map<Coordinate, Long> map;
  private int maxX;
  private int maxY;

  private Image(Map<Coordinate, Long> map, String decode, int maxX, int maxY) {
    this.map = map;
    this.maxX = maxX;
    this.maxY = maxY;
    this.decode = decode;
  }

  public static Image of(List<String> input) {
    String decode = input.get(0);

    HashMap<Coordinate, Long> map = new HashMap<>();

    for (int i = 2; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length(); j++) {
        map.put(new Coordinate(i - 2, j), input.get(i).charAt(j) == '#' ? 1L : 0L);
      }
    }

    return new Image(map, decode, input.size() - 3, input.get(2).length() - 1);
  }

  public long getNumOfPoints() {
    return map.values().stream()
        .reduce(0L, Long::sum);
  }

  public void enhance(int times) {
    for(int i = 0; i < times; i++) {
      enhanceImage(i);
    }
  }

  public void enhanceImage(int iteration) {
    extend(iteration);

    Map<Coordinate, Long> newMap = new HashMap<>();

    map.forEach((key, value) -> {
      String binary = "";
      binary += getValueInDirection(key, -1, -1, iteration);
      binary += getValueInDirection(key, -1, 0, iteration);
      binary += getValueInDirection(key, -1, 1, iteration);
      binary += getValueInDirection(key, 0, -1, iteration);
      binary += value;
      binary += getValueInDirection(key, 0, 1, iteration);
      binary += getValueInDirection(key, 1, -1, iteration);
      binary += getValueInDirection(key, 1, 0, iteration);
      binary += getValueInDirection(key, 1, 1, iteration);

      newMap.put(key, decode.charAt(Integer.parseInt(binary, 2)) == '#' ? 1L : 0L);
    });

    this.map = newMap;
  }

  private long getValueInDirection(Coordinate coord, int x, int y, int iteration) {
    return map.getOrDefault(coord.getCoordinateInDirection(new Coordinate(x, y)), standardPixel(iteration));
  }

  private void extend(int iteration) {
    Map<Coordinate, Long> newMap = new HashMap<>();

    for (int i = 0; i <= maxX+2; i++) {
      newMap.put(new Coordinate(i, 0), standardPixel(iteration));
      newMap.put(new Coordinate(i, maxY+2), standardPixel(iteration));
    }

    for (int i = 0; i <= maxY+2; i++) {
      newMap.put(new Coordinate(0, i), standardPixel(iteration));
      newMap.put(new Coordinate(maxX+2, i ), standardPixel(iteration));
    }

    map.forEach((key, value) -> newMap.put(key.getCoordinateInDirection(new Coordinate(1, 1)), value));

    this.map = newMap;
    this.maxX = maxX + 2;
    this.maxY = maxY + 2;
  }

  private long standardPixel(int iteration) {
    if(iteration == 0) {
      return 0L;
    }
    if(decode.charAt(0) == '.') {
      return 0L;
    }

    if(decode.charAt(511) == '#') {
      return 1L;
    }

    return (iteration)%2;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    IntStream.range(0, maxX+1)
        .forEach(x -> {
          IntStream.range(0, maxY+1)
              .forEach(y -> sb.append(map.get(new Coordinate(x, y)).equals(1L) ? "#" : "."));
          sb.append("\n");
        });

    return sb.toString();
  }
}

