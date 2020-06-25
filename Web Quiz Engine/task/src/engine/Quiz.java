package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
class Quiz {
    private String title;
    private String text;
    private String[] options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String[] answer;

    @Id
    @GeneratedValue
    private long id;

}
