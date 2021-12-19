package com.github.csalmhof.aoc2021.helpers.day18;

public class SnailfishNumber {

  private enum AddAction {
    NONE,
    EXPLODE,
    SPLIT
  }

  private SnailfishNumber parent;

  private Long value;
  private SnailfishNumber left;
  private SnailfishNumber right;

  private SnailfishNumber(SnailfishNumber parent) {
    this.parent = parent;
  }

  private SnailfishNumber(long value, SnailfishNumber parent) {
    this.value = value;
    this.parent = parent;
  }

  public static SnailfishNumber parse(String s) {
    return parse(null, s);
  }

  private static SnailfishNumber parse(SnailfishNumber parent, String s) {
    SnailfishNumber num = new SnailfishNumber(parent);

    if (s.length() == 1) {
      num.value = Long.parseLong(s);
      return num;
    }

    String sVal = s.substring(1, s.length() - 1);

    if (sVal.charAt(0) == '[') {
      int closingPos = findClosingPos(sVal);
      num.left = parse(num, sVal.substring(0, closingPos + 1));
      num.right = parse(num, sVal.substring(closingPos + 2));
    } else {
      num.left = parse(num, sVal.substring(0, 1));
      num.right = parse(num, sVal.substring(2));
    }

    return num;
  }

  private static int findClosingPos(String sVal) {
    int open = 1;
    int i = 1;
    while (open > 0) {
      if (sVal.charAt(i) == '[') {
        open++;
      }
      if (sVal.charAt(i) == ']') {
        open--;
      }

      i++;
    }
    return i - 1;
  }

  public long getMagnitude() {
    if(this.value != null) {
      return this.value;
    }
    return 3*this.left.getMagnitude() + 2*this.right.getMagnitude();
  }

  public SnailfishNumber add(SnailfishNumber other) {
    SnailfishNumber sum = new SnailfishNumber(null);
    sum.left = this;
    this.parent = sum;
    sum.right = other;
    other.parent = sum;

    sum.calc();

    return sum;
  }

  private void calc() {
    AddAction nextAction = AddAction.EXPLODE;
    while (!AddAction.NONE.equals(nextAction)) {
      if (AddAction.EXPLODE.equals(nextAction)) {
        nextAction = explode(0);
      } else {
        nextAction = split();
      }
    }
  }

  private AddAction explode(int level) {
    if (level == 4 && value == null) {

      SnailfishNumber nextLeft = findNextLeft();

      if (nextLeft == null) {
        this.parent.left = new SnailfishNumber(0, this.parent);
      } else {
        if(this.parent.left == this) {
          this.parent.left = new SnailfishNumber(0, this.parent);
        }
        SnailfishNumber addValueToNumber = nextLeft.findMostRightValue();
        addValueToNumber.value = addValueToNumber.value + this.left.value;
      }

      SnailfishNumber nextRight = findNextRight();

      if (nextRight == null) {
        this.parent.right = new SnailfishNumber(0, this.parent);
      } else {
        if(this.parent.right == this) {
          this.parent.right = new SnailfishNumber(0, this.parent);
        }
        SnailfishNumber addValueToNumber = nextRight.findMostLeftValue();
        addValueToNumber.value = addValueToNumber.value + this.right.value;
      }


      return AddAction.EXPLODE;

    } else if (value == null) {
      AddAction nextAction = left.explode(level + 1);
      if (!AddAction.SPLIT.equals(nextAction)) {
        return AddAction.EXPLODE;
      }

      nextAction = right.explode(level + 1);
      if (!AddAction.SPLIT.equals(nextAction)) {
        return AddAction.EXPLODE;
      }
    }

    return AddAction.SPLIT;
  }

  private SnailfishNumber findMostRightValue() {
    SnailfishNumber mostRight = this;
    while (mostRight.right != null) {
      mostRight = mostRight.right;
    }
    return mostRight;
  }

  private SnailfishNumber findMostLeftValue() {
    SnailfishNumber mostLeft = this;
    while(mostLeft.left != null) {
      mostLeft = mostLeft.left;
    }
    return mostLeft;
  }

  private SnailfishNumber findNextLeft() {
    SnailfishNumber nextLeft = this;
    SnailfishNumber root = this.parent;
    while (root.left.equals(nextLeft) && root.parent != null) {
      nextLeft = root;
      root = root.parent;
    }
    if (root.parent == null && root.left.equals(nextLeft)) {
      return null;
    } else {
      return root.left;
    }
  }

  private SnailfishNumber findNextRight() {
    SnailfishNumber nextRight = this;
    SnailfishNumber root = this.parent;
    while (root.right.equals(nextRight) && root.parent != null) {
      nextRight = root;
      root = root.parent;
    }
    if (root.parent == null && root.right.equals(nextRight)) {
      return null;
    } else {
      return root.right;
    }
  }

  private AddAction split() {
    AddAction nextAction = AddAction.NONE;
    if(this.value == null) {
      if (this.left != null) {
        nextAction = this.left.split();
      }
      if (this.right != null && AddAction.NONE.equals(nextAction)) {
        nextAction = this.right.split();
      }
    } else {
      if (this.value > 9) {
        SnailfishNumber newNum = new SnailfishNumber(this.parent);
        newNum.left = new SnailfishNumber(this.value / 2, newNum);
        newNum.right = new SnailfishNumber(this.value % 2 == 0 ? this.value / 2 : this.value / 2 + 1, newNum);

        if (this.parent.left == this) {
          this.parent.left = newNum;
        } else {
          this.parent.right = newNum;
        }

        nextAction = AddAction.EXPLODE;
      }
    }
    return nextAction;
  }

  @Override
  public String toString() {
    if (value != null) {
      return "" + value;
    } else {
      return "[" + left + "," + right + "]";
    }
  }
}
