package com.github.csalmhof.aoc2021.helpers.day20;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Coordinate {
  private static final List<Coordinate> adjacentDirections = new ArrayList<>();
  static {
    adjacentDirections.add(new Coordinate(0, -1));
    adjacentDirections.add(new Coordinate(0, 1));
    adjacentDirections.add(new Coordinate(-1, 0));
    adjacentDirections.add(new Coordinate(1, 0));
    adjacentDirections.add(new Coordinate(1, 1));
    adjacentDirections.add(new Coordinate(1, -1));
    adjacentDirections.add(new Coordinate(-1, 1));
    adjacentDirections.add(new Coordinate(-1, -1));
  }

  private final int x;
  private final int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Coordinate getCoordinateInDirection(Coordinate direction) {
    return new Coordinate(this.x + direction.getX(), this.y + direction.getY());
  }

  public List<Coordinate> getAdjacentCoordinates(int maxX, int maxY) {
    return adjacentDirections.stream()
        .map(this::getCoordinateInDirection)
        .filter(adjCoordinate -> adjCoordinate.getX() >= 0)
        .filter(adjCoordinate -> adjCoordinate.getX() <= maxX)
        .filter(adjCoordinate -> adjCoordinate.getY() >= 0)
        .filter(adjCoordinate -> adjCoordinate.getY() <= maxY)
        .collect(Collectors.toList());
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coordinate point = (Coordinate) o;
    return x == point.x && y == point.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
