package entities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private int code;
    private String name;
    private String lastName;
    private String email;
    private ArrayList<Activity>activities=new ArrayList<>();
    private List<Course> approved = new ArrayList<>();

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public Duration getActivitiesDuration()throws SabanaResearchException{
        Duration duration=Duration.ZERO;
        for(Activity a:this.activities){
            duration = duration.plusDays(a.getDuration().toDays());

        }
        return duration;
    }
}
