package eu.napcode.recipes.ui.step;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import eu.napcode.recipes.model.Step;
import eu.napcode.recipes.repository.recipes.RecipesRepository;

public class StepViewModel extends ViewModel {

    private RecipesRepository recipesRepository;

    private int recipeId;
    private int stepId;

    private MutableLiveData<Step> stepMutableLiveData = new MutableLiveData<>();

    @Inject
    public StepViewModel(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public void setStepInfo(int stepId, int recipeId) {
        this.recipeId = recipeId;
        this.stepId = stepId;
    }

    public LiveData<Step> getStep() {
        stepMutableLiveData.postValue(recipesRepository.getStepForRecipe(this.recipeId, this.stepId));

        return stepMutableLiveData;
    }

    public boolean hasNextStep() {
        return recipesRepository.hasNextStepForRecipe(recipeId, stepId);
    }
}
