package com.telegaming.helpi.resource.trainingMaterial;

import com.telegaming.helpi.domain.model.Game;

public class TrainingMaterialResource {

    private Long trainingMaterialId;
    private String title;
    private String trainingDescription;
    private String trainingCoverUri;
    private Game game;

    public Long getTrainingMaterialId() {
        return trainingMaterialId;
    }

    public void setTrainingMaterialId(Long trainingMaterialId) {
        this.trainingMaterialId = trainingMaterialId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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