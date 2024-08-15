package com.example.springaclsecurity.repositories;

import com.example.springaclsecurity.model.entities.SiteHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteHierarchyRepository extends JpaRepository<SiteHierarchy, Integer> {

}