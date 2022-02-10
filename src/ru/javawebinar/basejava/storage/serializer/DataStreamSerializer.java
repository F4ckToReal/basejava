package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.Section;
import ru.javawebinar.basejava.model.SectionType;

import java.io.*;
import java.util.Map;

public class DataStreamSerializer implements StreamSerialize {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream out = new DataOutputStream(os)) {
            out.writeUTF(r.getUuid());
            out.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            out.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                out.writeUTF(entry.getKey().name());
                out.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> section = r.getSections();
            out.writeInt(section.size());
            for (Map.Entry<SectionType, Section> entry : section.entrySet()) {
                out.writeUTF(entry.getKey().name());
                out.writeUTF(entry.getValue().toString());
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
            return resume;
        }
    }
}
