package com.blayze.blayze.ui.water;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.blayze.blayze.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WaterFragment extends Fragment {

    private WaterViewModel waterViewModel;

    private TextView waterGoalText;
    private ProgressBar waterProgress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_water, container, false);

        EditText waterGoalInput;
        FloatingActionButton addWaterEntry;
        waterGoalText = root.findViewById(R.id.waterGoalText);
        waterGoalInput = root.findViewById(R.id.waterGoalInput);
        waterProgress = root.findViewById(R.id.waterProgress);
        addWaterEntry = root.findViewById(R.id.addWaterEntry);

        waterViewModel = new ViewModelProvider(this).get(WaterViewModel.class);

        // Observe the LiveData from ViewModel
        waterViewModel.getWaterGoal().observe(getViewLifecycleOwner(), goal -> {
            String waterGoalTextString = getString(R.string.water_goal_text, goal);
            waterGoalText.setText(waterGoalTextString);
            waterProgress.setMax(goal);
        });

        waterViewModel.getCurrentWaterIntake().observe(getViewLifecycleOwner(), waterProgress::setProgress);

        // Set up the EditText to update the water goal
        waterGoalInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int goal = Integer.parseInt(s.toString());
                    waterViewModel.setWaterGoal(goal);
                } catch (NumberFormatException e) {
                    // Handle the error if input is not a valid number
                }
            }
        });

        // Set up the FAB to add water intake
        addWaterEntry.setOnClickListener(v -> waterViewModel.addWaterIntake());

        return root;
    }
}
