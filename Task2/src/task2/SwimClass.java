package task2;

import java.lang.StringBuilder;
import java.util.*;

public class SwimClass {

    private SwimDay day;
    private String start_time;
    private SwimLevel level;
    private Instructor instructor;
    private static List<SwimStudent> students;

    public SwimClass(SwimDay day, String start_time, SwimLevel sl, Instructor ins){
        this.day = day;
        this.start_time = start_time;
        this.level = sl;
        this.instructor = ins;
        this.students = new ArrayList<>();
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public SwimDay getDay() {
        return day;
    }

    public void setDay(SwimDay day) {
        this.day = day;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public SwimLevel getLevel() {
        return level;
    }

    public void setLevel(SwimLevel level) {
        this.level = level;
    }

    public static List<SwimStudent> getStudents() {
        return students;
    }
    public void addStudent(SwimStudent student) {
        students.add(student);
    }

}


