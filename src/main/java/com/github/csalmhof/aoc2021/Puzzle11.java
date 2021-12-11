package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day11.OctopusMap;

import java.util.List;

public class Puzzle11 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 11;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    OctopusMap oMap = OctopusMap.of(input);

    return oMap.flash(100);
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    OctopusMap oMap = OctopusMap.of(input);

    return oMap.findSyncFlash();
  }
}
