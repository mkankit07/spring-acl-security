package com.example.springaclsecurity.repositories;

import com.example.springaclsecurity.model.entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {

    @PostFilter("hasPermission(filterObject, 'ADMINISTRATION') or hasPermission(filterObject, 'READ')")
    List<Site> findAll();

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    Site findByIdOrderByIdDesc(Integer integer);
}
