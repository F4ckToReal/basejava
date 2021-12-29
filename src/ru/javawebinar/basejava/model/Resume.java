package ru.javawebinar.basejava.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private final String fullName;

    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    private final Map<SectionType, Section> section = new EnumMap<>(SectionType.class);


    public Resume(String uuid, String fullName, Map<ContactType, String> contacts, Map<SectionType, Section> section) {
        Objects.requireNonNull(uuid, "fullName must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }


    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "fullName must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }


    public Section getSection(SectionType type) {
        return section.get(type);
    }


    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void addContact(ContactType type, String value) {
        contacts.put(type, value);
    }

    public void addSection(SectionType type, Section sections) {
        section.put(type, sections);
    }


    @Override
    public String toString() {
        return uuid + "(" + fullName + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public int compareTo(Resume o) {
        int comp = fullName.compareTo(o.fullName);
        return comp != 0 ? comp : uuid.compareTo(o.uuid);
    }

}
