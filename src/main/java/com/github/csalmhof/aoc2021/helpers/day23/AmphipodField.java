package com.github.csalmhof.aoc2021.helpers.day23;

import java.util.Objects;

public class AmphipodField {

  public static final AmphipodField BORDER = new AmphipodField(true, null);
  public static final AmphipodField EMPTY = new AmphipodField(false, null);

  private Amphipod containedAmphipod;
  private final boolean border;

  private AmphipodField(boolean border, Amphipod containedAmphipod) {
    this.border = border;
    this.containedAmphipod = containedAmphipod;
  }

  public static AmphipodField occupiedWith(Amphipod amphipod) {
    return new AmphipodField(false, amphipod);
  }

  public AmphipodField copy() {
    if(!this.isBorder()) {
      return new AmphipodField(this.border, this.containedAmphipod);
    }
    return this;
  }

  public boolean isBorder() {
    return border;
  }

  public boolean isContainingAmphipod() {
    return getContainedAmphipod() != null;
  }

  public Amphipod getContainedAmphipod() {
    return containedAmphipod;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AmphipodField that = (AmphipodField) o;
    return border == that.border && containedAmphipod == that.containedAmphipod;
  }

  @Override
  public int hashCode() {
    return Objects.hash(containedAmphipod, border);
  }

  @Override
  public String toString() {
    if (border) {
      return "#";
    }
    if (Objects.nonNull(containedAmphipod)) {
      return String.valueOf(containedAmphipod.getShortValue());
    }
    return ".";
  }
}
