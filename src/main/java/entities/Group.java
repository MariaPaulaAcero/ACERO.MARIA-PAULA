package entities;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private String name;
    private List<Project> projects;

    public Group(String name) {
        this.name = name;
        this.projects = new ArrayList<>();
    }

    public int CountActiveProjects() {
       // return (int) this.projects.stream().map(p->p.isActive()).filter(b->b).count();
        int count = 0;
        for (Project p : this.projects) {
            if (p.isActive()) {
                count++;
            }

        }
        return count;

    }
    public int countClosedProjects(){
        int count = 0;
        for (Project p : this.projects) {
            if (!p.isActive()) {
                count++;
            }

        }
        return count;

    }

    public void addProject(Project plan) {
        this.projects.add(plan);
    }

}
