package com.samiksha.timetable;

import java.util.Arrays;

public class TimetableGenerator {

    private Timetable timetable;
    private Classroom[] classrooms;
    private TimeSlot[] timeSlots;

    public TimetableGenerator(
            Classroom[] classrooms,
            TimeSlot[] timeSlots
    ) {
        this.classrooms = classrooms;
        this.timeSlots = timeSlots;
        this.timetable = new Timetable();
    }

    public void generateSchedule(Teacher[] teachers) {

        ClassRequest[] requests =
                new ClassRequest[teachers.length];

        for (int i = 0; i < teachers.length; i++) {

            requests[i] = new ClassRequest(
                    "General",
                    teachers[i],
                    0
            );
        }

        generateSchedule(requests);
    }

    public void generateSchedule(ClassRequest[] requests) {

        ClassRequest[] sortedRequests = requests.clone();

        Arrays.sort(
                sortedRequests,
                (request1, request2) ->
                        Integer.compare(
                                request2.getStudentCount(),
                                request1.getStudentCount()
                        )
        );

        for (ClassRequest request : sortedRequests) {

            Teacher teacher = request.getTeacher();

            boolean scheduled = false;

            for (TimeSlot timeSlot : timeSlots) {

                for (Classroom classroom : classrooms) {

                    if (classroom.getCapacity()
                            < request.getStudentCount()) {

                        continue;
                    }

                    ClassSchedule newSchedule =
                            new ClassSchedule(
                                    teacher,
                                    classroom,
                                    timeSlot,
                                    request.getSection(),
                                    request.getStudentCount()
                            );

                    boolean added =
                            timetable.addSchedule(newSchedule);

                    if (added) {
                        scheduled = true;
                        break;
                    }
                }

                if (scheduled) {
                    break;
                }
            }

            if (!scheduled) {

                System.out.println(
                        "No suitable schedule found for "
                                + request.getSection()
                                + " - "
                                + teacher.getSubject()
                );
            }
        }
    }

    public void displayGeneratedTimetable() {
        timetable.displayTimetable();
    }

    public int getScheduledCount() {
        return timetable.getScheduleCount();
    }
}