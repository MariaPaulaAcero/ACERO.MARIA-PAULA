package entities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DocumentedActivity extends Activity {

    private NormalActivity activity;
    private List<Question> questions;
    private DocumentedActivity documentedActivity;

    public DocumentedActivity(String name, String state, Iteration iteration, NormalActivity activity) {
        super(name, state, iteration);
        this.activity = activity;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void addDocumentedActivity(DocumentedActivity documentedActivity) {

    }

    @Override
    public int getDuration() throws SabanaResearchException {
        int count = 0;
        for(DocumentedActivity d: documentedActivity) {
            count += d.countOpenActivities();
            if(count <=0){
                throw new SabanaResearchException(SabanaResearchException.BAD_FORMED_DOCUMENTED_ACTIVITY);
            }
        }
        return count;
    }
}
