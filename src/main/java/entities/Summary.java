package entities;

import java.time.LocalDate;

public class Summary {

    private int activeProjects;
    private int closedProjects;
    private LocalDate date;

    Summary(int activeProjects, LocalDate date,int closedProjects){
        this.activeProjects = activeProjects;
        this.date = date;
        this.closedProjects=closedProjects;
    }

    public int getActiveProjects() {
        return activeProjects;
    }
    public int getClosedProjects() {
        return closedProjects;
    }

    public LocalDate getDate() {
        return date;
    }
}
