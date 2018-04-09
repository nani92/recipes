package eu.napcode.recipes.ui.recipes;

import android.content.ComponentName;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
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

    @Rule
    public IntentsTestRule<RecipesActivity> recipesActivityRule =
            new IntentsTestRule<>(RecipesActivity.class);

    @Before
    public void registerIdlingResource() {
        idlingResource = recipesActivityRule.getActivity().idlingResource;
    }

    @Test
    public void clickOnItemOpensDetails() {
        onView(withId(R.id.recipesRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, RecipesViewAction.clickChildViewWithId(R.id.recipeConstraintLayout)));


        intended(hasComponent(new ComponentName(getTargetContext(), RecipeDetailsActivity.class.getName())));
    }
}