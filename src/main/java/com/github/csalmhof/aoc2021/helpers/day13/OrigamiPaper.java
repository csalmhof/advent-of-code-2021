package com.github.csalmhof.aoc2021.helpers.day13;

import java.awt.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OrigamiPaper {

  Set<Point> points;

  private OrigamiPaper(Set<Point> points) {
    this.points = points;
  }

  public static OrigamiPaper of(List<String> pointRepresentation) {
    Set<Point> points =  pointRepresentation.stream()
        .map(l -> {
          String[] split = l.split(",");
          return new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        })
        .collect(Collectors.toSet());

    return new OrigamiPaper(points);
  }

  public Set<Point> getPoints() {
    return points;
  }

  public void fold(String dir, int position) {
    fold(FoldDirection.of(dir), position);
  }

  public void fold(FoldDirection dir, int position) {
    Set<Point> newPointSet = new HashSet<>();
    for (Point p : points) {
      if (FoldDirection.VERTICAL.equals(dir)) {
        if (p.y < position) {
          newPointSet.add(p);
        } else {
          newPointSet.add(new Point(p.x, position - (p.y - position)));
        }
      } else {
        if (p.x < position) {
          newPointSet.add(p);
        } else {
          newPointSet.add(new Point(position - (p.x - position), p.y));
        }
      }
    }
    points = newPointSet;
  }

  private int getMaxY() {
    return points.stream()
        .map(p -> p.y)
        .max(Comparator.comparing(Function.identity()))
        .orElse(0);
  }

  private int getMaxX() {
    return points.stream()
        .map(p -> p.x)
        .max(Comparator.comparing(Function.identity()))
        .orElse(0);
  }

  @Override
  public String toString() {
    return IntStream.range(0, getMaxY()+1)
        .mapToObj(y -> IntStream.range(0, getMaxX()+1)
            .mapToObj(x -> points.contains(new Point(x, y)) ? "#" : " ")
            .collect(Collectors.joining()))
        .collect(Collectors.joining("\n"));
  }

  public enum FoldDirection {
    VERTICAL("y"),
    HORIZONTAL("x");

    private final String coordLine;

    FoldDirection(String coordLine) {
      this.coordLine = coordLine;
    }

    public static FoldDirection of(String coordLine) {
      return Stream.of(FoldDirection.values())
          .filter(f -> f.coordLine.equals(coordLine))
          .findFirst().get();
    }
  }
}
