package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day3.Selector;
import com.github.csalmhof.aoc2021.util.ListUtils;
import com.github.csalmhof.aoc2021.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Puzzle03 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 3;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    List<String> transposedInput = ListUtils.transpose(input);

    String binaryGamma = transposedInput.stream()
        .map(col -> StringUtils.findMaxOccuringCharInStringWithPreferringValue('1', col))
        .map(String::valueOf)
        .collect(Collectors.joining());

    String binaryEpsilon = StringUtils.invertBinaryString(binaryGamma);

    return StringUtils.binaryStringToInt(binaryGamma) * StringUtils.binaryStringToInt(binaryEpsilon);
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    String oxygenBinary = findRecursiveByMostMatchingValue('1', input);
    String co2scrubberRating = findRecursiveByLeastMatchingValue('0', input);

    return StringUtils.binaryStringToInt(oxygenBinary) * StringUtils.binaryStringToInt(co2scrubberRating);
  }

  private String findRecursiveByMostMatchingValue(char preferredValue, List<String> input) {
    return findRecursive(Selector.MOST, preferredValue, 0, input);
  }

  private String findRecursiveByLeastMatchingValue(char preferredValue, List<String> input) {
    return findRecursive(Selector.LEAST, preferredValue, 0, input);
  }

  private String findRecursive(Selector selector, char preferredValue, int position, List<String> input) {
    if (input.size() == 1) {
      return input.get(0);
    }

    String columnToConsider = ListUtils.transpose(input).get(position);
    char charToConsider;

    if (selector.equals(Selector.MOST)) {
      charToConsider = StringUtils.findMaxOccuringCharInStringWithPreferringValue(preferredValue, columnToConsider);
    } else {
      charToConsider = StringUtils.findMinOccuringCharInStringWithPreferringValue(preferredValue, columnToConsider);
    }

    List<String> remaining = input.stream()
        .filter(s -> s.charAt(position) == charToConsider)
        .collect(Collectors.toList());

    return findRecursive(selector, preferredValue, position + 1, remaining);
  }
}
