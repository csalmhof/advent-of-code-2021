package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day16.Packet;

import java.util.List;
import java.util.stream.Collectors;

public class Puzzle16 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 16;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    return Packet.of(inputToBinaryString(input)).calcVersSum();
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    return Packet.of(inputToBinaryString(input)).calcValue();
  }

  private String inputToBinaryString(List<String> input) {
    return input.get(0).chars()
        .mapToObj(c -> Integer.parseInt((char) c + "", 16))
        .map(i -> leftPad(Integer.toBinaryString(i), '0', 4))
        .collect(Collectors.joining());
  }

  private static String leftPad(String s, char fillchar, int len) {
    StringBuilder result = new StringBuilder(s);
    while (result.length() < len) {
      result.insert(0, fillchar);
    }

    return result.toString();
  }
}

