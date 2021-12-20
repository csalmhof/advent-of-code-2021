package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day20.Image;

import java.util.List;

public class Puzzle20 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 20;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    Image img = Image.of(input);
    img.enhance(2);

    return img.getNumOfPoints();
  }


  @Override
  public long calculatePart2Result(List<String> input) {
    Image img = Image.of(input);
    img.enhance(50);

    return img.getNumOfPoints();
  }

}

