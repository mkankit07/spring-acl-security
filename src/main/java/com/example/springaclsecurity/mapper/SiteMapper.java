package com.example.springaclsecurity.mapper;

import com.example.springaclsecurity.model.entities.Site;
import com.example.springaclsecurity.model.request.SiteRequest;
import com.example.springaclsecurity.model.response.SiteResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel =  "spring")
public interface SiteMapper {

    Site convertSiteRequestToSite(SiteRequest siteRequest);

    @Mapping(target = "createdBy",source = "createdBy.userId")
    SiteResponseDto convertSiteToDtos(Site sites);
    List<SiteResponseDto> convertSiteToDto(List<Site> sites);
}
