package models;

import lombok.Data;

@Data
public class UpdateUserResponseModel {
    String id,
            name,
            job,
            updatedAt;
}
