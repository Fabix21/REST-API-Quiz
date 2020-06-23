package engine;

public class Answer {
    private int answer;

    public Answer() {
    }

    public Answer( int answer ) {
        this.answer = answer;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer( int answer ) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer=" + answer +
                '}';
    }
}
