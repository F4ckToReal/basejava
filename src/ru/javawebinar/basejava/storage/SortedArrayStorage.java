package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
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
    protected void FillDeletedElement(int index) {
        int position = size - index - 1;
        if (position > 0) {
            System.arraycopy(storage,index+1,storage,index,position);
        }

    }
}
