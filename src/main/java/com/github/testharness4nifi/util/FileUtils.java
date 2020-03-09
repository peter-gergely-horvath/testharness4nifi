



package com.github.testharness4nifi.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

public final class FileUtils {


    private static final String MAC_DS_STORE_NAME = ".DS_Store";

    private FileUtils() {
        // no instances
    }

    public static void deleteDirectoryRecursive(Path directory) throws IOException {
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void deleteDirectoryRecursive(File dir) {
        try {
            deleteDirectoryRecursive(dir.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createLink(Path newLink, Path existingFile)  {
        try {
            Files.createSymbolicLink(newLink, existingFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createSymlinks(File newLinkDir, File existingDir) {
        Arrays.stream(existingDir.list())
                .filter(fileName -> !MAC_DS_STORE_NAME.equals(fileName))
                .forEach(fileName -> {
                    Path newLink = Paths.get(newLinkDir.getAbsolutePath(), fileName);
                    Path existingFile = Paths.get(existingDir.getAbsolutePath(), fileName);

                    File symlinkFile = newLink.toFile();
                    if (symlinkFile.exists()) {
                        symlinkFile.delete();
                    }

                    createLink(newLink, existingFile);
                });
    }
}
