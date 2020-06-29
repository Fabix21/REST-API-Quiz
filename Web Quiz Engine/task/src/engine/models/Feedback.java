package engine.models;

import lombok.Getter;

@Getter
public class Feedback {

    private boolean success;
    private String feedback;

    public Feedback( boolean success ) {
        this.success = success;
        setFeedback();
    }

    private void setFeedback() {
        feedback = success ? "Congratulations, you're right!"
                : "Wrong answer! Please, try again.";
    }
}
