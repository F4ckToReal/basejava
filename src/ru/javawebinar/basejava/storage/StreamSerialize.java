package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

interface StreamSerialize {
    public void doWrite(Resume r, OutputStream os) throws IOException;

    public Resume doRead(InputStream is) throws IOException;
}
