package com.telegaming.helpi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    @NotNull
    private String gameName;
    @NotNull
    private String description;
    @NotNull
    private String coverUri;

    public Game() {
    }

    public Game(String gameName, String description, String coverUri) {
        this.gameName = gameName;
        this.description = description;
        this.coverUri = coverUri;
    }

    public long getId() {
        return gameId;
    }

    public void setId(long id) {
        this.gameId = id;
    }

    public String getName() {
        return gameName;
    }

    public void setName(String name) {
        this.gameName = name;
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
