package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serializer.StreamSerialize;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final StreamSerialize myFile;

    protected FileStorage(File directory, StreamSerialize myFile) {
        Objects.requireNonNull(directory, "directory must not be null");
        this.myFile = myFile;
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;

    }


    @Override
    public void clear() {
        File[] direct = directory.listFiles();
        if (direct != null) {
            for (File deleteFile : direct) {
                doDelete(deleteFile);
            }
        }
    }

    @Override
    public int size() {
        String[] files = directory.list();
        if (files == null) {
            throw new StorageException("Directory read error");
        }
        return files.length;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(File file, Resume r) {
        try {
            myFile.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File couldn't update", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(file, r);
    }


    @Override
    protected Resume doGet(File file) {
        try {
            return myFile.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File delete error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }


    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> allResume = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error");
        }
        for (File file : files) {
            allResume.add(doGet(file));
        }
        return allResume;
    }

}
