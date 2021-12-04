package com.github.csalmhof.aoc2021;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle04 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 4;
  }

  @Override
  public int calculatePart1Result(List<String> input) {
    List<Integer> numbers = Stream.of(input.get(0).split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());

    input = input.subList(1, input.size());

    List<Board> boards = new ArrayList<>();

    while (input.size() > 5) {
      input = input.subList(1, input.size());
      boards.add(new Board(input.subList(0, 5)));
      input = input.subList(5, input.size());
    }

    Board winningBoard = null;
    int winningNum = -1;


    for(int num : numbers) {
      boolean winnerFound = false;
      for(Board board : boards) {
        if(board.processNumber(num)) {
          winningBoard = board;
          winningNum = num;
          winnerFound = true;
          break;
        }
      }
      if(winnerFound) {
        break;
      }
    }

    return winningBoard.calcBoard() * winningNum;
  }

  @Override
  public int calculatePart2Result(List<String> input) {
    List<Integer> numbers = Stream.of(input.get(0).split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());

    input = input.subList(1, input.size());

    List<Board> boards = new ArrayList<>();

    while (input.size() > 5) {
      input = input.subList(1, input.size());
      boards.add(new Board(input.subList(0, 5)));
      input = input.subList(5, input.size());
    }

    Board winningBoard = null;
    int winningNum = -1;


    for(int num : numbers) {
      boolean winnerFound = false;
      for(Iterator<Board> it = boards.iterator(); it.hasNext();) {
        Board board = it.next();
        if(board.processNumber(num)) {
          if(boards.size() == 1) {
            winningBoard = board;
            winningNum = num;
          }
          it.remove();
        }
      }
    }

    return winningBoard.calcBoard() * winningNum;
  }

  public static class Board {
    int[][] vals = new int[5][5];
    boolean[][] marked = new boolean[5][5];

    public Board(List<String> lines) {
      for (int i = 0; i < 5; i++) {
        String[] s = splitBoardLine(lines.get(i));
        for (int j = 0; j < 5; j++) {
          vals[i][j] = Integer.parseInt(s[j]);
        }
      }
    }

    private String[] splitBoardLine(String s) {
      String[] result = new String[5];
      for(int i = 0; i < 5; i++) {
        result[i] = s.substring(i*3, (Math.min(i * 3 + 3, s.length()))).trim();
      }
      return result;
    }

    public boolean processNumber(int num) {
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
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

      for (int i = 0; i < 5; i++) {
        boolean rowMatch = true;
        for(int j = 0; j < 5; j++) {
          if (!marked[i][j]) {
            rowMatch = false;
            break;
          }
        }
        if(rowMatch) {
          return true;
        }
      }

      for (int i = 0; i < 5; i++) {
        boolean colMatch = true;
        for(int j = 0; j < 5; j++) {
          if (!marked[j][i]) {
            colMatch = false;
            break;
          }
        }
        if(colMatch) {
          return true;
        }
      }

      return false;
    }

    public int calcBoard() {
      int sum = 0;
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          if (!marked[i][j]) {
            sum += vals[i][j];
          }
        }
      }
      return sum;
    }
  }
}
