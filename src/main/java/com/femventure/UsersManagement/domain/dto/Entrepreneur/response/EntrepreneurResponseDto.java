package com.femventure.UsersManagement.domain.dto.Entrepreneur.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrepreneurResponseDto {
    private Long id;
    private String fullName;
    private String location;
    private String description;
    private String image_url;
}
