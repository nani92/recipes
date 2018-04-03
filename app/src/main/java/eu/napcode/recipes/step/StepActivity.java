package eu.napcode.recipes.step;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;
import eu.napcode.recipes.R;

public class StepActivity extends AppCompatActivity {

    public static String STEP_ID_KEY = "step id ";
    public static String RECIPE_ID_KEY = "recipe id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        AndroidInjection.inject(this);

        StepFragment fragment = StepFragment.newInstance(
                getIntent().getIntExtra(STEP_ID_KEY, 0),
                getIntent().getIntExtra(RECIPE_ID_KEY, 0));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detailsContainer, fragment)
                .commit();
    }
}
