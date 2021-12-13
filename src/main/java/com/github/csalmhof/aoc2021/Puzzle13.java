package com.github.csalmhof.aoc2021;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle13 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 13;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    int index = IntStream.range(0, input.size())
        .filter(i -> input.get(i).equals(""))
        .findFirst().getAsInt();

    Set<Point> pointSet = input.subList(0, index).stream()
        .map(l -> {
          String[] split = l.split(",");
          return new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        })
        .collect(Collectors.toSet());

    List<Map.Entry<String, Integer>> instructions = input.subList(index + 1, input.size()).stream()
        .map(l -> {
          String[] split = l.substring(11).split("=");
          return Map.entry(split[0], Integer.parseInt(split[1]));
        })
        .collect(Collectors.toList());

    for (Map.Entry<String, Integer> inst : instructions) {
      Set<Point> newPointSet = new HashSet<>();
      for (Point p : pointSet) {
        if (inst.getKey().equals("y")) {
          if (p.y < inst.getValue()) {
            newPointSet.add(p);
          } else { //14 7 1  15 14 13  15-8
            newPointSet.add(new Point(p.x, inst.getValue() - (p.y - inst.getValue())));
          }

        } else {
          if (p.x < inst.getValue()) {
            newPointSet.add(p);
          } else { //14 7 1  15 14 13  15-8
            newPointSet.add(new Point(inst.getValue() - (p.x - inst.getValue()), p.y));
          }
        }
      }
      pointSet = newPointSet;
      return pointSet.size();
    }

    throw new IllegalArgumentException();
  }


  private int getMaxY(Collection<Point> points) {
    return points.stream().map(p -> p.y).max(Comparator.comparing(Function.identity())).get();
  }

  private int getMaxX(Collection<Point> points) {
    return points.stream().map(p -> p.x).max(Comparator.comparing(Function.identity())).get();
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    //no Number-Part 2
    return 0;
  }

  public List<String> getPart2Output(String filePath) {
    List<String> input = getInput(filePath);

    int index = IntStream.range(0, input.size())
        .filter(i -> input.get(i).equals(""))
        .findFirst().getAsInt();

    Set<Point> pointSet = input.subList(0, index).stream()
        .map(l -> {
          String[] split = l.split(",");
          return new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        })
        .collect(Collectors.toSet());

    List<Map.Entry<String, Integer>> instructions = input.subList(index + 1, input.size()).stream()
        .map(l -> {
          String[] split = l.substring(11).split("=");
          return Map.entry(split[0], Integer.parseInt(split[1]));
        })
        .collect(Collectors.toList());

    for (Map.Entry<String, Integer> inst : instructions) {
      Set<Point> newPointSet = new HashSet<>();
      for (Point p : pointSet) {
        if (inst.getKey().equals("y")) {
          if (p.y < inst.getValue()) {
            newPointSet.add(p);
          } else { //14 7 1  15 14 13  15-8
            newPointSet.add(new Point(p.x, inst.getValue() - (p.y - inst.getValue())));
          }

        } else {
          if (p.x < inst.getValue()) {
            newPointSet.add(p);
          } else { //14 7 1  15 14 13  15-8
            newPointSet.add(new Point(inst.getValue() - (p.x - inst.getValue()), p.y));
          }
        }
      }
      pointSet = newPointSet;
    }

    List<String> result = new ArrayList<>();
    for(int y = 0; y <= getMaxY(pointSet); y++) {
      StringBuilder line = new StringBuilder();
      for(int x = 0; x <= getMaxX(pointSet); x++) {
        if(pointSet.contains(new Point(x, y))) {
          line.append("#");
        } else {
          line.append(" ");
        }
      }
      result.add(line.toString());
    }

    return result;
  }


}
