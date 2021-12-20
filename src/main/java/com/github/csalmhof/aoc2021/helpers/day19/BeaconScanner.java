package com.github.csalmhof.aoc2021.helpers.day19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BeaconScanner {
  private List<Point3D> points;
  private Point3D position;
  private boolean isAligned = false;
  private String name;

  private BeaconScanner(String name, List<Point3D> points) {
    this.points = points;
    this.name = name;
  }

  public static BeaconScanner of(String name, List<String> points) {
    return new BeaconScanner(name, points.stream()
        .map(line -> line.split(","))
        .map(split -> Point3D.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])))
        .collect(Collectors.toList()));
  }

  public List<BeaconScanner> getAllPossibleOrientations() {
    if (this.isAligned) {
      return List.of(this);
    }
    List<BeaconScanner> result = new ArrayList<>();

    List<Integer> directions = Arrays.asList(1, -1);
    /* possible directions
       x,  y,  z
       x,  y, -z
       x, -y,  z
       x, -y, -z
      -x,  y,  z
      -x,  y, -z
      -x, -y,  z
      -x, -y, -z
       y,  z,  x
       y,  z, -x
       y, -z,  x
       y, -z, -x
      -y,  z,  x
      -y,  z, -x
      -y, -z,  x
      -y, -z, -x
       z,  x,  y
       z,  x, -y
       z, -x,  y
       z, -x, -y
      -z,  x,  y
      -z,  x, -y
      -z, -x,  y
      -z, -x, -y
     */
    for (int up = 0; up < 6; up++) {
      for (int dirX : directions) {
        for (int dirY : directions) {
          for (int dirZ : directions) {

            List<Point3D> pointsOfOrientation = new ArrayList<>();
            for (Point3D originalPoint : points) {
              pointsOfOrientation.add(originalPoint.getWithOrientation(up, dirX, dirY, dirZ));
            }
            result.add(new BeaconScanner(this.name, pointsOfOrientation));
          }
        }
      }
    }
    return result;
  }

  public boolean alignIfPossible(BeaconScanner alignedScanner) {
    for (BeaconScanner orientatedScanner : this.getAllPossibleOrientations()) {
      int asdf = 0;
      for (Point3D startPoint : alignedScanner.getPoints()) {
        for (Point3D targetPoint : orientatedScanner.getPoints()) {
          int matches = 0;
          Point3D diff = startPoint.minus(targetPoint);
          for (Point3D consideredPoint : orientatedScanner.getPoints()) {
            if (alignedScanner.getPoints().contains(consideredPoint.add(diff))) {
              matches++;
            }
          }

          if (matches >= 12) {
            this.position = diff;
            List<Point3D> orientatedAndAlignedPoints = new ArrayList<>();
            for (Point3D orientatedPoint : orientatedScanner.getPoints()) {
              orientatedAndAlignedPoints.add(orientatedPoint.add(diff));
            }
            this.points = orientatedAndAlignedPoints;
            this.setAligned();
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean isAligned() {
    return isAligned;
  }

  public void setAligned() {
    isAligned = true;
  }

  public List<Point3D> getPoints() {
    return points;
  }

  public List<Point3D> getAlignedPoints() {
    return getPoints().stream().map(p -> p.add(position)).collect(Collectors.toList());
  }

  public void setPoints(List<Point3D> points) {
    this.points = points;
  }

  public Point3D getPosition() {
    return position;
  }

  public void setPosition(Point3D position) {
    this.position = position;
  }

  public String getName() {
    return name;
  }
}
