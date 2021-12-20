package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day19.BeaconScanner;
import com.github.csalmhof.aoc2021.helpers.day19.Point3D;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle19 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 19;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    List<BeaconScanner> scanners = parseInput(input);

    alignScanners(scanners);

    return scanners.stream()
        .flatMap(s -> s.getPoints().stream())
        .collect(Collectors.toSet())
        .size();
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    List<BeaconScanner> scanners = parseInput(input);

    alignScanners(scanners);

    long bestManhattan = 0;
    for(int i = 0; i < scanners.size(); i++) {
      for(int j = i+1; j < scanners.size(); j++) {
        bestManhattan = Math.max(bestManhattan, scanners.get(i).getPosition().manhattanDistanceTo(scanners.get(j).getPosition()));
      }
    }

    return bestManhattan;
  }

  private void alignScanners(List<BeaconScanner> scanners) {
    scanners.get(0).setAligned();
    scanners.get(0).setPosition(Point3D.of(0, 0, 0));

    while (!getUnaligned(scanners).isEmpty()) {

      for (BeaconScanner alignedScanner : getAligned(scanners)) {
        for (BeaconScanner unalignedScanner : getUnaligned(scanners)) {
          if(unalignedScanner.alignIfPossible(alignedScanner)) {
            System.out.println("Aligned: " + alignedScanner.getName() + " -> " + unalignedScanner.getName());
          }
        }
      }
    }

    int i = 0;
  }

  private List<BeaconScanner> getUnaligned(List<BeaconScanner> scanners) {
    return scanners.stream().filter(s -> !s.isAligned()).collect(Collectors.toList());
  }

  private List<BeaconScanner> getAligned(List<BeaconScanner> scanners) {
    return scanners.stream().filter(BeaconScanner::isAligned).collect(Collectors.toList());
  }

  private List<BeaconScanner> parseInput(List<String> input) {
    List<BeaconScanner> scanners;
    scanners = new ArrayList<>();
    Iterator<String> it = input.iterator();

    List<String> points = new ArrayList<>();
    String name = "";
    while (it.hasNext()) {
      String line = it.next();

      if (line.startsWith("---")) {
        name = line.substring(4, line.length()-4);
        continue;
      }
      if (line.isEmpty()) {
        scanners.add(BeaconScanner.of(name, points));
        points = new ArrayList<>();
      } else {
        points.add(line);
      }
    }
    scanners.add(BeaconScanner.of(name, points));

    return scanners;
  }
}

