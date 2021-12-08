package com.github.csalmhof.aoc2021;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    BidiMap<Integer, String> decodingMap = findKeys(Arrays.asList(split[0].split(" ")));

    String[] vals = split[1].split(" ");

    Integer num1 = decode(vals[0], decodingMap);
    Integer num2 = decode(vals[1], decodingMap);
    Integer num3 = decode(vals[2], decodingMap);
    Integer num4 = decode(vals[3], decodingMap);

    return num1*1000 + num2*100 + num3 *10 + num4;
  }

  private Integer decode(String val, BidiMap<Integer, String> decodingMap) {
    return decodingMap.values().stream()
        .filter(v -> v.length() == val.length())
        .filter(v -> v.chars().allMatch(i -> val.contains("" + ((char) i))))
        .map(decodingMap::getKey)
        .findFirst().get();
  }

  private BidiMap<Integer, String> findKeys(List<String> code) {
    BidiMap<Integer, String> result = new DualHashBidiMap<>();

    result.put(1, code.stream()
        .filter(s -> s.length() == 2)
        .findFirst().get());

    result.put(4, code.stream()
        .filter(s -> s.length() == 4)
        .findFirst().get());

    result.put(7, code.stream()
        .filter(s -> s.length() == 3)
        .findFirst().get());

    result.put(8, code.stream()
        .filter(s -> s.length() == 7)
        .findFirst().get());

    result.put(3, code.stream()
        .filter(s -> s.length() == 5)
        .filter(s -> result.get(1).chars().allMatch(p -> s.contains(((char) p) + "")))
        .findFirst().get());

    result.put(6, code.stream()
        .filter(s -> s.length() == 6)
        .filter(s -> result.get(1).chars().anyMatch(p -> !s.contains(((char) p) + "")))
        .findFirst().get());

    result.put(5, code.stream()
        .filter(s -> s.length() == 5)
        .filter(s -> s.chars().allMatch(p -> result.get(6).contains(((char) p) + "")))
        .findFirst().get());

    result.put(0, code.stream()
        .filter(s -> s.length() == 6)
        .filter(s -> result.get(1).chars().allMatch(p -> s.contains(((char) p) + "")))
        .filter(s -> !result.get(5).chars().allMatch(p -> s.contains(((char) p) + "")))
        .findFirst().get());

    result.put(9, code.stream()
        .filter(s -> s.length() == 6)
        .filter(s -> !result.get(6).chars().allMatch(p -> s.contains(((char) p) + "")))
        .filter(s -> !result.get(0).chars().allMatch(p -> s.contains(((char) p) + "")))
        .findFirst().get());

    result.put(2, code.stream()
        .filter(s -> s.length() == 5)
        .filter(s -> !result.get(3).chars().allMatch(p -> s.contains(((char) p) + "")))
        .filter(s -> !result.get(5).chars().allMatch(p -> s.contains(((char) p) + "")))
        .findFirst().get());

    return result;
  }
}
