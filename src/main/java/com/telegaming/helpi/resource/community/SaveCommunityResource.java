package com.telegaming.helpi.resource.community;

import javax.validation.constraints.Size;

public class SaveCommunityResource {

    @Size(max = 50)
    private String communityTitle;
    private String communityDescription;

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
