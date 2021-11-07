package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateResume(index, r);
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void InsertResume(Resume r, int index);

    protected abstract void FillDeletedElement(int index);

    protected abstract void updateResume(int index, Resume r);
}
