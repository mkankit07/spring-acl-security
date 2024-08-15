package com.example.springaclsecurity.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "short_links")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ShortLink extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "short_link_id")
    private Integer id;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "short_url")
    private String shortUrl;

    @Column(name = "long_url")
    private String longUrl;

    @Column(name = "separator")
    private String separator;

    @Column(name = "start_datetime")
    private LocalDateTime startDateTime;

    @Column(name = "end_datetime")
    private LocalDateTime endDateTime;

    @Column(name = "publish_status")
    private String publishStatus;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


    @ManyToMany(mappedBy = "shortLink", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<SiteLink> siteLinks = new HashSet<>();

    public void addSite(Site site){
        SiteLink siteLink = SiteLink.builder().shortLink(this).site(site).build();
        this.siteLinks.add(siteLink);
    }

}