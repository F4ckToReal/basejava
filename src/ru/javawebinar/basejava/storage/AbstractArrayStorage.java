package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateResume(Object searchKey, Resume r) {
        storage[(int) searchKey] = r;
    }

    @Override
    public List<Resume> getAllSorted() {
        Arrays.sort(storage, 0, size, Resume::compareTo);
        return new ArrayList<>(Arrays.asList(storage).subList(0, size));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            InsertResume(r, (int) searchKey);
            size++;
        }
    }

    protected abstract void InsertResume(Resume r, int index);
}

