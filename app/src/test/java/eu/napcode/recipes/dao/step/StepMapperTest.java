package eu.napcode.recipes.dao.step;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import eu.napcode.recipes.model.Step;

@RunWith(MockitoJUnitRunner.class)
public class StepMapperTest {

    @Test
    public void testEntityToStepIdMapping() {
        StepEntity stepEntity = getStepEntity();

        Assert.assertEquals(stepEntity.getId(), StepMapper.toStep(stepEntity).getId());
    }

    @Test
    public void testEntityToStepShortDescriptionMapping() {
        StepEntity stepEntity = getStepEntity();

        Assert.assertEquals(stepEntity.getShortDescription(), StepMapper.toStep(stepEntity).getShortDescription());
    }

    @Test
    public void testEntityToStepDescriptionMapping() {
        StepEntity stepEntity = getStepEntity();

        Assert.assertEquals(stepEntity.getDescription(), StepMapper.toStep(stepEntity).getDescription());
    }

    @Test
    public void testEntityToStepVideoUrlMapping() {
        StepEntity stepEntity = getStepEntity();

        Assert.assertEquals(stepEntity.getVideoUrl(), StepMapper.toStep(stepEntity).getVideoURL());
    }

    @Test
    public void testEntityToStepThumbnailMapping() {
        StepEntity stepEntity = getStepEntity();

        Assert.assertEquals(stepEntity.getThumbnailUrl(), StepMapper.toStep(stepEntity).getThumbnailURL());
    }

    @Test
    public void testEntityToStepNullMap() {
        Assert.assertEquals(null, StepMapper.toStep(null));
    }

    @Test
    public void testStepToEntityIdMapping() {
        Step step = getStep();

        Assert.assertEquals(step.getId(), StepMapper.toStepEntity(step, 0).getId());
    }

    @Test
    public void testStepToEntityRecipeIdMapping() {
        Step step = getStep();
        int recipeId = 5;

        Assert.assertEquals(recipeId, StepMapper.toStepEntity(step, recipeId).getRecipeId());
    }

    @Test
    public void testStepToEntityDescriptionMapping() {
        Step step = getStep();

        Assert.assertEquals(step.getDescription(), StepMapper.toStepEntity(step, 0).getDescription());
    }

    @Test
    public void testStepToEntityShortDescriptionMapping() {
        Step step = getStep();

        Assert.assertEquals(step.getShortDescription(), StepMapper.toStepEntity(step, 0).getShortDescription());
    }

    @Test
    public void testStepToEntityVideoUrlMapping() {
        Step step = getStep();

        Assert.assertEquals(step.getVideoURL(), StepMapper.toStepEntity(step, 0).getVideoUrl());
    }

    @Test
    public void testStepToEntityThumbnailMapping() {
        Step step = getStep();

        Assert.assertEquals(step.getThumbnailURL(), StepMapper.toStepEntity(step, 0).getThumbnailUrl());
    }

    @Test
    public void testStepToEntityNullMap() {
        Assert.assertEquals(null, StepMapper.toStepEntity(null, 0));
    }

    @Test
    public void testNullToStepsMap() {
        Assert.assertEquals(0, StepMapper.toSteps(null).size());
    }

    @Test
    public void testEntitiesToStepsMap() {
        List<StepEntity> stepEntities = new ArrayList<>();
        stepEntities.add(getStepEntity());
        stepEntities.add(getStepEntity());

        Assert.assertEquals(stepEntities.size(), StepMapper.toSteps(stepEntities).size());
    }

    public StepEntity getStepEntity() {
        StepEntity stepEntity = new StepEntity();
        stepEntity.setId(0);
        stepEntity.setRecipeId(0);
        stepEntity.setDescription("description");
        stepEntity.setShortDescription("short description");
        stepEntity.setVideoUrl("video url");
        stepEntity.setThumbnailUrl("thumbnail");

        return stepEntity;
    }

    public Step getStep() {
        Step step = new Step();
        step.setId(0);
        step.setDescription("description");
        step.setShortDescription("short description");
        step.setVideoURL("video url");
        step.setThumbnailURL("thumbnail");

        return step;
    }
}