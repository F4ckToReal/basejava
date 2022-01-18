package ru.javawebinar.basejava.exception;

public class NotExistStorageException extends StorageException{
    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " not exist", uuid);
    }

    public NotExistStorageException(String uuid, Exception e){
        super(uuid, e.getMessage());
    }
}
