package ru.javawebinar.basejava.storage;

public class ArrayStorageTest extends AbstractStorageTest {

    private static final Storage storage2 = new ArrayStorage();

    public ArrayStorageTest() {
        super(storage2);
    }
}