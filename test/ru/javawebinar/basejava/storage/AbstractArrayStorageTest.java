package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;
    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID1));
        storage.save(new Resume(UUID2));
        storage.save(new Resume(UUID3));
    }

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void get() {
        storage.get("uuid2");
        Assert.assertEquals(UUID2, "uuid2");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID3));
        Assert.assertEquals(UUID3, "uuid3");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist(){
        storage.update(new Resume("UUID5"));
        Assert.assertEquals("UUID5", UUID3);
    }

    @Test
    public void save() {
        storage.save(new Resume("YO"));
        Assert.assertEquals("YO", "YO");
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist(){
        storage.save(new Resume(UUID3));
    }

    @Test
    public void delete() {
        storage.delete("uuid1");
        Assert.assertEquals(2,storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist(){
        storage.delete("uuid5");
    }


    @Test
    public void getAll() {
        String[] resumes = new String[3];
        resumes[0] = "uuid1";
        resumes[1] = "uuid2";
        resumes[2] = "uuid3";
        Assert.assertEquals(Arrays.toString(storage.getAll()), Arrays.toString(resumes));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}