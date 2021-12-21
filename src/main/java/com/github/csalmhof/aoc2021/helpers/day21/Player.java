package com.github.csalmhof.aoc2021.helpers.day21;

public class Player {

  private static final long BOARDLEN = 10;

  private long position;
  private long score;


  public Player(long position) {
    this.position = position;
    this.score = 0;
  }

  public long move(long value) {
    position = (position + value-1) % (BOARDLEN) +1;
    score += position;
    return score;
  }

  public long getScore() {
    return score;
  }
}
