package com.example.springaclsecurity.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sites")
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Site extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "site_id")
    private Integer id;

    private String title;

    private String description;

    private String heroImageUrl;

    private Integer locationId;

    @ManyToMany(mappedBy = "site", fetch = FetchType.LAZY)
    private Set<SiteLink> siteLinks = new HashSet<>();

    @OneToOne(mappedBy = "childSite",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private SiteHierarchy siteHierarchy;

    @OneToMany(mappedBy = "parentSite", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<SiteHierarchy> siteChild;
}
