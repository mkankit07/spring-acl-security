package com.example.springaclsecurity.model.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ShortLinkResponseDto {
    private Integer id;
    private Integer locationId;
    private String prefix;
    private String shortUrl;
    private String longUrl;
    private String separator;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String publishStatus;
    private String title;
    private String description;
    private Integer createdBy;

}
