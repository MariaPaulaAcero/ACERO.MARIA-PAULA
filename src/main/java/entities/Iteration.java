package entities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Iteration {

    public Duration Duration;
    private String goal;
    private Project project;
    private List<Activity> activities;
    public List<Activity> getActivities() {
        return activities;
    }

    public Iteration(String goal, Project project) {
        this.goal = goal;
        this.project = project;
        this.activities = new ArrayList<>();

        project.addIteration(this);
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }
    public int countOpenActivities(){
        int count = 0;
        for (Activity a : this.activities) {
            if (a.isActive()) {
                count++;
            }

        }
        return count;

    }
    public int countClosedActivities(){
        int count = 0;
        for (Activity a : this.activities) {
            if (!a.isActive()) {
                count++;
            }
        }
        return count;

    }

    public Duration getDuration() throws SabanaResearchException {
        Duration duration1=Duration.ZERO;
        if(this.activities.isEmpty()){
            throw new SabanaResearchException(SabanaResearchException.BAD_FORMED_ITERATION);
        }
        for(Activity a:this.activities){
            duration1=duration1.plusDays(a.getDuration().toDays());
        }
        return duration1;
    }
    public Duration getActivitiesDuration() throws SabanaResearchException {
        return getDuration();
    }

}
