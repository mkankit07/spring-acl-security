package com.example.springaclsecurity.controllers;

import com.example.springaclsecurity.model.request.AssignSiteRequest;
import com.example.springaclsecurity.model.request.SiteRequest;
import com.example.springaclsecurity.model.response.SiteResponseDto;
import com.example.springaclsecurity.service.SiteService;
import com.example.springaclsecurity.service.impl.UserInfoDetailImpl;
import com.example.springaclsecurity.utils.EntityResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/site")
public class SiteController {

    private final SiteService siteService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('SUPER_SITE_ADMIN')")
    public EntityResponse<Void, Void> siteAdd(@Valid @RequestBody SiteRequest siteRequest, @AuthenticationPrincipal UserInfoDetailImpl userInfoDetail){
        siteService.addSite(siteRequest, userInfoDetail);
        return EntityResponse.setEntityResponse(HttpStatus.OK.value(), "Site add successful!");

    }

    @GetMapping("/all")
    public EntityResponse<List<SiteResponseDto>, Void> getAll() {
        return EntityResponse.setEntityResponse(HttpStatus.OK.value(), "Site access successful!", siteService.getAll());
    }

    @PostMapping("/assign-site-access")
    public EntityResponse<Void, Void> sitePermission(@Valid @RequestBody AssignSiteRequest assignSiteRequest){
        siteService.assignSitePermission(assignSiteRequest);
        return EntityResponse.setEntityResponse(HttpStatus.OK.value(), "permission granted!");

    }
}
