package task2;

import java.lang.StringBuilder;

public class Instructor {

    private String name;

    public Instructor(String n){
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Instructor name: " + name );

        return str.toString();
    }
}
