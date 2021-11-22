package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage<Integer> {
    protected List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid))
                return i;
        }
        return -1;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return list;
    }


    @Override
    protected void doDelete(Integer searchKey) {
        list.remove((int)searchKey);
    }

    @Override
    protected void updateResume(Integer searchKey, Resume r) {
        list.set(searchKey, r);
    }

    @Override
    protected Resume getResume(Integer searchKey) {
        return list.get(searchKey);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        list.add(r);
    }

}
