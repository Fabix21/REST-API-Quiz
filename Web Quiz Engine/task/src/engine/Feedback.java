package engine;

import lombok.Getter;

@Getter
class Feedback {

    private boolean success;
    private String result;

    Feedback( boolean success ) {
        this.success = success;
        setFeedback();
    }

    private void setFeedback() {
        result = success ? "Congratulations, you're right!"
                : "Wrong answer! Please, try again.";
    }
}
