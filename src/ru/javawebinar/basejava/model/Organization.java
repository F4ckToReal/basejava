package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private final List <OrganizationTimeWork> timeWork;

    public Organization(Link homePage, List<OrganizationTimeWork> timeWork) {
        this.homePage = homePage;
        this.timeWork = timeWork;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) && timeWork.equals(that.timeWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, timeWork);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", timeWork=" + timeWork +
                '}';
    }
}

