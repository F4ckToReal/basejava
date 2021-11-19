package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage{
    Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void FillDeletedElement(Object resume) {
        map.remove(((Resume) resume).getUuid());
    }

    @Override
    protected void updateResume(Object Resume, Resume r) {
        map.put(r.getUuid(),r);
    }

    @Override
    protected Resume getResume(Object resume) {
        return (Resume) resume ;
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null ;
    }

    @Override
    protected void doSave(Resume r, Object resume) {
       map.put(r.getUuid(), r);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumeList = new ArrayList<>(map.values());
        resumeList.sort(Resume::compareTo);
        return resumeList;
    }

    @Override
    public int size() {
        return map.size();
    }
}
