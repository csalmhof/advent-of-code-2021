package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day8.SevenSegmentKey;

import java.util.*;

public class Puzzle08 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 8;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    return input.stream()
        .map(i -> i.split(" \\| ")[1])
        .flatMap(i -> Arrays.stream(i.split(" ")))
        .map(this::mapLengthDefined)
        .filter(Objects::nonNull)
        .count();
  }

  public Integer mapLengthDefined(String s) {
    switch (s.length()) {
      case 2: return 1;
      case 4: return 4;
      case 3: return 7;
      case 7: return 8;
      default: return null;
    }
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    return input.stream()
        .map(this::decodeInput)
        .reduce(0, Integer::sum);
  }

  private Integer decodeInput(String s) {
    String[] split = s.split(" \\| ");

    Collection<SevenSegmentKey> keyMap = findKeys(Arrays.asList(split[0].split(" ")));

    String[] vals = split[1].split(" ");

    Integer num1 = decode(vals[0], keyMap);
    Integer num2 = decode(vals[1], keyMap);
    Integer num3 = decode(vals[2], keyMap);
    Integer num4 = decode(vals[3], keyMap);

    return num1*1000 + num2*100 + num3 *10 + num4;
  }

  private Integer decode(String val, Collection<SevenSegmentKey> decodingMap) {
    return decodingMap.stream()
        .filter(key -> key.isSame(val))
        .map(SevenSegmentKey::getValue)
        .findFirst().get();
  }

  private Collection<SevenSegmentKey> findKeys(List<String> code) {
    Map<Integer, SevenSegmentKey> resultMap = new HashMap<>();

    resultMap.put(1, new SevenSegmentKey(1, code.stream()
        .filter(s -> s.length() == 2)
        .findFirst().get()));

    resultMap.put(4, new SevenSegmentKey(4, code.stream()
        .filter(s -> s.length() == 4)
        .findFirst().get()));

    resultMap.put(7, new SevenSegmentKey(7, code.stream()
        .filter(s -> s.length() == 3)
        .findFirst().get()));

    resultMap.put(8, new SevenSegmentKey(8, code.stream()
        .filter(s -> s.length() == 7)
        .findFirst().get()));

    resultMap.put(3, new SevenSegmentKey(3, code.stream()
        .filter(s -> s.length() == 5)
        .filter(s -> resultMap.get(1).isContainedIn(s))
        .findFirst().get()));

    resultMap.put(6, new SevenSegmentKey(6, code.stream()
        .filter(s -> s.length() == 6)
        .filter(s -> !resultMap.get(1).isContainedIn(s))
        .findFirst().get()));

    resultMap.put(5, new SevenSegmentKey(5, code.stream()
        .filter(s -> s.length() == 5)
        .filter(s -> resultMap.get(6).contains(s))
        .findFirst().get()));

    resultMap.put(0, new SevenSegmentKey(0, code.stream()
        .filter(s -> s.length() == 6)
        .filter(s -> resultMap.get(1).isContainedIn(s))
        .filter(s -> !resultMap.get(5).isContainedIn(s))
        .findFirst().get()));

    resultMap.put(9, new SevenSegmentKey(9, code.stream()
        .filter(s -> s.length() == 6)
        .filter(s -> !resultMap.get(6).isSame(s))
        .filter(s -> !resultMap.get(0).isSame(s))
        .findFirst().get()));

    resultMap.put(2, new SevenSegmentKey(2, code.stream()
        .filter(s -> s.length() == 5)
        .filter(s -> !resultMap.get(3).isSame(s))
        .filter(s -> !resultMap.get(5).isSame(s))
        .findFirst().get()));

    return resultMap.values();
  }
}
