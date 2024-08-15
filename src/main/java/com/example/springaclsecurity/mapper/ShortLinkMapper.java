package com.example.springaclsecurity.mapper;

import com.example.springaclsecurity.model.entities.ShortLink;
import com.example.springaclsecurity.model.request.ShortLinkRequest;
import com.example.springaclsecurity.model.response.ShortLinkResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel =  "spring")
public interface ShortLinkMapper {

    ShortLink convertRequestToShortLink(ShortLinkRequest shortLinkRequest);

    @Mapping(target = "createdBy",source = "createdBy.userId")
    ShortLinkResponseDto convertShortLinkToDtos(ShortLink shortLinks);

    List<ShortLinkResponseDto> convertShortLinkToDto(List<ShortLink> shortLinks);
}
