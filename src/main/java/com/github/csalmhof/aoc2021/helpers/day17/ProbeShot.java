package com.github.csalmhof.aoc2021.helpers.day17;

import java.awt.*;

public class ProbeShot {
  public final Point initialSpeed;

  public ProbeShot(int xSpeed, int ySpeed) {
    this.initialSpeed = new Point(xSpeed, ySpeed);
  }

  public int getHighestY() {
    return calcYposition(initialSpeed.y);
  }

  public Point calcPosition(int time) {
    return new Point(calcXposition(time), calcYposition(time));
  }

  private int calcXposition(int time) {
    if (initialSpeed.x < time) {
      return calcXposition(initialSpeed.x);
    }

    return time * initialSpeed.x - Math.max(0, gaussianSum(time -1));
  }

  private int calcYposition(int time) {
    return time * initialSpeed.y - Math.max(0, gaussianSum(time -1));
  }

  private int gaussianSum(int value) {
    return value * (value + 1) / 2;
  }

  public static int getMinXSpeedToReachValue(int targetX) {
    return (int) Math.ceil(-1 + Math.sqrt(8*targetX)/2);
  }
}
