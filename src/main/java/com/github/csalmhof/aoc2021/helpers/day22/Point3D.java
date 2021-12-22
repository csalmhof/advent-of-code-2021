package com.github.csalmhof.aoc2021.helpers.day22;

import java.util.Objects;

public class Point3D {
  final long x;
  final long y;
  final long z;

  private Point3D(long x, long y, long z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public static Point3D of(long x, long y, long z) {
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
