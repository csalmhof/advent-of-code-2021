package com.github.csalmhof.aoc2021;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Puzzle05 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 5;
  }

  @Override
  public int calculatePart1Result(List<String> input) {
    List<Vent> vents = parseInput(input);

    Map<Point, Integer> cntMap = new HashMap<>();
    vents.stream()
        .flatMap(vent -> Stream.concat(vent.passedPointsHor().stream(), vent.passedPointsVer().stream()))
        .forEach(p -> {
          cntMap.putIfAbsent(p, 0);
          cntMap.put(p, cntMap.get(p) + 1);
        });

    return (int) cntMap.values().stream()
        .filter(cnt -> cnt > 1)
        .count();
  }

  @Override
  public int calculatePart2Result(List<String> input) {
    List<Vent> vents = parseInput(input);

    Map<Point, Integer> cntMap = new HashMap<>();
    vents.stream()
        .flatMap(vent -> Stream.concat(Stream.concat(vent.passedPointsHor().stream(), vent.passedPointsVer().stream()), vent.passedPointsDiagonal().stream()))
        .forEach(p -> {
          cntMap.putIfAbsent(p, 0);
          cntMap.put(p, cntMap.get(p) + 1);
        });

    return (int) cntMap.values().stream()
        .filter(cnt -> cnt > 1)
        .count();
  }

  public List<Vent> parseInput(List<String> input) {
    return input.stream()
        .map(this::parseLine)
        .collect(Collectors.toList());
  }

  public Vent parseLine(String input) {
    String[] splitted = input.split(" -> ");
    String[] startsplitted = splitted[0].split(",");
    String[] endsplitted = splitted[1].split(",");
    Point start = new Point(Integer.parseInt(startsplitted[0]), Integer.parseInt(startsplitted[1]));
    Point end = new Point(Integer.parseInt(endsplitted[0]), Integer.parseInt(endsplitted[1]));

    return new Vent(start, end);
  }

  public static class Point {
    int x;
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      Point point = (Point) o;
      return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  public static class Vent {
    Point start;
    Point end;

    public Vent(Point start, Point end) {
      this.start = start;
      this.end = end;
    }

    public boolean isHorizontal() {
      return start.y == end.y;
    }

    public boolean isVertical() {
      return start.x == end.x;
    }

    public boolean isDiagonal() {
      return Math.abs(start.x - end.x) == Math.abs(start.y - end.y);
    }

    public List<Point> passedPointsHor() {
      if (isHorizontal()) {
        return IntStream.range(Math.min(start.x, end.x), Math.max(start.x, end.x) + 1)
            .mapToObj(x -> new Point(x, start.y))
            .collect(Collectors.toList());
      }
      return Collections.emptyList();
    }

    public List<Point> passedPointsVer() {
      if (isVertical()) {
        return IntStream.range(Math.min(start.y, end.y), Math.max(start.y, end.y) + 1)
            .mapToObj(y -> new Point(start.x, y))
            .collect(Collectors.toList());
      }
      return Collections.emptyList();
    }

    public List<Point> passedPointsDiagonal() {
      List<Point> result = new ArrayList<>();
      if (isDiagonal()) {
        int y = start.y;
        for (int x = start.x; start.x < end.x ? x <= end.x : x >= end.x; ) {
          result.add(new Point(x, y));
          if (start.y < end.y) {
            y++;
          } else {
            y--;
          }

          if (start.x < end.x) {
            x++;
          } else {
            x--;
          }
        }
      }
      return result;
    }
  }
}
