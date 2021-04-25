package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SabanaResearch {

    private List<Group> groups;
    private List<Summary> summaries;

    public List<Group> getGroups() {
        return groups;
    }

    public SabanaResearch(List<Group> groups) {
        this.groups = groups;
        this.summaries = new ArrayList<>();
    }

    public int countOfGroups() {
        return this.groups.size();
    }

    public int countOfSummaries() {
        return this.summaries.size();
    }

    /**
     * Create a summary entry in the current date.
     * - Calculate the count of active projects.
     *
     * @return The new Summary entry.
     */
    public Summary createSummaryEntry() {

        // int activeProjects = this.groups.stream().map(g->g.CountActiveProjects()).reduce(0,(a,b)->a+b);
        int activeProjects =0;
        int closedProjects=0;
        for(Group g: this.groups){
            activeProjects += g.CountActiveProjects();
            closedProjects += g.countClosedProjects();
        }

        Summary summary =new Summary(activeProjects,LocalDate.now(),closedProjects);
        this.summaries.add(summary);

        return summary;

    }
}
