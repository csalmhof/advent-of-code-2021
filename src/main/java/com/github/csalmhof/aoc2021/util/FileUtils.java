package com.github.csalmhof.aoc2021.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class FileUtils {

  private FileUtils() {
  }

  public static List<String> getFileInputAsList(String filePath) {
    Path path = Paths.get("src", "test", "resources", filePath);

    try {
      return Files.readAllLines(path);
    } catch (IOException e) {
      throw new RuntimeException("Error reading File", e);
    }
  }
}
