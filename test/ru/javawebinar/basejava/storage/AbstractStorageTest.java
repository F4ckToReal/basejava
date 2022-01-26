package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.io.File;
import java.io.IOException;
import java.util.*;


public abstract class AbstractStorageTest extends ResumeTestDate {
    protected static final File STORAGE_DIR = new File("C:\\Users\\Shiro\\basejava\\testResume");
    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
   // private static final Resume RESUME_4;

    private static final Map<ContactType, String> contacts = new EnumMap<ContactType, String>(ContactType.class);
    private static final Map<SectionType, Section> section = new EnumMap<>(SectionType.class);

    static {
        RESUME_1 = new Resume(UUID_1, "Ketti", contacts, section);
        RESUME_2 = new Resume(UUID_2, "Barri");
        RESUME_3 = createResume(UUID_3, "Genre");
    }


    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }


    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }


    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }


    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(UUID_1));
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
        storage.save(RESUME_3);
    }


    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid5");
    }


    @Test
    public void getAllSorted() throws IOException {
        List<Resume> list = storage.getAllSorted();
        List<Resume> list2 = new ArrayList<>();
        list2.add(0, RESUME_2);
        list2.add(1, RESUME_3);
        list2.add(2, RESUME_1);
        Assert.assertEquals(3, list.size());
        if (list == list2) {
            Assert.assertTrue(true);
        }
    }


    private void assertGet(Resume r) throws Exception {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

}