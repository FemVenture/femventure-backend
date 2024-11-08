package com.femventure.MentorhipManagement.domain.entity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mentorships")
public class Mentorship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float cost;
    private Long mentorId;
    private String description;
    private String contactDetails;
    private Integer duration;
    private String availability;
    private Boolean isMentorshipOffered;
}
