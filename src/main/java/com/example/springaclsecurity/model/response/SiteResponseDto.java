package com.example.springaclsecurity.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SiteResponseDto {
    private Integer id;
    private String title;
    private String description;
    private String heroImageUrl;
    private Integer locationId;
    private Integer createdBy;
}
