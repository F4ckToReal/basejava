package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void InsertResume(Resume r, int index) {
        int pos = Math.abs(index) - 1;
        System.arraycopy(storage, pos, storage, pos + 1, size - pos);
        storage[pos] = r;
    }

    @Override
    protected void FillDeletedElement(Object searchKey) {
        int position = size - (int) searchKey - 1;
        if (position > 0) {
            System.arraycopy(storage,(int) searchKey+1,storage,(int) searchKey,position);
        }
        storage[size - 1] = null;
        size--;
    }
}
