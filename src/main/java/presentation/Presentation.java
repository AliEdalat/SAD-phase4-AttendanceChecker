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

    public Presentation(String courseName, Professor professor) {
        this.id = "1"; // TODO: Any other idea?!
        this.course = new Course(courseName);
        this.presentationTimes = new ArrayList<>();
        this.term = "95961";
        this.room = new Room(101);
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }
}
