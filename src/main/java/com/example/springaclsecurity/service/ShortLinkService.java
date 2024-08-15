package com.example.springaclsecurity.service;

import com.example.springaclsecurity.exceptions.DataNotFoundException;
import com.example.springaclsecurity.mapper.ShortLinkMapper;
import com.example.springaclsecurity.model.entities.ShortLink;
import com.example.springaclsecurity.model.entities.Site;
import com.example.springaclsecurity.model.request.ShortLinkRequest;
import com.example.springaclsecurity.model.response.ShortLinkResponseDto;
import com.example.springaclsecurity.repositories.ShortLinkRepository;
import com.example.springaclsecurity.repositories.SiteRepository;
import com.example.springaclsecurity.service.impl.UserInfoDetailImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortLinkService {

    private final ShortLinkRepository shortLinkRepository;
    private final SiteRepository siteRepository;
    private final ShortLinkMapper shortLinkMapper;
    private final AclPermissionService aclPermissionService;


    @Transactional
    public void addSortLink(ShortLinkRequest shortLinkRequest, UserInfoDetailImpl userInfoDetail){
        Site site = Optional.ofNullable(siteRepository.findByIdOrderByIdDesc(shortLinkRequest.getSiteId())).orElseThrow(()-> new DataNotFoundException("Site id not found"));
        ShortLink shortLink = shortLinkMapper.convertRequestToShortLink(shortLinkRequest);
        shortLink.addSite(site);
        shortLink = shortLinkRepository.save(shortLink);
        aclPermissionService.assignPermissionToUser(shortLink,shortLink.getId(),userInfoDetail.getUsername(), BasePermission.ADMINISTRATION,site,site.getId());
    }

    public List<ShortLinkResponseDto> getAllShortLink(){
        List<ShortLink> shortLinks=  shortLinkRepository.findAll();
        return shortLinkMapper.convertShortLinkToDto(shortLinks);
    }
}
