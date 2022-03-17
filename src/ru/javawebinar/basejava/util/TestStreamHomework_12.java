package ru.javawebinar.basejava.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TestStreamHomework_12 {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter array length: ");
        String lengthMas = reader.readLine();
        int size = Integer.parseInt(lengthMas); // Читаем с клавиатуры размер массива и записываем в size
         int [] array = new int[size]; // Создаём массив int размером в size
        System.out.println("Insert array elements:");
        /*Пройдёмся по всему массиву, заполняя его*/
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(reader.readLine()); // Заполняем массив элементами, введёнными с клавиатуры
        }
        System.out.print("Inserted array elements:");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i]); // Выводим на экран, полученный массив
        }
        System.out.println();
        System.out.println(minValue(array));
    }
    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a,b) -> 10 * a + b);


    }

}


