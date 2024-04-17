package models;

import lombok.Data;

@Data
public class ListUsersResponseModel {
    int page,
            per_page,
            total,
            total_pages;
    SingleUserModel[] data;
    SupportResponseModel support;


}
