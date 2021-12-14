package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day13.OrigamiPaper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Puzzle13 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 13;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    int index = IntStream.range(0, input.size())
        .filter(i -> input.get(i).equals(""))
        .findFirst().getAsInt();

    OrigamiPaper origamiPaper = OrigamiPaper.of(input.subList(0, index));

    String firstDirection = input.get(index+1).substring(11).split("=")[0];
    int firstValue = Integer.parseInt(input.get(index+1).substring(11).split("=")[1]);

    origamiPaper.fold(firstDirection, firstValue);

    return origamiPaper.getPoints().size();
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    //no Number-Part 2
    return 0;
  }

  public List<String> getPart2Output(String filePath) {
    List<String> input = getInput(filePath);

    int index = IntStream.range(0, input.size())
        .filter(i -> input.get(i).equals(""))
        .findFirst().getAsInt();

    OrigamiPaper origamiPaper = OrigamiPaper.of(input.subList(0, index));

    for(String cmd : input.subList(index+1, input.size())) {
      String direction = cmd.substring(11).split("=")[0];
      int value = Integer.parseInt(cmd.substring(11).split("=")[1]);

      origamiPaper.fold(direction, value);
    }

    return Stream.of(origamiPaper.toString().split("\n")).collect(Collectors.toList());
  }


}
