package com.github.csalmhof.aoc2021.helpers.day5;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vent {
  Point start;
  Point end;

  public Vent(String s) {
    String[] splitted = s.split(" -> ");
    String[] startsplitted = splitted[0].split(",");
    String[] endsplitted = splitted[1].split(",");
    this.start = new Point(Integer.parseInt(startsplitted[0]), Integer.parseInt(startsplitted[1]));
    this.end = new Point(Integer.parseInt(endsplitted[0]), Integer.parseInt(endsplitted[1]));
  }

  public List<Point> points() {
    return IntStream.range(0, dist()+1)
        .mapToObj(i -> new Point(start.x + (i*dirX()), start.y + (i*dirY())))
        .collect(Collectors.toList());
  }

  private int dist() {
    return Math.max(Math.abs(start.x - end.x), Math.abs(start.y - end.y));
  }

  private int dirY() {
    return Integer.compare(end.y, start.y);
  }

  private int dirX() {
    return Integer.compare(end.x, start.x);
  }

  public boolean isDiagonal() {
    return start.x != end.x && start.y != end.y;
  }
}
