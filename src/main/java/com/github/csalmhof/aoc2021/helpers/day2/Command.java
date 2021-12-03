package com.github.csalmhof.aoc2021.helpers.day2;

import java.util.List;
import java.util.stream.Collectors;

public class Command {
  private Instruction instruction;
  private int value;

  public Command(String commandline) {
    String[] splitted = commandline.split(" ");
    this.instruction = Instruction.fromString(splitted[0]);
    this.value = Integer.parseInt(splitted[1]);
  }

  public Instruction getInstruction() {
    return instruction;
  }

  public int getValue() {
    return value;
  }

  public static List<Command> toCommands(List<String> strings) {
    return strings.stream()
        .map(Command::new)
        .collect(Collectors.toList());
  }
}
