package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SingleUserModel {
    int id;
    String email,
            avatar;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
}
