package ru.javawebinar.basejava.storage;

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
        list.add(index, r);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
