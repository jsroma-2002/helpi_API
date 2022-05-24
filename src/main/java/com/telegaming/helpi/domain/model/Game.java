package com.telegaming.helpi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

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

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "game")
    private Set<TrainingMaterial> trainingMaterials= new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "game")
    private Set<Coach> coaches= new HashSet<>();

    public Game() {
    }

    public Game(String gameName, String description, String coverUri) {
        this.gameName = gameName;
        this.description = description;
        this.coverUri = coverUri;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
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

    public Set<Coach> getCoaches() {
        return coaches;
    }

    public Set<TrainingMaterial> getTrainingMaterials() {
        return trainingMaterials;
    }
}
