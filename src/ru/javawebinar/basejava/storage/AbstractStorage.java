package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract void FillDeletedElement(Object searchKey);

    protected abstract void updateResume(Object searchKey, Resume r);

    protected abstract Resume getResume(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected Object getExistedKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected Object getNotExistedKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }


    public void update(Resume r) {
        Object searchKey = getExistedKey(r.getUuid());
        updateResume(searchKey, r);
    }


    @Override
    public void save(Resume r) {
        Object searchKey = getNotExistedKey(r.getUuid());
        doSave(r, searchKey);
    }


    public Resume get(String uuid) {
        Object searchKey = getExistedKey(uuid);
        return getResume(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedKey(uuid);
        FillDeletedElement(searchKey);
    }
}

