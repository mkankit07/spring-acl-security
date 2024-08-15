package com.example.springaclsecurity.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SiteRequest {
    private String title;
    private String description;
    private String heroImageUrl;
    private String locationId;
    @NotNull
    private Integer parentSiteId;
}
