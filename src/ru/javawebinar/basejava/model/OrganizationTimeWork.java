package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class OrganizationTimeWork {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Set<String> title;
    private final String description;

    public OrganizationTimeWork(LocalDate startDate, LocalDate endDate, Set<String> title, String description) {
        Objects.requireNonNull(startDate, "startDay must not be null");
        Objects.requireNonNull(endDate, " endDay must not be null");
        Objects.requireNonNull(title, " title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate,endDate,title,description);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationTimeWork that = (OrganizationTimeWork) o;
        return startDate.equals(that.startDate) && endDate.equals(that.endDate) && title.equals(that.title) && Objects.equals(description, that.description);
    }

}
