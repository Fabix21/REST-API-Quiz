package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
class Quiz {
    private String title;
    private String text;
    private String[] options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String[] answer;
    private long id;

}
