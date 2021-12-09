package com.github.csalmhof.aoc2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Puzzle09 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 9;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    char[][] map = new char[input.size()][input.get(0).length()];

    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length(); j++) {
        map[i][j] = input.get(i).charAt(j);
      }
    }

    List<Long> adj = new ArrayList<>();
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (isAdjacent(map, i, j)) {
          adj.add(Long.parseLong(map[i][j] + "") + 1);
        }
      }
    }

    return adj.stream().reduce(0L, Long::sum);

  }

  private boolean isAdjacent(char[][] map, int i, int j) {
    char toCheck = map[i][j];
    for (int i2 = -1; i2 < 2; i2++) {
      if ((i + i2 >= 0) && (i + i2 < map.length)) {
        for (int j2 = -1; j2 < 2; j2++) {
          if ((j + j2 >= 0) && (j + j2 < map[0].length)) {
            if (Math.abs(j2) != Math.abs(i2)) {
              if (map[i + i2][j + j2] <= toCheck) {
                return false;
              }
            }
          }
        }
      }
    }

    return true;
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    char[][] map = new char[input.size()][input.get(0).length()];
    boolean[][] marked = new boolean[input.size()][input.get(0).length()];

    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length(); j++) {
        map[i][j] = input.get(i).charAt(j);
      }
    }

    List<Long> bas = new ArrayList<>();
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (!marked[i][j] && map[i][j] != '9') {
          List<Character> basin = basin(map, marked, i, j);
          bas.add((long) basin.size());
        }
      }
    }

    bas.sort(Comparator.comparing(i -> -i));
    return bas.get(0) * bas.get(1) * bas.get(2);
  }

  private List<Character> basin(char[][] map, boolean[][] marked, int i, int j) {
    char toCheck = map[i][j];
    if (marked[i][j] || map[i][j] == '9') {
      return Collections.emptyList();
    }
    marked[i][j] = true;
    List<Character> basin = new ArrayList<>();
    basin.add(toCheck);
    for (int i2 = -1; i2 < 2; i2++) {
      if ((i + i2 >= 0) && (i + i2 < map.length)) {
        for (int j2 = -1; j2 < 2; j2++) {
          if ((j + j2 >= 0) && (j + j2 < map[0].length)) {
            if (Math.abs(j2) != Math.abs(i2)) {
              if (!marked[i + i2][j + j2]) {
                if ((map[i + i2][j + j2] <= toCheck  || map[i + i2][j + j2] > toCheck)) {

                  basin.addAll(basin(map, marked, i + i2, j + j2));
                }
              }
            }
          }
        }
      }
    }
    return basin;

  }
}
