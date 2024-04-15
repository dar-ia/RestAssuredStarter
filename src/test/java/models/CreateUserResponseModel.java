package models;

import lombok.Data;

@Data
public class CreateUserResponseModel {
    String id,
            name,
            job,
            createdAt;
}
