package com.telegaming.helpi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

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

    private String communityCover;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "player_communities",
            joinColumns = @JoinColumn(name = "community_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Player> memberPlayers = new HashSet<>();

    public Community() {
    }

    public Community(String communityTitle, String communityDescription, String communityCover) {
        this.communityTitle = communityTitle;
        this.communityDescription = communityDescription;
        this.communityCover = communityCover;
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

    public Set<Player> getMemberPlayers() {
        return memberPlayers;
    }

    public String getCommunityCover() {
        return communityCover;
    }

    public void setCommunityCover(String communityCover) {
        this.communityCover = communityCover;
    }
}
