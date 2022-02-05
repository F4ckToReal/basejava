package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serializer.StreamSerialize;

import java.io.*;
import java.util.Map;

public class DataStreamSerializer implements StreamSerialize {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try(DataOutputStream out = new DataOutputStream(os)){
            out.writeUTF(r.getUuid());
            out.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            out.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                out.writeUTF(entry.getKey().name());
                out.writeUTF(entry.getValue());
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            // TODO implements sections
            return resume;
        }
    }
}
