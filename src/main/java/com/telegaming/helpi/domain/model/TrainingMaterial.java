package com.telegaming.helpi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "trainings")
public class TrainingMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainingMaterialId;
    @NotNull
    private String title;
    @NotNull
    private String trainingDescription;
    @NotNull
    private String trainingCoverUri;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "game_id", referencedColumnName = "gameId")
    private Game game;

    public TrainingMaterial() {
    }

    public TrainingMaterial(String title, String trainingDescription, String trainingCoverUri) {
        this.title = title;
        this.trainingDescription = trainingDescription;
        this.trainingCoverUri = trainingCoverUri;
    }

    public Long getTrainingMaterialId() {
        return trainingMaterialId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTrainingMaterialId(Long trainingMaterialId) {
        this.trainingMaterialId = trainingMaterialId;
    }

    public String getTrainingDescription() {
        return trainingDescription;
    }

    public void setTrainingDescription(String trainingDescription) {
        this.trainingDescription = trainingDescription;
    }

    public String getTrainingCoverUri() {
        return trainingCoverUri;
    }

    public void setTrainingCoverUri(String trainingCoverUri) {
        this.trainingCoverUri = trainingCoverUri;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
