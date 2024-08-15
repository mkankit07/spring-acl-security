package com.example.springaclsecurity.model.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AssignSiteRequest {
    private Integer userId;
    private Integer siteId;
    private String type;
}
