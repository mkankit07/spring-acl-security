package com.example.springaclsecurity.controllers;


import com.example.springaclsecurity.model.request.ShortLinkRequest;
import com.example.springaclsecurity.model.response.ShortLinkResponseDto;
import com.example.springaclsecurity.service.ShortLinkService;
import com.example.springaclsecurity.service.impl.UserInfoDetailImpl;
import com.example.springaclsecurity.utils.EntityResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/short-link")
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    @PostMapping("/add")
    public EntityResponse<Void, Void> addNewShortLink(@Valid @RequestBody ShortLinkRequest shortLinkRequest, @AuthenticationPrincipal UserInfoDetailImpl userInfoDetail){
        shortLinkService.addSortLink(shortLinkRequest, userInfoDetail);
        return EntityResponse.setEntityResponse(HttpStatus.OK.value(), "Short link saved!");
    }

    @GetMapping("/all")
    public  EntityResponse<List<ShortLinkResponseDto>, Void> getAll(){
        return EntityResponse.setEntityResponse(HttpStatus.OK.value(), "Site access successful!", shortLinkService.getAllShortLink());

    }
}
