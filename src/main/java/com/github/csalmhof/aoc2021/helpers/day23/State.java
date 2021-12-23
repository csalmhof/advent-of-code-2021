package com.github.csalmhof.aoc2021.helpers.day23;

import com.github.csalmhof.aoc2021.util.Pair;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class State {

  private static Long border = Long.MAX_VALUE;

  private Map<Point, AmphipodField> fields = new HashMap<>();
  private long cost = 0;
  private int hor = 0;
  private int ver = 0;
  List<State> goneWay = new ArrayList<>();

  private static final Set<Point> UNALLOWED_HALLWAY_POINTS = new HashSet<>();

  static {
    UNALLOWED_HALLWAY_POINTS.add(new Point(3, 1));
    UNALLOWED_HALLWAY_POINTS.add(new Point(5, 1));
    UNALLOWED_HALLWAY_POINTS.add(new Point(7, 1));
    UNALLOWED_HALLWAY_POINTS.add(new Point(9, 1));
  }

  private State() {
  }

  private State copy() {
    State newState = new State();
    newState.cost = this.cost;
    newState.hor = this.hor;
    newState.ver = this.ver;

    for (Point p : this.fields.keySet()) {
      newState.fields.put(p, this.fields.get(p));
    }

    return newState;
  }

  public static State fromInput(List<String> input) {
    State result = new State();

    Map<Point, AmphipodField> fieldsFromInput = IntStream.range(0, input.size())
        .boxed()
        .flatMap(y -> IntStream.range(0, input.get(y).length())
            .mapToObj(x -> {
              Point point = new Point(x, y);
              if (input.get(y).charAt(x) == '#') {
                return Pair.of(point, AmphipodField.BORDER);
              }
              if (input.get(y).charAt(x) == '.') {
                return Pair.of(point, AmphipodField.EMPTY);
              }
              if (Amphipod.fromString(input.get(y).charAt(x)) != null) {
                return Pair.of(point, AmphipodField.occupiedWith(Amphipod.fromString(input.get(y).charAt(x))));
              }
              return null;
            })
            .filter(Objects::nonNull))
        .collect(Collectors.toMap(pair -> pair.left, pair -> pair.right));

    result.fields = fieldsFromInput;
    result.hor = result.fields.keySet().stream().map(p -> p.x).max(Integer::compare).get();
    result.ver = result.fields.keySet().stream().map(p -> p.y).max(Integer::compare).get();
    return result;
  }

  public long getCost() {
    return cost;
  }

  public boolean isWinningState() {
    int maxYContaining = fields.keySet().stream().map(p -> p.y).max(Integer::compare).get();

    for (int x = 3; x < 10; x += 2) {
      for (int y = 2; y < maxYContaining; y++) {
        switch (x) {
          case 3:
            if (!Amphipod.AMBER.equals(fields.get(new Point(x, y)).getContainedAmphipod())) {
              return false;
            }
            break;
          case 5:
            if (!Amphipod.BRONZE.equals(fields.get(new Point(x, y)).getContainedAmphipod())) {
              return false;
            }
            break;
          case 7:
            if (!Amphipod.COPPER.equals(fields.get(new Point(x, y)).getContainedAmphipod())) {
              return false;
            }
            break;
          case 9:
            if (!Amphipod.DESERT.equals(fields.get(new Point(x, y)).getContainedAmphipod())) {
              return false;
            }
            break;
          default:
            throw new IllegalStateException("Something went wrong in examining winning");
        }
      }
    }

    return true;
  }

  private List<Point> getPointsWithAmphipods() {
    return fields.entrySet().stream()
        .filter(e -> e.getValue().isContainingAmphipod())
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  private AmphipodField getFieldAtPosition(int x, int y) {
    return fields.get(new Point(x, y));
  }

  public long getMinimumSolutionCost(long border) {
    State.border = border;
    return getMinimumSolutionCost(new HashMap<>());
  }

  private long getMinimumSolutionCost(Map<State, Long> stateCostMap) {
    if (this.cost >= border || (stateCostMap.containsKey(this) && stateCostMap.get(this) <= this.cost)) {
      return border;
    }
    if (this.isWinningState()) {
      System.out.println("================================");
      System.out.println("=========== SOLUTION ===========");
      System.out.println("================================");
      for(State state : goneWay) {
        System.out.println(state);
      }
      System.out.println(this);
      System.out.println();
      System.out.println();
      return this.cost;
    }

    stateCostMap.put(this, this.cost);

    List<State> nextPossibleStates = getAllNextPossibleStates();

    for (State nextState : nextPossibleStates) {
      border = Math.min(border, nextState.getMinimumSolutionCost(stateCostMap));
    }

    return border;
  }

  private List<State> getAllNextPossibleStates() {
    List<Point> moveableAmphipods = getMoveableAmphipods();

    List<State> nextStates = new ArrayList<>();

    for (Point p : moveableAmphipods) {
      nextStates.addAll(getNextPossibleStatesWithMovedPoint(p));
    }

    return nextStates;
  }

  private Set<State> getNextPossibleStatesWithMovedPoint(Point p) {
    List<Pair<Point, Integer>> targetsWithMoves = getTargetsWithMoves(p, 0, new HashSet<>()).stream()
        .filter(e -> !UNALLOWED_HALLWAY_POINTS.contains(e.left))
        .filter(e -> isAllowedTarget(p, e.left))
        .filter(e -> isPossibleNonHallwayTarget(p, e.left))
        .filter(e -> magic(p, e.left))
        .filter(e -> magic2(e.left))
        .sorted((i1,i2) -> -Integer.compare(i1.getRight(), i2.getRight()))
        .collect(Collectors.toList());

    return targetsWithMoves.stream()
        .map(next -> {
          State nextState = this.copy();
          AmphipodField current = nextState.fields.get(p);
          nextState.fields.put(p, AmphipodField.EMPTY);
          nextState.fields.put(next.left, current);
          nextState.cost += current.getContainedAmphipod().getStepCost() * next.right;
          nextState.goneWay.addAll(this.goneWay);
          nextState.goneWay.add(this);
          return nextState;
        })
        .collect(Collectors.toSet());
  }

  private boolean magic2(Point target) {
    if(!isHallwayPoint(target)) {
      return isLowestEmptySpace(target);
    }
    return true;
  }

  private boolean isLowestEmptySpace(Point target) {
    return target.y+1 == ver || this.fields.get(new Point(target.x, target.y+1)).isContainingAmphipod();
  }

  private boolean magic(Point start, Point target) {
    if(isHallwayPoint(start)) {
      return !isHallwayPoint(target);
    } else {
      return isHallwayPoint(target);
    }
  }

  private boolean isPossibleNonHallwayTarget(Point start, Point target) {
    if(isHallwayPoint(target)) {
      return true;
    }

    Amphipod movedAmphipod = this.fields.get(start).getContainedAmphipod();
    if(target.y > 1) {
      for(int y = 2; y < ver; y++) {
        if(this.fields.get(new Point(target.x, y)).isContainingAmphipod() && !this.fields.get(new Point(target.x, y)).getContainedAmphipod().equals(movedAmphipod)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean isAllowedTarget(Point start, Point target) {
    Amphipod movedAmphipod = this.fields.get(start).getContainedAmphipod();
    if (isHallwayPoint(start)) {
      if (Amphipod.AMBER.equals(movedAmphipod)) {
        return target.x == 3;
      }
      if (Amphipod.BRONZE.equals(movedAmphipod)) {
        return target.x == 5;
      }
      if (Amphipod.COPPER.equals(movedAmphipod)) {
        return target.x == 7;
      } else {
        return target.x == 9;
      }
    } else {
      if (isHallwayPoint(target)) {
        return true;
      } else {
        if (Amphipod.AMBER.equals(movedAmphipod)) {
          return target.x == 3;
        }
        if (Amphipod.BRONZE.equals(movedAmphipod)) {
          return target.x == 5;
        }
        if (Amphipod.COPPER.equals(movedAmphipod)) {
          return target.x == 7;
        } else {
          return target.x == 9;
        }
      }
    }
  }

  private boolean isHallwayPoint(Point p) {
    return p.y == 1;
  }

  private List<Pair<Point, Integer>> getTargetsWithMoves(Point p, int movesToP, Set<Point> alreadyConsidered) {
    alreadyConsidered.add(p);

    List<Pair<Point, Integer>> result = new ArrayList<>();
    for (int x = -1; x < 2; x++) {
      for (int y = -1; y < 2; y++) {
        if(Math.abs(x) == Math.abs(y)) {
          continue;
        }
        Point pToConsider = new Point(p.x + x, p.y + y);
        if (!alreadyConsidered.contains(pToConsider)
            && !fields.get(pToConsider).isBorder()
            && !fields.get(pToConsider).isContainingAmphipod()) {
          result.add(Pair.of(pToConsider, movesToP + 1));
          result.addAll(getTargetsWithMoves(pToConsider, movesToP + 1, alreadyConsidered));
        }
      }
    }

    return result;
  }

  private List<Point> getMoveableAmphipods() {
    return getPointsWithAmphipods().stream()
        .filter(p -> {
          for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
              if(Math.abs(x) == Math.abs(y)) {
                continue;
              }
              if (!fields.get(new Point(p.x + x, p.y + y)).isBorder()
                  && !fields.get(new Point(p.x + x, p.y + y)).isContainingAmphipod()) {
                return true;
              }
            }
          }
          return false;
        })
        .sorted(Comparator.comparingLong(i -> this.fields.get(i).getContainedAmphipod().getStepCost()))
        .collect(Collectors.toList());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    State state = (State) o;

    if (state.fields.size() != this.fields.size()) {
      return false;
    }

    if (!this.getPointsWithAmphipods().containsAll(state.getPointsWithAmphipods())) {
      return false;
    }

    boolean eq = true;

    for (Point p : this.getPointsWithAmphipods()) {
      if (!state.fields.get(p).getContainedAmphipod().equals(this.fields.get(p).getContainedAmphipod())) {
        return false;
      }
    }

    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(fields);
  }

  @Override
  public String toString() {
    if (fields.isEmpty()) {
      return "empty State";
    }

    return IntStream.range(0, ver + 1)
        .mapToObj(y -> IntStream.range(0, hor + 1)
            .mapToObj(x -> getFieldAtPosition(x, y))
            .map(field -> {
              if (field == null) {
                return " ";
              }
              return field.toString();
            })
            .collect(Collectors.joining()))
        .collect(Collectors.joining("\n")) + "\nCost: " + cost + "\n";
  }
}
