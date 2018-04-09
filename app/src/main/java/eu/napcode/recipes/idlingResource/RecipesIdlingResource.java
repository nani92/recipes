package eu.napcode.recipes.idlingResource;

import android.support.test.espresso.IdlingResource;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import eu.napcode.recipes.model.Recipe;
import eu.napcode.recipes.repository.Resource;

public class RecipesIdlingResource implements IdlingResource {

    private ResourceCallback resourceCallback;
    private AtomicBoolean isIdleNow = new AtomicBoolean(false);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return isIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback = resourceCallback;
    }

    public void setIdleState(boolean state) {
        isIdleNow.set(state);
    }
}
