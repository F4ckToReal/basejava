package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;


public abstract class AbstractStorageTest {

    private final Storage storage;
    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID1, fullName));
        storage.save(new Resume(UUID2, fullName));
        storage.save(new Resume(UUID3, fullName));
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void get() {
        storage.get(UUID2);
        storage.get(UUID1);
        storage.get(UUID3);
        Assert.assertEquals(UUID2, "uuid2");
        Assert.assertEquals(UUID1, "uuid1");
        Assert.assertEquals(UUID3, "uuid3");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID3, fullName);
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(UUID3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get("Kitti");

    }

    @Test
    public void save() {
        storage.save(new Resume("YO", fullName));
        Assert.assertEquals("YO", "YO");
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID3, fullName));
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Hello your number: " + i, fullName));
            }
        } catch (StorageException e) {
            Assert.fail("Переполнение произошло раньше времени");
        }
        storage.save(new Resume());
    }


    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID3);
        storage.get(UUID3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid5");
    }


    @Test
    public void getAll() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}