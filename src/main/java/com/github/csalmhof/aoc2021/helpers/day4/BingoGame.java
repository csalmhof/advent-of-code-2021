package com.github.csalmhof.aoc2021.helpers.day4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BingoGame {

  private List<Integer> numbers;
  private List<Board> boards = new ArrayList<>();

  private int firstWinningNumber;
  private Board firstWinningBoard;
  private int lastWinningNumber;
  private Board lastWinningBoard;

  public void initNumbers(String string) {
    this.numbers = Stream.of(string.split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }

  public void addBoard(Board board) {
    boards.add(board);
  }

  public void addBoards(Collection<Board> boards) {
    this.boards.addAll(boards);
  }

  public void play() {
    List<Board> playingBoards = new ArrayList<>(boards);
    for (int num : numbers) {
      List<Board> winners = new ArrayList<>();
      for (Board board : playingBoards) {
        if (board.processNumber(num)) {
          if (firstWinningBoard == null) {
            firstWinningBoard = board;
            firstWinningNumber = num;
          } else if (playingBoards.size() == 1) {
            lastWinningBoard = board;
            lastWinningNumber = num;
          }
          winners.add(board);
        }
      }
      playingBoards.removeAll(winners);
    }
  }

  public int getFirstWinningNumber() {
    return firstWinningNumber;
  }

  public Board getFirstWinningBoard() {
    return firstWinningBoard;
  }

  public int getLastWinningNumber() {
    return lastWinningNumber;
  }

  public Board getLastWinningBoard() {
    return lastWinningBoard;
  }
}
