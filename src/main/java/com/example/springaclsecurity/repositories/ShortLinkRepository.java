package com.example.springaclsecurity.repositories;

import com.example.springaclsecurity.model.entities.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortLinkRepository extends JpaRepository<ShortLink, Integer> {

    @PostFilter("hasPermission(filterObject, 'READ') and hasPermission(filterObject, 'ADMINISTRATION') or hasAuthority('SITE_ADMIN')")
    List<ShortLink> findAll();

}
