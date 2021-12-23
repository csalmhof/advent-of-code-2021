package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day23.State;

import java.util.List;

public class Puzzle23 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 23;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    State state = State.fromInput(input);

    return state.getMinimumSolutionCost(15000);
  }


  @Override
  public long calculatePart2Result(List<String> input) {
    State state = State.fromInput(input);

    return state.getMinimumSolutionCost(45000);
  }

}

