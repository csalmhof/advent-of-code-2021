package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day4.BingoGame;
import com.github.csalmhof.aoc2021.helpers.day4.Board;

import java.util.ArrayList;
import java.util.List;

public class Puzzle04 extends AbstractPuzzle {

  private final int LINE_INDEX = 0;
  private final int BOARD_SPACING_LINES_CNT = 1;

  @Override
  public int getDay() {
    return 4;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    BingoGame bingoGame = new BingoGame();
    bingoGame.initNumbers(input.get(LINE_INDEX));
    bingoGame.addBoards(getBoardsFromInput(input));
    bingoGame.play();

    return bingoGame.getFirstWinningNumber() * bingoGame.getFirstWinningBoard().calcBoard();
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    BingoGame bingoGame = new BingoGame();
    bingoGame.initNumbers(input.get(LINE_INDEX));
    bingoGame.addBoards(getBoardsFromInput(input));
    bingoGame.play();

    return bingoGame.getLastWinningNumber() * bingoGame.getLastWinningBoard().calcBoard();
  }

  private List<Board> getBoardsFromInput(List<String> input) {
    List<Board> result = new ArrayList<>();
    List<String> manipulatedInput = new ArrayList<>(input.subList(BOARD_SPACING_LINES_CNT, input.size()));

    while (manipulatedInput.size() > Board.DEFAULT_SIZE) {
      Board newBoard = new Board();
      newBoard.initWithLines(manipulatedInput.subList(BOARD_SPACING_LINES_CNT, Board.DEFAULT_SIZE + BOARD_SPACING_LINES_CNT));
      result.add(newBoard);
      manipulatedInput = manipulatedInput.subList(Board.DEFAULT_SIZE + BOARD_SPACING_LINES_CNT, manipulatedInput.size());
    }

    return result;
  }
}
