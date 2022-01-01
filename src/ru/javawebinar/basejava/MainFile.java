package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;


public class MainFile {

    public static void main(String[] args) throws IOException {
        String filePath = ".\\.gitignore";
/*
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }*/

        File dir = new File("./src/ru/javawebinar");
      /*  System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            // System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        printDirectoryDeeply(dir);

        //Files.walkFileTree(dir.toPath(),new MyFileVisitor());
    }
    // TODO: make pretty output
    public static void printDirectoryDeeply(File dir) {
        int count = 0;
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.print(" ");
                    count++;
                    System.out.println("File: " + file.getName() + " ");
                    for (int i = 0; i < count; i++) {
                        System.out.print(" ");
                    }
                } else if (file.isDirectory()) {
                    count = 0;
                    System.out.println("Directory: " + file.getName());
                    System.out.print(" ");
                    printDirectoryDeeply(file);
                }
            }
        }
    }
}