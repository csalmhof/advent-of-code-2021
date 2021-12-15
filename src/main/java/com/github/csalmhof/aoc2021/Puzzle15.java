package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day15.RiskMap;

import java.util.List;

public class Puzzle15 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 15;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    RiskMap rMap = RiskMap.of(input);
    return rMap.getLowestPathLen();
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    RiskMap rMap = RiskMap.of(input, 5);
    return rMap.getLowestPathLen();
  }

}

