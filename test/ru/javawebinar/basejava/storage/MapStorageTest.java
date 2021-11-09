package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;

public class MapStorageTest {
    private final MapStorage map = new MapStorage(new HashMap<>());

    private static final String UUID1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID1);

    private static final String UUID2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID2);

    private static final String UUID3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID3);

    @Before
    public void setUp() throws Exception {
        map.clear();
        map.save(RESUME_1);
        map.save(RESUME_2);
        map.save(RESUME_3);
    }

    @Test
    public void clear() {
        map.clear();
        Assert.assertEquals(0, map.size());
    }

    @Test
    public void save() {
        map.save(new Resume("UUID4"));
        Assert.assertEquals(4, map.size());
        Assert.assertEquals(new Resume("UUID4"), map.get("UUID4"));
    }

    @Test
    public void getAll() {
        Resume[] array = map.getAll();
        Assert.assertEquals(array.length, map.size());
    }

    @Test
    public void update() {
        map.update(RESUME_3);
        Assert.assertEquals(3, map.size());
        Assert.assertEquals(RESUME_3, RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        map.update(new Resume("YO"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        map.get("UUID5");
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, map.get(UUID1));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        map.delete(UUID3);
        map.get(UUID3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        map.delete("UUID6");
    }

}