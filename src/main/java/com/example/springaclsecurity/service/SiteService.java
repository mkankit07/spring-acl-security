package com.example.springaclsecurity.service;


import com.example.springaclsecurity.exceptions.DataNotFoundException;
import com.example.springaclsecurity.mapper.SiteMapper;
import com.example.springaclsecurity.model.entities.Site;
import com.example.springaclsecurity.model.entities.SiteHierarchy;
import com.example.springaclsecurity.model.entities.User;
import com.example.springaclsecurity.model.request.AssignSiteRequest;
import com.example.springaclsecurity.model.request.SiteRequest;
import com.example.springaclsecurity.model.response.SiteResponseDto;
import com.example.springaclsecurity.repositories.SiteHierarchyRepository;
import com.example.springaclsecurity.repositories.SiteRepository;
import com.example.springaclsecurity.repositories.UserRepository;
import com.example.springaclsecurity.service.impl.UserInfoDetailImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SiteService {

    private final SiteRepository siteRepository;
    private final SiteMapper siteMapper;
    private final UserRepository userRepository;
    private final SiteHierarchyRepository siteHierarchyRepository;
    private static final Integer SITE_CLASS_ID =1;
    private final AclPermissionService aclPermissionService;

    @Transactional
    public void addSite(SiteRequest siteRequest, UserInfoDetailImpl userInfoDetail) {
        Site site = siteMapper.convertSiteRequestToSite(siteRequest);
        Site parentSite = null;
        if (siteRequest.getParentSiteId() != null) {
            parentSite = Optional.ofNullable(siteRepository.findByIdOrderByIdDesc(siteRequest.getParentSiteId())).orElseThrow(() -> new DataNotFoundException("parent site id not exists!"));
        }
        site = siteRepository.save(site);
        addSiteHierarchy(site, parentSite);

        aclPermissionService.assignPermissionToUser(site, site.getId(), userInfoDetail.getUsername(), BasePermission.ADMINISTRATION);

    }

    private void addSiteHierarchy(Site site, Site parentSite){
        if (parentSite != null) {
            siteHierarchyRepository.save(SiteHierarchy.builder()
                    .childSite(site).parentSite(parentSite).build());
        }
    }


    public List<SiteResponseDto> getAll(){
        List<Site> sites = siteRepository.findAll();
        return siteMapper.convertSiteToDto(sites);
    }

    @Transactional
    public void assignSitePermission(AssignSiteRequest siteRequest){
        Site site = Optional.ofNullable(siteRepository.findByIdOrderByIdDesc(siteRequest.getSiteId())).orElseThrow(()->new DataNotFoundException("site id not found"));
        User user = userRepository.findById(siteRequest.getUserId()).orElseThrow();
        String type = siteRequest.getType().toUpperCase();
        if("USER".equals(type)){
            aclPermissionService.assignPermissionToUser(site, site.getId(), user.getUsername(), BasePermission.READ);
        }else {
            aclPermissionService.assignPermissionToAuthority(site, site.getId(), "SITE_ADMIN", BasePermission.READ);
        }
    }

}
