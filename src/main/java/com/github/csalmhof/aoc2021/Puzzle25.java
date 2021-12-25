package com.github.csalmhof.aoc2021;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle25 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 25;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    CucumberField field = CucumberField.of(input);

    int moves = 1;

    while(field.move()) {
      moves++;
    }

    return moves;
  }


  @Override
  public long calculatePart2Result(List<String> input) {
    return 0;
  }

  public static class CucumberField {
    private final int width;
    private final int height;

    private Map<Point, Cucumber> map = new HashMap<>();

    private CucumberField(int width, int height) {
      this.width = width;
      this.height = height;
    }

    public static CucumberField of(List<String> lines) {
      CucumberField result = new CucumberField(lines.get(0).length(), lines.size());

      for (int y = 0; y < result.height; y++) {
        for (int x = 0; x < result.width; x++) {
          if (lines.get(y).charAt(x) == '>') {
            result.map.put(new Point(x, y), Cucumber.MOVING_RIGHT);
          }
          if (lines.get(y).charAt(x) == 'v') {
            result.map.put(new Point(x, y), Cucumber.MOVING_DOWN);
          }
        }
      }
      return result;
    }

    public boolean move() {
      boolean movedRight = moveRightDirection() > 0;
      boolean movedDown = moveDownDirection() > 0;

      return movedRight || movedDown;
    }

    private int moveRightDirection() {
      Map<Point, Cucumber> nextMap = new HashMap<>();
      int moved = 0;

      for (Map.Entry<Point, Cucumber> entry : map.entrySet()) {
        Point currentPosition = entry.getKey();
        Point rightPosition = new Point((currentPosition.x+1)%width, currentPosition.y);
        if (Cucumber.MOVING_RIGHT.equals(entry.getValue())) {
          if(!map.containsKey(rightPosition)) {
            nextMap.put(rightPosition, entry.getValue());
            moved++;
            continue;
          }
        }
        nextMap.put(currentPosition, entry.getValue());
      }

      this.map = nextMap;
      return moved;
    }

    private int moveDownDirection() {
      Map<Point, Cucumber> nextMap = new HashMap<>();
      int moved = 0;

      for (Map.Entry<Point, Cucumber> entry : map.entrySet()) {
        Point currentPosition = entry.getKey();
        Point downPosition = new Point(currentPosition.x, (currentPosition.y+1)%height);
        if (Cucumber.MOVING_DOWN.equals(entry.getValue())) {
          if(!map.containsKey(downPosition)) {
            nextMap.put(downPosition, entry.getValue());
            moved++;
            continue;
          }
        }
        nextMap.put(currentPosition, entry.getValue());
      }

      this.map = nextMap;
      return moved;
    }

  }

  public enum Cucumber {
    MOVING_RIGHT,
    MOVING_DOWN
  }
}

