package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

public class Organization {
    private final Link homePage;
    private final List<Position> timeWork;

    public Organization(String name, String url, Position... timeWorks) {
        this(new Link(name, url), Arrays.asList(timeWorks));
    }

    public Organization(Link homePage, List<Position> timeWork) {
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
        return "Organization(" + homePage + ", " + timeWork + ')';
    }

    public static class Position { ;
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final Set<String> title;
        private final String description;

        public Position(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, Collections.singleton(title), description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), Collections.singleton(title), description);
        }

        public Position(LocalDate startDate, LocalDate endDate, Set<String> title, String description) {
            Objects.requireNonNull(startDate, "startDay must not be null");
            Objects.requireNonNull(endDate, " endDay must not be null");
            Objects.requireNonNull(title, " title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }


            public LocalDate getStartDate () {
                return startDate;
            }

            public LocalDate getEndDate () {
                return endDate;
            }

            public Set<String> getTitle () {
                return title;
            }

            public String getDescription () {
                return description;
            }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return startDate.equals(position.startDate) &&
                    endDate.equals(position.endDate) &&
                    title.equals(position.title) &&
                    Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }
        @Override
        public String toString() {
            return "Position(" + startDate + ',' + endDate + ',' + title + ',' + description + ')';
        }
    }
}

