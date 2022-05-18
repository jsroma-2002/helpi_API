package com.telegaming.helpi.resource.trainingMaterial;

public class TrainingMaterialResource {

    private Long trainingMaterialId;
    private String title;
    private String trainingDescription;
    private String trainingCoverUri;

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
}
