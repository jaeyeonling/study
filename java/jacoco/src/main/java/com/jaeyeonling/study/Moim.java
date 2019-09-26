package com.jaeyeonling.study;

public class Moim {

    private final int maxNumberOfAttendees;
    private final int numberOfEnrollment;

    public Moim(final int maxNumberOfAttendees,
                final int numberOfEnrollment) {
        this.maxNumberOfAttendees = maxNumberOfAttendees;
        this.numberOfEnrollment = numberOfEnrollment;
    }

    public boolean isFull() {
        if (maxNumberOfAttendees == 0) {
            return false;
        }

        return numberOfEnrollment >= maxNumberOfAttendees;
    }
}
