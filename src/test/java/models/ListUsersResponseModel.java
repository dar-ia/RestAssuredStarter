package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ListUsersResponseModel {
    int page,
            total;
    @JsonProperty("per_page")
    int per_page;
    @JsonProperty("total_pages")
    int totalPages;
    SingleUserModel[] data;
    SupportResponseModel support;
}
