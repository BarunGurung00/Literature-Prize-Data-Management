package task2;

import java.lang.StringBuilder;

public class SwimStudent {

    private String name;
    private String level;
    private boolean onWaitingList;

    public SwimStudent(String name, String level, boolean onWaitingList){
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
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
