package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{
    Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            map.put(r.getUuid(),r);
        }
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }


    @Override
    protected void InsertResume(Resume r, int index) {

    }

    @Override
    protected void FillDeletedElement(int index) {

    }

    @Override
    protected void updateResume(int index, Resume r) {

    }

    @Override
    protected Resume getResume(int index) {
        return null;
    }
}
