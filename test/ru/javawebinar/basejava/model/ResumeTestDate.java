package ru.javawebinar.basejava.model;

public class ResumeTestDate {

    public Resume createResume(String uuid, String fullName) {
        Resume testResume = new Resume(uuid, fullName);

        testResume.getContact(ContactType.PHONE);
        testResume.getContact(ContactType.MOBILE);
        testResume.getContact(ContactType.HOME_PHONE);
        testResume.getContact(ContactType.SKYPE);
        testResume.getContact(ContactType.MAIL);
        testResume.getContact(ContactType.LINKEDIN);
        testResume.getContact(ContactType.GITHUB);
        testResume.getContact(ContactType.STATCKOVERFLOW);
        testResume.getContact(ContactType.HOME_PAGE);

        testResume.getSection(SectionType.PERSONAL);
        testResume.getSection(SectionType.OBJECTIVE);
        testResume.getSection(SectionType.ACHIEVEMENT);
        testResume.getSection(SectionType.QUALIFICATIONS);
        testResume.getSection(SectionType.EXPERIENCE);
        testResume.getSection(SectionType.EDUCATION);

        return testResume;
    }
}