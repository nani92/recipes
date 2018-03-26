package eu.napcode.recipes.step;

import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import javax.inject.Inject;

import eu.napcode.recipes.model.Step;

public class StepViewModel extends ViewModel {

    private Step step;

    @Inject
    public StepViewModel() {
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public String getVideoUrl() {
        return step.getVideoURL();
    }

    public String getTitle() {
        return step.getShortDescription();
    }

    public String getDescription() {
        return step.getDescription();
    }

    public boolean hasNoVideo() {
        return TextUtils.isEmpty(step.getVideoURL());
    }
}
