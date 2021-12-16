package com.github.csalmhof.aoc2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Puzzle16 extends AbstractPuzzle {

  @Override
  public int getDay() {
    return 16;
  }

  @Override
  public long calculatePart1Result(List<String> input) {

    String binary = input.get(0).chars()
        .mapToObj(c -> Integer.parseInt((char) c + "", 16))
        .map(i -> leftPad(Integer.toBinaryString(i), '0', 4))
        .collect(Collectors.joining());

    Packet p = Packet.of(binary);

    return p.calcVersSum();
  }

  private static String leftPad(String s, char fillchar, int len) {
    StringBuilder result = new StringBuilder(s);
    while (result.length() < len) {
      result.insert(0, fillchar);
    }

    return result.toString();
  }


  @Override
  public long calculatePart2Result(List<String> input) {
    String binary = input.get(0).chars()
        .mapToObj(c -> Integer.parseInt((char) c + "", 16))
        .map(i -> leftPad(Integer.toBinaryString(i), '0', 4))
        .collect(Collectors.joining());

    Packet p = Packet.of(binary);

    return p.calcValue();
  }

  public static class Packet {
    int version;
    int typeId;
    Long number;
    List<Packet> subPackets = new ArrayList<>();

    public long calcVersSum() {
      return version + subPackets.stream().map(Packet::calcVersSum).reduce(0L, Long::sum);
    }

    public Long calcValue() {
      if(typeId == 4) {
        return number;
      }

      if(typeId == 0) {
        return subPackets.stream()
            .map(Packet::calcValue)
            .reduce(0L, Long::sum);
      }

      if(typeId == 1) {
        return subPackets.stream()
            .map(Packet::calcValue)
            .reduce(1L, (v1, v2) -> v1*v2);
      }

      if(typeId == 2) {
        return subPackets.stream()
            .map(Packet::calcValue)
            .min(Long::compare).get();
      }

      if(typeId == 3) {
        return subPackets.stream()
            .map(Packet::calcValue)
            .max(Long::compare).get();
      }

      if(typeId == 5) {
        return subPackets.get(0).calcValue() > subPackets.get(1).calcValue() ? 1L : 0L;
      }

      if(typeId == 6) {
        return subPackets.get(0).calcValue() < subPackets.get(1).calcValue() ? 1L : 0L;
      }

      if(typeId == 7) {
        return subPackets.get(0).calcValue().equals(subPackets.get(1).calcValue()) ? 1L : 0L;
      }

      throw new IllegalStateException("Illegal type found: " + typeId);
    }

    public static Packet of(String binary) {
      return buildPacket(binary, 0).getValue();
    }

    private static Map.Entry<Integer, Packet> buildPacket(String binary, int nextPosition) {
      Packet result = new Packet();
      result.version = Integer.parseInt(binary.substring(nextPosition, nextPosition + 3), 2);
      nextPosition += 3;
      result.typeId = Integer.parseInt(binary.substring(nextPosition, nextPosition + 3), 2);
      nextPosition += 3;

      if (result.typeId == 4) {
        Map.Entry<Integer, Long> numberResult = parseNumber(binary, nextPosition);
        nextPosition = numberResult.getKey();
        result.number = numberResult.getValue();
      } else {
        nextPosition += 1;
        if (binary.charAt(nextPosition-1) == '1') {
          int numSubPackets = Integer.parseInt(binary.substring(nextPosition, nextPosition + 11), 2);
          nextPosition += 11;

          for (int i = 0; i < numSubPackets; i++) {
            Map.Entry<Integer, Packet> subPackageResult = buildPacket(binary, nextPosition);
            nextPosition = subPackageResult.getKey();
            result.subPackets.add(subPackageResult.getValue());
          }
        } else {
          int bitsSubPackets = Integer.parseInt(binary.substring(nextPosition, nextPosition + 15), 2);
          nextPosition += 15;

          int actualPosition = nextPosition;

          while(nextPosition-actualPosition < bitsSubPackets) {
            Map.Entry<Integer, Packet> subPackageResult = buildPacket(binary, nextPosition);
            nextPosition = subPackageResult.getKey();
            result.subPackets.add(subPackageResult.getValue());
          }
        }
      }

      return Map.entry(nextPosition, result);
    }

    private static Map.Entry<Integer, Long> parseNumber(String binary, int position) {
      StringBuilder binNum = new StringBuilder();

      int nextPosition = position;
      for (int i = position; i < binary.length(); i += 5) {
        String v = binary.substring(i, i + 5);
        binNum.append(binary, i + 1, i + 5);
        nextPosition += 5;

        if (v.startsWith("0")) {
          break;
        }
      }

      return Map.entry(nextPosition, Long.parseLong(binNum.toString(), 2));
    }
  }
}

