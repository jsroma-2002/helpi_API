package com.telegaming.helpi.resource.game;

public class GameResource {

    private Long gameId;
    private String name;
    private String description;
    private String coverUri;

    public Long getId() {
        return gameId;
    }

    public void setId(Long id) {
        this.gameId = id;
    }

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
