package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[4];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resum) {
        boolean resumeInBase = false;
        if (resum.toString() != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(resum.toString())) {
                    System.out.println("Резюме с таким UUID присутствует в базе");
                    resumeInBase = true;
                }
            }
            if ((!resumeInBase) && (size < storage.length)) {
                storage[size] = resum;
                size++;
            }
            if (size == storage.length) {
                System.out.println("База резюме заполнена");
            }
        } else System.out.println("Ошибка, вы не ввели uuid");
    }


    public void update(Resume resume) {
        boolean chekResume = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(resume.toString())) {
                storage[i] = resume;
                chekResume = true;
            }
        }
        if (!chekResume) printNull();
    }


    public Resume get(String uuid) {
        boolean chekResume = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                chekResume = true;
                return storage[i];
            }
        }
        if (!chekResume) printNull();
        return null;
    }


    public void delete(String uuid) {
        boolean chekResume = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                chekResume = true;
            }
        }
        if (!chekResume) printNull();
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, allResume, 0, size);
        return allResume;
    }

    public int size() {
        return size;
    }

    void printNull() {
        System.out.println("Нет в базе данных!");
    }
}

