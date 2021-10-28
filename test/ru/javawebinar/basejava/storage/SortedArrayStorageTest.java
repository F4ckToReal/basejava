package ru.javawebinar.basejava.storage;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
private static final Storage storage1 = new SortedArrayStorage();

    public SortedArrayStorageTest() {
        super(storage1);
    }
}