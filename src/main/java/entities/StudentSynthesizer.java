package entities;

import com.sun.org.apache.xpath.internal.objects.XNull;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StudentSynthesizer implements ISynthesizer{

    @Override
    public Duration synthesize(List<Student>students,List<Iteration>iterations) throws SabanaResearchException {
        Duration duration=Duration.ZERO;
        for(Student s:students){
            duration = duration.plusDays(s.getActivitiesDuration().toDays());
        }
        return duration;
    }

}
