package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> list;

    public ListStorage(List<Resume> list) {
        this.list = list;
    }


    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return list.indexOf(resume);
    }

    @Override
    protected void InsertResume(Resume r, int index) {
        list.add(index, r);
    }

    @Override
    protected void FillDeletedElement(int index) {
        list.remove(index);
    }

    @Override
    protected void updateResume(int index, Resume r) {
        list.set(index, r);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            list.add(r);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(uuid);
    }

    @Override
    public Resume[] getAll() throws ClassCastException {
        return list.toArray(new Resume[0]);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            FillDeletedElement(index);
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    private Resume getResume(String uuid) {
        Resume resume = new Resume(uuid);
        return list.get(list.indexOf(resume));
    }
}
