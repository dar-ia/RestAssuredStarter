package models;

import lombok.Data;

@Data
public class SingleUserModel {
    int id;
    String email,
            first_name,
            last_name,
            avatar;
}
