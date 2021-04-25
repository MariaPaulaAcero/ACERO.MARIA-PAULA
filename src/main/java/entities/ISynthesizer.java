package entities;

import java.time.Duration;
import java.util.List;

public interface ISynthesizer{

    Duration synthesize(List<Student> students,List<Iteration>iterations) throws SabanaResearchException;

}
