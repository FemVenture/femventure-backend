package com.femventure.UsersManagement.domain.dto.Mentor.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorResponseDto {
    private Long id;
    private String fullName;
    private String location;
    private String description;
    private String image_url;
}
