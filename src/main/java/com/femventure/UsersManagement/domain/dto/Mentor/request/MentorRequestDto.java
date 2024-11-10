package com.femventure.UsersManagement.domain.dto.Mentor.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorRequestDto {
    private String fullName;
    private String location;
    private String description;
    private String image_url;
}
