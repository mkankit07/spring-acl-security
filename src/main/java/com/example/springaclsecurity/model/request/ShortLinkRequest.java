package com.example.springaclsecurity.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ShortLinkRequest {
    private Integer locationId;
    @NotNull
    private Integer siteId;
    @NotNull
    private String title;
    @NotNull
    private String publishStatus;
    private String description;
    private String prefix;
    @NotNull
    private String shortUrl;
    @NotNull
    private String longUrl;
    private String separator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime endDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startDateTime;
}
