package eu.napcode.recipes.ui.recipedetails;

import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import eu.napcode.recipes.R;
import eu.napcode.recipes.ui.ingredients.IngredientsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailsActivityTest {

    @Rule
    public IntentsTestRule<RecipeDetailsActivity> recipeDetailsActivityRule =
            new IntentsTestRule<>(RecipeDetailsActivity.class);

    @Test
    public void clickOnItemOpensIngredients() {
        onView(withId(R.id.recipeDetailsRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        intended(hasComponent(IngredientsActivity.class.getName()));
    }
}