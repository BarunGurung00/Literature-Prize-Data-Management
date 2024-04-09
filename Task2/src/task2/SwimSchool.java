package task2;

import java.util.ArrayList;
import java.util.List;

public class SwimSchool {
    private static List<Instructor> instructors;
    private static List<SwimStudent> students;
    private static List<SwimClass> classes;
    private static List<Qualification> qualifications;
    private static List<SwimStudent> waitingList;

    public SwimSchool() {
        this.instructors = new ArrayList<>();
        this.students = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.qualifications = new ArrayList<>();
        this.waitingList = new ArrayList<>();
    }

    public static List<Instructor> getInstructors() {
        return instructors;
    }

    public static List<SwimStudent> getStudents() {
        return students;
    }

    public static List<SwimClass> getClasses() {
        return classes;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public static void addToWaitingList(SwimStudent student) {
        waitingList.add(student);
    }

    public static void addQualification(Qualification qualification) {
        qualifications.add(qualification);
    }

    public static List<SwimStudent> getWaitingList() {
        return waitingList;
    }

    public static void removeFromWaitingList(SwimStudent student) {
        waitingList.remove(student);
    }

    // Retrieve swim class matching the student's level
    public static SwimClass findClassForStudent(SwimStudent student) {
        for (SwimClass swimClass : classes) {
            if (swimClass.getLevel() == student.getLevel() && swimClass.getStudents().contains(student)) {
                return swimClass;
            }
        }
        return null;
    }

    // Retrieve swim qualifications for a given student
    public static List<Qualification> getQualificationsForStudent(SwimStudent student) {
        List<Qualification> studentQualifications = new ArrayList<>();
        for (Qualification qualification : qualifications) {
            if (qualification.getStudent() == student) {
                studentQualifications.add(qualification);
            }
        }
        return studentQualifications;
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public static void addStudent(SwimStudent student) {
        students.add(student);
    }

    public void addClass(SwimClass swimClass) {
        classes.add(swimClass);
    }
}
