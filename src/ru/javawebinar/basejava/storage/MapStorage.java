package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {
    HashMap<String, Resume> map;

    public MapStorage(HashMap<String, Resume> map) {
        this.map = map;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void save(Resume r) {
        map.put(r.getUuid(), r);
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
    public void update(Resume r) {
        if(!map.containsValue(r)){
            throw new NotExistStorageException(r.getUuid());
        }
        else {
            updateResume(r.getUuid(), r );
        }
    }

    @Override
    public Resume get(String uuid) {
       if(!map.containsKey(uuid)){
           throw new NotExistStorageException(uuid);
       }
       return getResume(uuid);
    }

    @Override
    public void delete(String uuid) {
        if(!map.containsKey(uuid)){
            throw new NotExistStorageException(uuid);
        }
        else{
            FillDeletedElement(uuid);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
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
}
