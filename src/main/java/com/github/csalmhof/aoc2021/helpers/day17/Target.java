package com.github.csalmhof.aoc2021.helpers.day17;

import java.awt.*;

public class Target {
  public final int minX;
  public final int maxX;
  public final int minY;
  public final int maxY;

  public Target(int minX, int maxX, int minY, int maxY) {
    this.minX = minX;
    this.maxX = maxX;
    this.minY = minY;
    this.maxY = maxY;
  }

  public boolean contains(Point point) {
    return point.x >= minX
        && point.x <= maxX
        && point.y >= minY
        && point.y <= maxY;
  }

  public boolean isHitBy(ProbeShot shot) {
    for (int i = Math.max(0, 1+shot.initialSpeed.y*2); shot.calcPosition(i).x <= maxX && shot.calcPosition(i).y >= minY; i++) {
      if (this.contains(shot.calcPosition(i))) {
        return true;
      }
    }
    return false;
  }
}
