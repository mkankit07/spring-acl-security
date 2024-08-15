package com.example.springaclsecurity.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "site_links")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SiteLink extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "site_link_id")
    private Integer siteLinkId;

    @ManyToOne
    @JoinColumn(name = "site_id", referencedColumnName = "site_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Site site;

    @ManyToOne
    @JoinColumn(name = "short_link_id", referencedColumnName = "short_link_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private ShortLink shortLink;

}