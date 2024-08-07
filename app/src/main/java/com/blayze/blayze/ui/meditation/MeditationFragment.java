package com.blayze.blayze.ui.meditation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.blayze.blayze.databinding.FragmentMeditationBinding;

public class MeditationFragment extends Fragment{
    private FragmentMeditationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MeditationViewModel meditationViewModel =
                new ViewModelProvider(this).get(MeditationViewModel.class);

        binding = FragmentMeditationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMeditation;
        meditationViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
