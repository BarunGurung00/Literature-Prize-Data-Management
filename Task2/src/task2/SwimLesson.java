package task2;

import java.util.*;

public class SwimLesson {

    private SwimClass swimClass;
    private List<SwimStudent> students;

    public SwimLesson(SwimClass sc){
        this.swimClass = sc;
        this.students = new ArrayList<>();
    }
    public SwimClass getSwimClass(){
        return swimClass;
    }
    public List<SwimStudent> getSwimStudent(){
        return students;
    }
}
