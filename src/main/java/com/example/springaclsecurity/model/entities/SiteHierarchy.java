package com.example.springaclsecurity.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "site_hierarchy")
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SiteHierarchy extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "site_hierarchy_id")
    private Integer siteHierarchyId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "site_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_child_site")
    )
    private  Site childSite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "parent_site_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "site_id")
    )
    private Site parentSite;
}