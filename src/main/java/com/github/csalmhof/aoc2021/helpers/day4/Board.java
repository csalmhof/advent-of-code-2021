package com.github.csalmhof.aoc2021.helpers.day4;

import java.util.List;
import java.util.stream.IntStream;

public class Board {

  public static final int DEFAULT_SIZE = 5;

  int[][] vals;
  boolean[][] marked;

  public Board() {
    this.vals = new int[DEFAULT_SIZE][DEFAULT_SIZE];
    this.marked = new boolean[DEFAULT_SIZE][DEFAULT_SIZE];
  }

  public Board(int size) {
    this.vals = new int[size][size];
    this.marked = new boolean[size][size];
  }

  public Board initWithLines(List<String> lines) {
    for (int i = 0; i < vals.length; i++) {
      String[] s = splitBoardLine(lines.get(i));
      for (int j = 0; j < vals.length; j++) {
        vals[i][j] = Integer.parseInt(s[j]);
      }
    }
    return this;
  }

  private String[] splitBoardLine(String s) {
    String[] result = new String[vals.length];
    for (int i = 0; i < vals.length; i++) {
      result[i] = s.substring(i * 3, (Math.min(i * 3 + 3, s.length()))).trim();
    }
    return result;
  }

  public boolean processNumber(int num) {
    for (int i = 0; i < vals.length; i++) {
      for (int j = 0; j < vals.length; j++) {
        if (vals[i][j] == num) {
          markValue(i, j);
        }
      }
    }
    return isWinning();
  }

  private void markValue(int i, int j) {
    marked[i][j] = true;
  }

  public boolean isWinning() {

    boolean rowMatch = IntStream.range(0, vals.length)
        .mapToObj(i ->
            IntStream.range(0, vals.length)
                .mapToObj(j -> marked[i][j])
                .allMatch(mark -> mark))
        .anyMatch(lineMatch -> lineMatch);

    boolean colMatch = IntStream.range(0, vals.length)
        .mapToObj(i ->
            IntStream.range(0, vals.length)
                .mapToObj(j -> marked[j][i])
                .allMatch(mark -> mark))
        .anyMatch(lineMatch -> lineMatch);

    return rowMatch || colMatch;
  }

  public int calcBoard() {
    return IntStream.range(0, vals.length)
        .mapToObj(i ->
            IntStream.range(0, vals.length)
                .filter(j -> !marked[i][j])
                .mapToObj(j -> vals[i][j])
                .reduce(0, Integer::sum))
        .reduce(0, Integer::sum);
  }
}
