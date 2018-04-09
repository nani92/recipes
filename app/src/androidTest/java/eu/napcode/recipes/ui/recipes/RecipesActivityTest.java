package eu.napcode.recipes.ui.recipes;

import android.content.ComponentName;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import eu.napcode.recipes.R;
import eu.napcode.recipes.RecipesViewAction;
import eu.napcode.recipes.ui.recipedetails.RecipeDetailsActivity;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static android.support.test.espresso.intent.Intents.intended;

@RunWith(AndroidJUnit4.class)
public class RecipesActivityTest {

    IdlingResource idlingResource;
    RecipesActivity recipesActivity;

    @Rule
    public IntentsTestRule<RecipesActivity> recipesActivityRule =
            new IntentsTestRule<>(RecipesActivity.class);

    public void registerIdlingResource() {
        recipesActivity = recipesActivityRule.getActivity();
        idlingResource = recipesActivityRule.getActivity().idlingResource;
    }

    @Test
    public void clickOnItemOpensDetails() {
        onView(withId(R.id.recipesRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, RecipesViewAction.clickChildViewWithId(R.id.recipeConstraintLayout)));


        intended(hasComponent(new ComponentName(getTargetContext(), RecipeDetailsActivity.class.getName())));
    }
}