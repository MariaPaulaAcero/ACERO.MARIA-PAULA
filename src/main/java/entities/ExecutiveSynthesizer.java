package entities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ExecutiveSynthesizer implements ISynthesizer {
    private List<Iteration>iterations=new ArrayList<>();

    @Override
    public Duration synthesize(List<Student> students,List<Iteration>iterations) throws SabanaResearchException {
        Duration duration=Duration.ZERO;
        for(Iteration i:this.iterations){
            duration = duration.plusDays(i.getActivitiesDuration().toDays());
        }
        return duration;
    }

}
