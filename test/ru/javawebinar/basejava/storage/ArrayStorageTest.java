package ru.javawebinar.basejava.storage;

import org.junit.experimental.categories.Category;

@Category(AbstractStorageTest.class)
public class ArrayStorageTest extends AbstractStorageTest {

    private static final Storage storage2 = new ArrayStorage();

    public ArrayStorageTest() {
        super(storage2);
    }
}