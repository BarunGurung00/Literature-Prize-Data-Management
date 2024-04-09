package task2;

import java.lang.StringBuilder;

public class SwimStudent {

    private String name;
    private SwimLevel level;
    private boolean onWaitingList;

    public SwimStudent(String name, SwimLevel level){
        this.name = name;
        this.level = level;
        this.onWaitingList = false;

    }
    public boolean isOnWaitingList() {
        return onWaitingList;
    }

    public void setOnWaitingList(boolean onWaitingList) {
        this.onWaitingList = onWaitingList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SwimLevel getLevel() {
        return level;
    }

    public void setLevel(SwimLevel level) {
        this.level = level;
    }

    @Override
    public String toString(){
        StringBuilder str =  new StringBuilder();
        str.append("Name: " + name + "\n");
        str.append("Level: " + level + "\n");

        return str.toString();
    }
}
