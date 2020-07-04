package engine.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String email;
    private String password;


    public String setPassword( String password ) {
        this.password = password;
        return password;
    }
}


