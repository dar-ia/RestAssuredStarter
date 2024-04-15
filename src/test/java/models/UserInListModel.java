package models;

import lombok.Data;

@Data
public class UserInListModel {
    int id;
    String email,
            first_name,
            last_name,
            avatar;
}
