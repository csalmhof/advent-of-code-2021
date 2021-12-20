package com.github.csalmhof.aoc2021.helpers.day19;

import java.util.Objects;

public class Point3D {
  final int x;
  final int y;
  final int z;

  private Point3D(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Point3D getWithOrientation(int up, int xDir, int yDir, int zDir) {
    int newX = this.x * xDir;
    int newY = this.y * yDir;
    int newZ = this.z * zDir;

    switch (up) {
      case 0: return new Point3D(newX, newY, newZ);
      case 1: return new Point3D(newX, newZ, newY);
      case 2: return new Point3D(newY, newX, newZ);
      case 3: return new Point3D(newY, newZ, newX);
      case 4: return new Point3D(newZ, newX, newY);
      default: return new Point3D(newZ, newY, newX);
    }
  }

  public long manhattanDistanceTo(Point3D other) {
    return Math.abs(this.x - other.x) + Math.abs(this.y - other.y) + Math.abs(this.z - other.z);
  }

  public Point3D minus(Point3D other) {
    return new Point3D(this.x-other.x, this.y-other.y, this.z-other.z);
  }

  public Point3D add(Point3D other) {
    return new Point3D(this.x+other.x, this.y+other.y, this.z+other.z);
  }

  public static Point3D of(int x, int y, int z) {
    return new Point3D(x, y, z);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Point3D point3D = (Point3D) o;
    return x == point3D.x && y == point3D.y && z == point3D.z;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, z);
  }

  @Override
  public String toString() {
    return "[" + x + "," + y + "," + z + "]";
  }
}
