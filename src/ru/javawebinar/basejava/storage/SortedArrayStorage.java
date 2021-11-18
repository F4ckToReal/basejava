package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {


    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
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
            System.arraycopy(storage, (int) searchKey + 1, storage, (int) searchKey, position);
        }
        storage[size - 1] = null;
        size--;
    }
}
