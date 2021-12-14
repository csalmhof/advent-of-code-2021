package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.util.FileUtils;

import java.util.List;

public abstract class AbstractPuzzle {

  public abstract int getDay();

  public abstract long calculatePart1Result(List<String> input);

  public abstract long calculatePart2Result(List<String> input);

  public long getPart1Result(String filePath) {
    long start = System.currentTimeMillis();
    long result =  calculatePart1Result(getInput(filePath));
    System.out.println("Time: " + (System.currentTimeMillis()-start) + "ms");
    return result;
  }

  public long getPart2Result(String filePath) {
    long start = System.currentTimeMillis();
    long result =  calculatePart2Result(getInput(filePath));
    System.out.println("Time: " + (System.currentTimeMillis()-start) + "ms");
    return result;
  }

  public void printPart1Result(String filePath) {
    System.out.println(getPart1Result(filePath));
  }

  public void printPart2Result(String filePath) {
    System.out.println(getPart2Result(filePath));
  }

  public List<String> getInput(String filePath) {
    return FileUtils.getFileInputAsList(filePath);
  }
}
