package ru.javawebinar.basejava;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class MyFileVisitor extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        List<String> lines = Files.readAllLines(file);
        int count = 0;
        if (attrs.isDirectory()) {
            System.out.print("Directory: ");
        } else if (attrs.isRegularFile()) {
            count++;
            for (int i = 0; i < count; i++) {
                System.out.print(" ");
            }
            System.out.print("File: ");
        }

        System.out.println(file.getFileName());
        return FileVisitResult.CONTINUE;
    }
}

