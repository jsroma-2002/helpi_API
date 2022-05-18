package com.telegaming.helpi.resource.game;

import javax.validation.constraints.Size;

public class SaveGameResource {

    @Size(max = 50)
    private String name;
    private String description;
    private String coverUri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverUri() {
        return coverUri;
    }

    public void setCoverUri(String coverUri) {
        this.coverUri = coverUri;
    }
}

