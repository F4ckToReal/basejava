package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doDelete(SK searchKey);

    protected abstract void updateResume(SK searchKey, Resume r);

    protected abstract Resume getResume(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract List<Resume> addResume();

    protected SK getExistedKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected SK getNotExistedKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }


    public void update(Resume r) {
        SK searchKey = getExistedKey(r.getUuid());
        updateResume(searchKey, r);
    }


    @Override
    public void save(Resume r) {
        SK searchKey = getNotExistedKey(r.getUuid());
        doSave(r, searchKey);
    }


    public Resume get(String uuid) {
        SK searchKey = getExistedKey(uuid);
        return getResume(searchKey);
    }

    public void delete(String uuid) {
        SK searchKey = getExistedKey(uuid);
        doDelete(searchKey);
    }

    public List<Resume> getAllSorted() {
        List<Resume> resumeList;
        resumeList = addResume();
        Collections.sort(resumeList);
        return resumeList;
    }

}

