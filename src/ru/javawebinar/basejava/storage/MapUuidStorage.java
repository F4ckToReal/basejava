package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {
    Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumeList = new ArrayList<>(map.values());
        resumeList.sort(Comparator.comparing(Resume::getFullName));
        return resumeList;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }


    @Override
    protected void FillDeletedElement(Object searchKey) {
        map.remove((String) searchKey);
    }

    @Override
    protected void updateResume(Object searchKey, Resume r) {
        map.put((String) searchKey, r);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return map.get((String) searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey((String) searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }
}
