package com.blayze.blayze.ui.home;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.blayze.blayze.R;
import com.blayze.blayze.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel;
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel.getText().observe(getViewLifecycleOwner(), binding.welcomeText::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        binding.journalButton.setOnClickListener(v -> navController.navigate(R.id.action_homeFragment_to_journalFragment));
        binding.waterButton.setOnClickListener(v -> navController.navigate(R.id.action_homeFragment_to_waterFragment));
        binding.insightsButton.setOnClickListener(v -> navController.navigate(R.id.action_homeFragment_to_insightsFragment));
        binding.sleepButton.setOnClickListener(v -> navController.navigate(R.id.action_homeFragment_to_sleepFragment));
        binding.meditationButton.setOnClickListener(v -> navController.navigate(R.id.action_homeFragment_to_meditationFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}