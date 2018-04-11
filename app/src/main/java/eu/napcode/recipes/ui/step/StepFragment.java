package eu.napcode.recipes.ui.step;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import eu.napcode.recipes.R;
import eu.napcode.recipes.databinding.FragmentStepBinding;
import eu.napcode.recipes.dependency.modules.viewmodel.ViewModelFactory;
import eu.napcode.recipes.model.Step;

public class StepFragment extends Fragment {

    @Inject
    ViewModelFactory viewModelFactory;

    public static final String STEP_ID_KEY = "step id";
    public static final String RECIPE_ID_KEY = "recipe id";

    private StepViewModel viewModel;
    private FragmentStepBinding binding;
    private SimpleExoPlayer simpleExoPlayer;

    public static StepFragment newInstance(int stepId, int recipeId) {
        Bundle bundle = new Bundle();
        bundle.putInt(STEP_ID_KEY, stepId);
        bundle.putInt(RECIPE_ID_KEY, recipeId);

        StepFragment fragment = new StepFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AndroidSupportInjection.inject(this);

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(StepViewModel.class);

        viewModel.setStepInfo(getArguments().getInt(STEP_ID_KEY),
                getArguments().getInt(RECIPE_ID_KEY));

        viewModel.getStep().observe(this,
                step -> displayStepDetails(step));
        setupNextStepButton();
    }

    void displayStepDetails(Step step) {

        if (step == null) {
            Snackbar.make(this.binding.contraintLayout, R.string.error, Snackbar.LENGTH_LONG).show();

            return;
        }

        this.binding.titleTextView.setText(step.getShortDescription());
        displayVideo(step);
        this.binding.descriptionTextView.setText(step.getDescription());
    }

    private void displayVideo(Step step) {

        if (step.getVideoURL().isEmpty()) {
            return;
        }

        initializePlayer();

        MediaSource mediaSource = new ExtractorMediaSource(
                Uri.parse(step.getVideoURL()),
                getDataSourceFactory(),
                new DefaultExtractorsFactory(),
                0, null, null, null, 0);

        simpleExoPlayer.prepare(mediaSource);

        this.binding.videoPlayer.setVisibility(View.VISIBLE);
    }

    private void initializePlayer() {
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());
        binding.videoPlayer.setPlayer(simpleExoPlayer);
    }

    private DefaultDataSourceFactory getDataSourceFactory() {
        return new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), getContext().getString(R.string.app_name)));
    }

    private void setupNextStepButton() {

        if (viewModel.hasNextStep()) {

            StepFragment fragment = newInstance(getArguments().getInt(STEP_ID_KEY) + 1,
                    getArguments().getInt(RECIPE_ID_KEY));

            this.binding.nextStepButton.setOnClickListener(view -> {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.detailsContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            });
        } else {
            this.binding.nextStepButton.setEnabled(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
        }
    }
}
