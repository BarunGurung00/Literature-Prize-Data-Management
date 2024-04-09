package task2;

public class Qualification {

    private String name;
    private SwimStudent student;
    private Instructor instructor;

    public Qualification(String name, SwimStudent student, Instructor instructor) {
        this.name = name;
        this.student = student;
        this.instructor = instructor;
    }

    public void setInstructor(Instructor instructor){
        this.instructor = instructor;
    }
    
    public Instructor getInstructor(){
        return instructor;
    }
    public SwimStudent getStudent() {
        return student;
    }

    public void setStudent(SwimStudent student) {
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
