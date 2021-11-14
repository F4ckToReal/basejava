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

    private String fullName;


    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID1, "Олег Викторович"));
        storage.save(new Resume(UUID2, "Зинаида Петровна"));
        storage.save(new Resume(UUID3, "Оксана Тобакова"));
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
        storage.update(new Resume(UUID3, "Оксана Тобакова"));
        Assert.assertEquals(new Resume(UUID3, "Оксана Тобакова"), storage.get(UUID3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get("Kitti");

    }

    @Test
    public void save() {
        storage.save(new Resume("YO", "Децл Децл"));
        Assert.assertEquals("YO", "YO");
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID3, "Оксана Тобакова"));
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Hello your number: " + i, "overFlow"));
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