package presentation;

import course.Course;
import person.Professor;
import room.Room;

import java.util.ArrayList;

public class Presentation {
    private String id;
    private Course course;
    private ArrayList<PresentationTime> presentationTimes;
    private String term;
    private Room room;
    private Professor professor;

    public Presentation(String id, Course course, ArrayList<PresentationTime> presentationTimes, String term, Room room,
                        Professor professor) {
        this.id = id;
        this.course = course;
        this.presentationTimes = presentationTimes;
        this.term = term;
        this.room = room;
        this.professor = professor;
    }
}
