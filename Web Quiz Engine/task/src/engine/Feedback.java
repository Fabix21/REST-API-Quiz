package engine;

public class Feedback {

    private boolean success;
    private String feedback;

    public Feedback( boolean success ) {
        this.success = success;
        setFeedback();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess( boolean success ) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback() {
        feedback = success ? "Congratulations, you're right!"
                : "Wrong answer! Please, try again.";

    }
}
