package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.helpers.day17.ProbeShot;
import com.github.csalmhof.aoc2021.helpers.day17.Target;

import java.util.ArrayList;
import java.util.List;

public class Puzzle17 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 17;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    Target target = inputToTarget(input);

    return calcHittingShots(target).stream()
        .map(ProbeShot::getHighestY)
        .max(Integer::compare)
        .get();
  }

  @Override
  public long calculatePart2Result(List<String> input) {
    Target target = inputToTarget(input);

    return calcHittingShots(target).size();
  }

  private List<ProbeShot> calcHittingShots(Target target) {
    List<ProbeShot> hittingShots = new ArrayList<>();
    for (int initialXspeed = ProbeShot.getMinXSpeedToReachValue(target.minX); initialXspeed <= target.maxX; initialXspeed++) {
      for (int initialYspeed = target.minY; initialYspeed < Math.abs(target.minY); initialYspeed++) {
        ProbeShot shot = new ProbeShot(initialXspeed, initialYspeed);
        if (target.isHitBy(shot)) {
          hittingShots.add(shot);
        }
      }
    }
    return hittingShots;
  }

  private Target inputToTarget(List<String> input) {
    String[] split1 = input.get(0).substring(15).split(", ");
    int minX = Integer.parseInt(split1[0].split("\\.\\.")[0]);
    int maxX = Integer.parseInt(split1[0].split("\\.\\.")[1]);
    int minY = Integer.parseInt(split1[1].substring(2).split("\\.\\.")[0]);
    int maxY = Integer.parseInt(split1[1].substring(2).split("\\.\\.")[1]);

    return new Target(minX, maxX, minY, maxY);
  }

}

