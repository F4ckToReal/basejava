package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.io.File;
import java.util.*;


public abstract class AbstractStorageTest extends ResumeTestDate {
    protected static final File STORAGE_DIR = Config.get().getTestResume();
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
        RESUME_1 = new Resume(UUID_1, "Ketti");
        RESUME_2 = new Resume(UUID_2, "Barri");
        RESUME_3 = new Resume(UUID_3, "Genre");

        RESUME_1.addContact(ContactType.MAIL, "Yn@com");
        RESUME_1.addContact(ContactType.PHONE, "331276");

        RESUME_3.addContact(ContactType.PHONE, "551521");
        RESUME_3.addContact(ContactType.SKYPE, "Skype");
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
        newResume.addContact(ContactType.PHONE, "44-11-22");
        newResume.addContact(ContactType.MAIL, "yandex_me@mail.ru");
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
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(3, list.size());
        List<Resume> sortedResumes = Arrays.asList(RESUME_2, RESUME_3, RESUME_1);
        Collections.sort(sortedResumes);
        Assert.assertEquals(sortedResumes, list);
    }


    private void assertGet(Resume r) throws Exception {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

}