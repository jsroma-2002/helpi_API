package com.telegaming.helpi.resource.training;

import javax.validation.constraints.Size;

public class SaveTrainingMaterialResource {

    @Size(max = 50)
    private String title;
    private String trainingDescription;
    private String trainingCoverUri;
    private Float value;

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

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
