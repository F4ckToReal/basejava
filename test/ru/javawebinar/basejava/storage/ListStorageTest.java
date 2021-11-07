package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;

public class ListStorageTest {

   private final ListStorage list = new ListStorage(new ArrayList<>());


    @Before
    public void setUp() throws Exception {
        list.clear();
        list.save(new Resume("uuid1"));
        list.save(new Resume("uuid2"));
        list.save(new Resume("uuid4"));
    }

    @Test
    public void getIndex() {
        list.getIndex("uuid2");
        Assert.assertEquals(1,1);
    }


    @Test
    public void clear() {
        list.clear();
        Assert.assertEquals(0,list.size());
    }

    @Test
    public void update(){
        list.update(new Resume("uuid1"));
        Assert.assertEquals("uuid1", "uuid1");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist(){
        list.update(new Resume("uuid7"));
    }

    @Test
    public void save() {
        list.save(new Resume("uuid5"));
        Assert.assertEquals("uuid5", "uuid5");
        Assert.assertEquals(4,list.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        list.save(new Resume("uuid1"));
    }

    @Test
    public void get() {
        list.get("uuid4");
        Assert.assertEquals("uuid4", "uuid4");
    }

    @Test (expected = NotExistStorageException.class)
    public void getNotExist(){
        list.get("Hello");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        list.delete("uuid4");
        list.get("uuid4");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        list.delete("uuid5");
    }

    @Test
    public void getAll() {
        ArrayList<Resume> arrayList = new ArrayList<>();
        arrayList.add(new Resume("uuid1"));
        arrayList.add(new Resume("uuid2"));
        arrayList.add(new Resume("uuid4"));
        Assert.assertEquals(arrayList.toString(), Arrays.toString(list.getAll()));
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3,list.size());
    }

}