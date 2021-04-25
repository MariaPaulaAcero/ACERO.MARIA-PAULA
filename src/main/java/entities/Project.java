package entities;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;
    private LocalDate dateInit;
    private LocalDate dateEnd;
    private Group group;
    private List<Iteration> iterations= new ArrayList<>();
    private StudentSynthesizer studentSynthesizer=new StudentSynthesizer();
    private List<Student>students=new ArrayList<>();
    private ExecutiveSynthesizer executiveSynthesizer= new ExecutiveSynthesizer();

    public List<Student> getStudents() {
        return students;
    }
    public List<Iteration> getIterations() {
        return iterations;
    }

    public Project(String name, LocalDate dateInit, LocalDate dateEnd, Group group) {
        this.name = name;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
        this.group = group;
        this.iterations = new ArrayList<>();

        group.addProject(this);
    }

    public void addIteration(Iteration iteration) {
        this.iterations.add(iteration);
    }


    public Duration getDuration() throws SabanaResearchException {

            if (this.iterations.isEmpty())
                throw new SabanaResearchException(SabanaResearchException.BAD_FORMED_PROJECT);
            Duration d = Duration.ZERO;
            for (Iteration i : this.iterations) {
                d = d.plus(i.getDuration());
            }
            return d;

    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
    public boolean isActive() {
        boolean active=true;
        for(Iteration i:this.iterations){
            if(i.countOpenActivities()==0||this.dateEnd.isBefore(LocalDate.now())){
                active=false;
            }
        }
        return active;
    }
      /*  boolean isActive = true;
        if(LocalDate.now().isAfter(this.dateEnd)){
            isActive = false;
        }else {
            int openActivities = this.countOpenActivities();
            isActive = openActivities >0;
        }
        return isActive;
    }
       */
    public int countOpenActivities(){
        int count = 0;
        for(Iteration i: this.iterations){
            count += i.countOpenActivities();

        }
        return count;
    }
    public boolean isClosed() {
        boolean isClosed = true;
        if(LocalDate.now().isBefore(this.dateEnd)){
            isClosed = false;
        }else {
            int closedActivities = this.countClosedActivities();
            isClosed = closedActivities >0;
        }
        return isClosed;
    }
    public int countClosedActivities(){
        int count = 0;
        for(Iteration i: this.iterations){
            count += i.countClosedActivities();

        }
        return count;
    }

    public Duration summarize() throws SabanaResearchException {
        Duration duration=Duration.ZERO;
        if(isStudentsSynthesizer()){
            duration=duration.plusDays(studentSynthesizer.synthesize(students,iterations).toDays());
        }
        if(isExecutiveSynthesizer()){
            duration=duration.plusDays(executiveSynthesizer.synthesize(students,iterations).toDays());
        }
        return duration;
    }
    public boolean isStudentsSynthesizer(){
        boolean result=false;
        if(this.students != null){
            result=true;
        }
        return result;
    }
    public boolean isExecutiveSynthesizer(){
        boolean result=false;
        if(this.iterations != null){
            result=true;
        }
        return result;
    }

}
