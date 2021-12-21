package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day21.DeterministicDice;
import com.github.csalmhof.aoc2021.helpers.day21.Dice;
import com.github.csalmhof.aoc2021.helpers.day21.GameState;
import com.github.csalmhof.aoc2021.helpers.day21.Player;
import com.github.csalmhof.aoc2021.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle21 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 21;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    Player p1 = new Player(Long.parseLong(input.get(0).substring(28)));
    Player p2 = new Player(Long.parseLong(input.get(1).substring(28)));

    Player active = p1;
    Player inactive = p2;

    Dice dice = new DeterministicDice();

    while(inactive.getScore() < 1000L) {
      active.move(dice.roll(3));
      Player temp = active;
      active = inactive;
      inactive = temp;
    }

    return active.getScore()*dice.getRolls();
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    long player1Position = Long.parseLong(input.get(0).substring(28));
    long player2Position = Long.parseLong(input.get(1).substring(28));

    GameState initialGameState = new GameState(player1Position, 0, player2Position, 0);

    Pair<Long, Long> wins = countWins(initialGameState);

    return Math.max(wins.getLeft(), wins.getRight());
  }

  private Pair<Long, Long> countWins(GameState initialGameState) {
    return countWins(initialGameState, new HashMap<>());
  }

  private Pair<Long, Long> countWins(GameState gameState, Map<GameState, Pair<Long, Long>> foundSolutions) {

    if (gameState.getPlayer1Score() >= 21) {
      return Pair.of(1L, 0L);
    }

    if (gameState.getPlayer2Score() >= 21) {
      return Pair.of(0L, 1L);
    }

    if (foundSolutions.containsKey(gameState)) {
      return foundSolutions.get(gameState);
    }

    Pair<Long, Long> result = Pair.of(0L, 0L);
    for (int i1 = 1; i1 <= 3; i1++) {
      for (int i2 = 1; i2 <= 3; i2++) {
        for (int i3 = 1; i3 <= 3; i3++) {
          long nextPosition = (gameState.getPlayer1Position() + (i1 + i2 + i3) - 1) % (10) + 1;
          long nextScore = (gameState.getPlayer1Score()) + nextPosition;

          GameState inverse = new GameState(gameState.getPlayer2Position(), gameState.getPlayer2Score(), nextPosition, nextScore);
          Pair<Long, Long> inverseResult = countWins(inverse, foundSolutions);

          result = Pair.of(result.getLeft() + inverseResult.getRight(), result.getRight() + inverseResult.getLeft());
        }
      }
    }
    foundSolutions.put(gameState, result);
    return result;
  }



}

