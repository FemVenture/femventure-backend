package com.femventure.MentorhipManagement.domain.dto.Mentorship.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorshipResponseDto {
    private Float cost;
    private Long mentorId;
    private String description;
    private String contactDetails;
    private Integer duration;
    private String availability;
    private Boolean isMentorshipOffered;
}
