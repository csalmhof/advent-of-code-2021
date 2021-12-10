package com.github.csalmhof.aoc2021;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle10 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 10;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    return input.stream().map(this::isCorrupted).reduce(0, Integer::sum);
  }
  @Override
  public long calculatePart2Result(List<String> input) {
    List<List<Character>> closingList = input.stream()
        .map(this::closingLine)
        .collect(Collectors.toList());

    List<Long> scores = closingList.stream()
        .filter(i -> !i.isEmpty())
        .map(this::scoreClosingList)
        .sorted(Comparator.comparing(Function.identity()))
        .collect(Collectors.toList());

    int pos = (scores.size() +1)/2;

    return scores.get(pos-1);
  }

  private long scoreClosingList(List<Character> list) {
    long score = 0;
    for(Character c : list) {
      score = score * 5 + pointsPart2(c);
    }
    return score;
  }

  private List<Character> closingLine(String s) {
    List<Character> open = new LinkedList<>();
    for(int i = 0; i <s.length(); i++) {
      if(open.isEmpty()) {
        open.add(s.charAt(i));
        continue;
      }

      if(isOpening(s.charAt(i))) {
        open.add(s.charAt(i));
        continue;
      }

      if(closingOf(open.get(open.size()-1)) == s.charAt(i)) {
        open.remove(open.size()-1);
        continue;
      }

      return Collections.emptyList();
    }

    return IntStream.range(0, open.size())
        .mapToObj(i -> open.get(open.size()-i-1))
        .map(this::closingOf)
        .collect(Collectors.toList());
  }

  private int isCorrupted(String s) {
    List<Character> open = new LinkedList<>();
    int pos = 0;
    for(int i = 0; i <s.length(); i++) {
      if(open.isEmpty()) {
        open.add(s.charAt(i));
        continue;
      }

      if(isOpening(s.charAt(i))) {
        open.add(s.charAt(i));
        continue;
      }

      if(closingOf(open.get(open.size()-1)) == s.charAt(i)) {
        open.remove(open.size()-1);
        continue;
      }

      return pointsPart1(s.charAt(i));
    }

    return 0;
  }

  private boolean isOpening(char c) {
    return c == '('
        || c ==  '['
        || c ==  '{'
        || c ==  '<';
  }

  private char closingOf(char c) {
    if(c == '(') {
      return ')';
    }

    if(c == '[') {
      return ']';
    }

    if(c == '{') {
      return '}';
    }

    if(c == '<') {
      return '>';
    }

    throw  new IllegalArgumentException();
  }

  private int pointsPart1(char c) {
    if(c ==  ')') {
      return 3;
    }

    if(c ==  ']') {
      return 57;
    }

    if(c == '}') {
      return 1197;
    }

    if(c == '>') {
      return 25137;
    }

    return 0;
  }

  private int pointsPart2(char c) {
    if(c ==  ')') {
      return 1;
    }

    if(c ==  ']') {
      return 2;
    }

    if(c == '}') {
      return 3;
    }

    if(c == '>') {
      return 4;
    }

    return 0;
  }
}
