package eu.napcode.recipes.step;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;
import eu.napcode.recipes.R;
import eu.napcode.recipes.model.Step;

public class StepActivity extends AppCompatActivity {

    public static String STEP_KEY = "step";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        AndroidInjection.inject(this);

        StepFragment fragment = StepFragment.newInstance(getStepFromIntent());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.stepContainer, fragment)
                .commit();
    }

    private Step getStepFromIntent() {
        return getIntent().getParcelableExtra(STEP_KEY);
    }
}
