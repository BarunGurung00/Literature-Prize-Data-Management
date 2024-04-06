package task2;

import java.lang.StringBuilder;
import java.util.*;

public class SwimClass {

    private SwimDay day;
    private String start_time;
    private String level;
    private Instructor instructor;
    private List<SwimStudent> students;

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<SwimStudent> getStudents() {
        return students;
    }

    public void setStudents(List<SwimStudent> students) {
        this.students = students;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Day: " + day + "\n");
        str.append("Start time: " + start_time + "\n");
        str.append("Level of the swimmer -> " + level + "\n");

        return str.toString();
    }
}


