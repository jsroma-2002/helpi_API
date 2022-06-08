package com.telegaming.helpi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "communities")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityId;

    @NotNull
    private String communityTitle;

    @NotNull
    private String communityDescription;

    public Community() {
    }

    public Community(String communityTitle, String communityDescription) {
        this.communityTitle = communityTitle;
        this.communityDescription = communityDescription;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getCommunityTitle() {
        return communityTitle;
    }

    public void setCommunityTitle(String communityTitle) {
        this.communityTitle = communityTitle;
    }

    public String getCommunityDescription() {
        return communityDescription;
    }

    public void setCommunityDescription(String communityDescription) {
        this.communityDescription = communityDescription;
    }
}
