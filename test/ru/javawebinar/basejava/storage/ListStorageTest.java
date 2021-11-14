package ru.javawebinar.basejava.storage;

import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Ignore("Нет переполнения")
    @Test
    public void saveOverFlow() {
        super.saveOverFlow();
    }
}