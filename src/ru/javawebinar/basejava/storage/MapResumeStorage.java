package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapResumeStorage extends MapUuidStorage{
    Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void FillDeletedElement(Object searchKey) {
        super.FillDeletedElement(searchKey);
    }

    @Override
    protected void updateResume(Object searchKey, Resume r) {
        super.updateResume(searchKey, r);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return super.getResume(searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsValue(searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        super.doSave(r, searchKey);
    }
}
