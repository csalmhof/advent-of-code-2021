package com.github.csalmhof.aoc2021;

import com.github.csalmhof.aoc2021.util.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Puzzle22 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 22;
  }

  @Override
  public long calculatePart1Result(List<String> input) {
    List<CuboidCommand> commands = input.stream()
        .map(CuboidCommand::of)
        .filter(c -> c.getCuboid().isInRange(-50, 50, -50, 50, -50, 50))
        .collect(Collectors.toList());

    return calc(commands);
  }


  @Override
  public long calculatePart2Result(List<String> input) {
    List<CuboidCommand> commands = input.stream()
        .map(CuboidCommand::of)
        .collect(Collectors.toList());

    return calc(commands);
  }

  private long calc(List<CuboidCommand> commands) {
    Set<Cuboid> onCuboids = new HashSet<>();
    Set<Cuboid> positiveIntersections = new HashSet<>();
    Set<Cuboid> negativeIntersections = new HashSet<>();

    for (CuboidCommand command : commands) {
      Set<Cuboid> newPositiveIntersections = new HashSet<>();
      Set<Cuboid> newNegativeIntersections = new HashSet<>();

      for (Cuboid cuboid : onCuboids) {
        addIntersectionToSet(newNegativeIntersections, command.getCuboid(), cuboid);
      }

      for (Cuboid cuboid : positiveIntersections) {
        addIntersectionToSet(newNegativeIntersections, command.getCuboid(), cuboid);
      }

      for (Cuboid cuboid : negativeIntersections) {
        addIntersectionToSet(newPositiveIntersections, command.getCuboid(), cuboid);
      }

      if (Instruction.ON.equals(command.getInstruction())) {
        onCuboids.add(command.getCuboid());
      }

      negativeIntersections.addAll(newNegativeIntersections);
      positiveIntersections.addAll(newPositiveIntersections);
    }

    return onCuboids.stream().map(Cuboid::getVolume).reduce(0L, Long::sum)
        + positiveIntersections.stream().map(Cuboid::getVolume).reduce(0L, Long::sum)
        - negativeIntersections.stream().map(Cuboid::getVolume).reduce(0L, Long::sum);
  }

  private void addIntersectionToSet(Set<Cuboid> set, Cuboid first, Cuboid second) {
    Cuboid intersection = first.getIntersectionWith(second);
    if (intersection.getVolume() != 0) {
      set.add(intersection);
    }
  }


  public enum Instruction {
    ON, OFF
  }

  public static class Cuboid {
    private final Pair<Long, Long> x;
    private final Pair<Long, Long> y;
    private final Pair<Long, Long> z;

    public static Cuboid empty() {
      return new Cuboid(null, null, null);
    }

    public boolean isInRange(long minX, long maxX, long minY, long maxY, long minZ, long maxZ) {
      return x.left >= minX
          && x.right <= maxX
          && y.left >= minY
          && y.right <= maxY
          && z.left >= minZ
          && z.right <= maxZ;
    }

    public Cuboid(Pair<Long, Long> x, Pair<Long, Long> y, Pair<Long, Long> z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public long getVolume() {
      if (x != null && y != null && z != null) {
        return (x.right - x.left + 1) * (y.right - y.left + 1) * (z.right - z.left + 1);
      }
      return 0;
    }

    public Cuboid getIntersectionWith(Cuboid other) {
      long minX = Math.max(this.x.left, other.x.left);
      long maxX = Math.min(this.x.right, other.x.right);
      long minY = Math.max(this.y.left, other.y.left);
      long maxY = Math.min(this.y.right, other.y.right);
      long minZ = Math.max(this.z.left, other.z.left);
      long maxZ = Math.min(this.z.right, other.z.right);

      if (minX > maxX || minY > maxY || minZ > maxZ) {
        return empty();
      }

      return new Cuboid(Pair.of(minX, maxX), Pair.of(minY, maxY), Pair.of(minZ, maxZ));
    }
  }

  public static class CuboidCommand {
    private Instruction instruction;
    private final Cuboid cuboid;

    private CuboidCommand(Instruction instruction, Cuboid cuboid) {
      this.instruction = instruction;
      this.cuboid = cuboid;
    }

    public static CuboidCommand of(String s) {
      Instruction i = s.startsWith("on") ? Instruction.ON : Instruction.OFF;

      long xMin = Long.parseLong(s.split("x=")[1].split("\\.\\.")[0]);
      long xMax = Long.parseLong(s.split("x=")[1].split("\\.\\.")[1].split(",y")[0]);
      long yMin = Long.parseLong(s.split("y=")[1].split("\\.\\.")[0]);
      long yMax = Long.parseLong(s.split("y=")[1].split("\\.\\.")[1].split(",z")[0]);
      long zMin = Long.parseLong(s.split("z=")[1].split("\\.\\.")[0]);
      long zMax = Long.parseLong(s.split("z=")[1].split("\\.\\.")[1]);

      return new CuboidCommand(i, new Cuboid(Pair.of(xMin, xMax), Pair.of(yMin, yMax), Pair.of(zMin, zMax)));
    }

    public Instruction getInstruction() {
      return instruction;
    }

    public Cuboid getCuboid() {
      return cuboid;
    }
  }
}

