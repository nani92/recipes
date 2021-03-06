package eu.napcode.recipes.dao.step;

import java.util.ArrayList;
import java.util.List;

import eu.napcode.recipes.model.Step;

public class StepMapper {

    public static List<Step> toSteps(List<StepEntity> stepEntities) {

        if (stepEntities == null || stepEntities.isEmpty()) {
            return new ArrayList<>();
        }

        List<Step> steps = new ArrayList<>();

        for(StepEntity stepEntity : stepEntities) {
            steps.add(StepMapper.toStep(stepEntity));
        }

        return steps;
    }

    public static Step toStep(StepEntity stepEntity) {

        if (stepEntity == null) {
            return null;
        }

        Step step = new Step();
        step.setId(stepEntity.getId());
        step.setShortDescription(stepEntity.getShortDescription());
        step.setDescription(stepEntity.getDescription());
        step.setVideoURL(stepEntity.getVideoUrl());
        step.setThumbnailURL(stepEntity.getThumbnailUrl());

        return step;
    }

    public static StepEntity toStepEntity(Step step, int recipeId) {

        if (step == null) {
            return null;
        }

        StepEntity stepEntity = new StepEntity();
        stepEntity.setId(step.getId());
        stepEntity.setRecipeId(recipeId);
        stepEntity.setDescription(step.getDescription());
        stepEntity.setShortDescription(step.getShortDescription());
        stepEntity.setVideoUrl(step.getVideoURL());
        stepEntity.setThumbnailUrl(step.getThumbnailURL());

        return stepEntity;
    }
}
