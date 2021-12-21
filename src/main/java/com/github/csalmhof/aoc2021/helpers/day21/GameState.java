package com.github.csalmhof.aoc2021.helpers.day21;

import java.util.Objects;

public class GameState {
  private long player1Position;
  private long player1Score;
  private long player2Position;
  private long player2Score;

  public GameState(long player1Position, long player1Score, long player2Position, long player2Score) {
    this.player1Position = player1Position;
    this.player1Score = player1Score;
    this.player2Position = player2Position;
    this.player2Score = player2Score;
  }

  public long getPlayer1Position() {
    return player1Position;
  }

  public void setPlayer1Position(long player1Position) {
    this.player1Position = player1Position;
  }

  public long getPlayer1Score() {
    return player1Score;
  }

  public void setPlayer1Score(long player1Score) {
    this.player1Score = player1Score;
  }

  public long getPlayer2Position() {
    return player2Position;
  }

  public void setPlayer2Position(long player2Position) {
    this.player2Position = player2Position;
  }

  public long getPlayer2Score() {
    return player2Score;
  }

  public void setPlayer2Score(long player2Score) {
    this.player2Score = player2Score;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GameState gameState = (GameState) o;
    return player1Position == gameState.player1Position && player1Score == gameState.player1Score && player2Position == gameState.player2Position && player2Score == gameState.player2Score;
  }

  @Override
  public int hashCode() {
    return Objects.hash(player1Position, player1Score, player2Position, player2Score);
  }
}
