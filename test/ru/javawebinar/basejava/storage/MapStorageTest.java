package ru.javawebinar.basejava.storage;


import org.junit.Ignore;
import org.junit.Test;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapUuidStorage());
    }

    @Ignore("Нет переполнения")
    @Test
    public void saveOverFlow() {
        super.saveOverFlow();
    }
}