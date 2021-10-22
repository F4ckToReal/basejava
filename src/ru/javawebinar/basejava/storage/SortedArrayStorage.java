package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    public void clear() {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void delete(String uuid) {

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
            System.out.println(pos);
            for (int i = size; i >= pos; i--) {
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
