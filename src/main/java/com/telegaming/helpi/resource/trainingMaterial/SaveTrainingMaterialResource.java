package com.telegaming.helpi.resource.trainingMaterial;

import com.telegaming.helpi.domain.model.Game;

import javax.validation.constraints.Size;

public class SaveTrainingMaterialResource {

    @Size(max = 50)
    private String title;
    private String trainingDescription;
    private String trainingCoverUri;

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

}
