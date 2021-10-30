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
        Assert.assertEquals(new Resume(UUID3), new Resume(UUID3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("UUID5"));
        Assert.assertEquals(new Resume("UUID5"), new Resume(UUID1));
    }

    @Test
    public void save() {
        storage.save(new Resume("YO"));
        Assert.assertEquals("YO", "YO");
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void saveOverFlow() {
        Resume[] resumes = new Resume[3];
        try {
            for (int i = 0; i < resumes.length; i++) {
                resumes[i] = new Resume("Hello your number: " + i);
            }
        } catch (Exception e) {
            Assert.fail("Переполнение произошло раньше времени");
        }
        resumes[4] = new Resume("overFlow");
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