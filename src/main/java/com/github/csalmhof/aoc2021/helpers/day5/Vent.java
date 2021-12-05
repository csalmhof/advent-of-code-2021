package com.github.csalmhof.aoc2021.helpers.day5;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    int dirX = Integer.compare(end.x, start.x);
    int dirY = Integer.compare(end.y, start.y);

    ArrayList<Point> result = new ArrayList<>();

    for (int x = start.x, y = start.y; (x != end.x + dirX) || (y != end.y + dirY); x += dirX, y += dirY) {
      result.add(new Point(x, y));
    }
    return result;
  }

  public boolean isHorizontal() {
    return start.y == end.y;
  }

  public boolean isVertical() {
    return start.x == end.x;
  }

  public boolean isDiagonal() {
    return !isHorizontal() && !isVertical();
  }
}
