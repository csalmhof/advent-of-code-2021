package com.github.csalmhof.aoc2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Puzzle14 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 14;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    String pattern = input.get(0);

    Map<String, String> mapping = input.subList(2, input.size()).stream()
        .map(s -> s.split(" -> "))
        .collect(Collectors.toMap(s -> s[0], s -> s[1]));

    for(int c = 0; c<10; c++) {
      for (int i = 0; i < pattern.length(); i++) {
        if ((i + 1) >= pattern.length()) {
          continue;
        }
        if (mapping.get(pattern.charAt(i) + "" + pattern.charAt(i + 1)) != null) {
          pattern = pattern.substring(0, i + 1) + mapping.get(pattern.charAt(i) + "" + pattern.charAt(i + 1)) + pattern.substring(i + 1);
          i++;
        }
      }
    }

    Map<Character, Integer> occuring = pattern.chars()
        .mapToObj(i -> (char) i)
        .collect(Collectors.groupingBy(Function.identity()))
        .entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));

    Integer max = occuring.values().stream().max(Integer::compare).get();
    Integer min = occuring.values().stream().min(Integer::compare).get();

    return max - min;
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    String pattern = input.get(0);

    Map<String, String> mapping = input.subList(2, input.size()).stream()
        .map(s -> s.split(" -> "))
        .collect(Collectors.toMap(s -> s[0], s -> s[1]));

    Map<String, Long> pairCounter = new HashMap<>();

    for(int i = 0; i < pattern.length()-1; i++) {
      pairCounter.putIfAbsent(pattern.charAt(i) + "" + pattern.charAt(i+1), 0L);
      pairCounter.put(pattern.charAt(i) + "" + pattern.charAt(i+1), pairCounter.get(pattern.charAt(i) + "" + pattern.charAt(i+1)) + 1);
    }

    for(int i = 0; i < 40; i++) {
      Map<String, Long> nextPairCounter = new HashMap<>();

      for(Map.Entry<String, Long> e : pairCounter.entrySet()) {
       if(mapping.containsKey(e.getKey())) {
         String key1 = e.getKey().charAt(0) + mapping.get(e.getKey());
         String key2 = mapping.get(e.getKey()) + e.getKey().charAt(1);

         nextPairCounter.putIfAbsent(key1, 0L);
         nextPairCounter.put(key1, nextPairCounter.get(key1) + e.getValue());
         nextPairCounter.putIfAbsent(key2, 0L);
         nextPairCounter.put(key2, nextPairCounter.get(key2) + e.getValue());
       } else {
         nextPairCounter.putIfAbsent(e.getKey(), 0L);
         nextPairCounter.put(e.getKey(), nextPairCounter.get(e.getKey()) + e.getValue());
       }
      }
      pairCounter = nextPairCounter;
    }

    Map<Character, Long> occuring = pairCounter.entrySet().stream()
        .collect(Collectors.groupingBy(e -> e.getKey().charAt(0)))
        .entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().map(Map.Entry::getValue).reduce(0L, Long::sum)));

    if(occuring.containsKey(pattern.charAt(pattern.length()-1))) {
      occuring.put(pattern.charAt(pattern.length()-1), occuring.get(pattern.charAt(pattern.length()-1)) + 1);
    } else {
      occuring.put(pattern.charAt(pattern.length()-1), 1L);
    }

    Long max = occuring.values().stream().max(Long::compare).get();
    Long min = occuring.values().stream().min(Long::compare).get();

    return max-min;
  }

}

