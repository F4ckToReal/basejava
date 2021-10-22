package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume " + r.getUuid() + " not exist");
        } else {
            storage[index] = r;
        }

    }

    @Override
    public void delete(String uuid) {
        int i;
        for (i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                size--;
                break;
            }
        }
        for (int k = i + 1; k <= size; k++) {
            storage[k - 1] = storage[k];
        }
    }

    @Override
    public void save(Resume r) {
        int position = getIndex(r.getUuid());
        if (position >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else if (Math.abs(position) - 1 >= size) {
            storage[size] = r;
            size++;
        } else {
            int pos = Math.abs(position) - 1;
            for (int i = size; i > pos; i--) {
                storage[i] = storage[i - 1];
            }
            storage[pos] = r;
            size++;
        }
    }


    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
