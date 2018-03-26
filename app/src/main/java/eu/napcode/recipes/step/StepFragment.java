package eu.napcode.recipes.step;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class StepFragment extends Fragment{

    @Inject
    ViewModelFactory viewModelFactory;

    public static final String STEP_KEY = "step";
    private StepViewModel viewModel;
    private FragmentStepBinding binding;
    private SimpleExoPlayer simpleExoPlayer;

    public static StepFragment newInstance(Step step) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STEP_KEY, step);

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

        viewModel.setStep(getArguments().getParcelable(STEP_KEY));

        displayDetails();
        setupNextStepButton();
    }

    void displayDetails() {
        this.binding.titleTextView.setText(this.viewModel.getTitle());
        displayVideo();
        this.binding.descriptionTextView.setText(this.viewModel.getDescription());
    }

    private void displayVideo() {

        if (this.viewModel.hasNoVideo()) {
            return;
        }

        initializePlayer();

        MediaSource mediaSource = new ExtractorMediaSource(
                Uri.parse(viewModel.getVideoUrl()),
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
        this.binding.nextStepButton.setOnClickListener(view -> {
            
        });
    }
}
