package com.blayze.blayze.ui.water;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.blayze.blayze.databinding.FragmentWaterBinding;

public class WaterFragment extends Fragment{
    private FragmentWaterBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WaterViewModel waterViewModel =
                new ViewModelProvider(this).get(WaterViewModel.class);

        binding = FragmentWaterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textWater;
        waterViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
