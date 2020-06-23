package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Arrays;

@Data
public class Quiz {

    private String title;
    private String text;
    private String[] options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int answer;
    private long id;

    @Override
    public String toString() {
        return "Question{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + Arrays.toString(options) +
                ", answer=" + answer +
                ", id=" + id +
                '}';
    }

    public static final class Builder {

        private String title;
        private String text;
        private String[] options;
        private int answer;
        private long id;

        public Builder title( String title ) {
            this.title = title;
            return this;
        }

        public Builder text( String text ) {
            this.text = text;
            return this;
        }

        public Builder options( String[] options ) {
            this.options = options;
            return this;
        }

        public Builder answer( int answer ) {
            this.answer = answer;
            return this;
        }

        public Builder id( long id ) {
            this.id = id;
            return this;
        }

        public Quiz build() {
            Quiz quiz = new Quiz();
            quiz.title = this.title;
            quiz.text = this.text;
            quiz.options = this.options;
            quiz.answer = this.answer;
            quiz.id = this.id;
            return quiz;
        }
    }
}
