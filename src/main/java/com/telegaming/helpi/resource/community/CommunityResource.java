package com.telegaming.helpi.resource.community;

public class CommunityResource {

    private Long communityId;
    private String communityTitle;
    private String communityDescription;
    private String communityCover;

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

    public String getCommunityCover() {
        return communityCover;
    }

    public void setCommunityCover(String communityCover) {
        this.communityCover = communityCover;
    }
}
