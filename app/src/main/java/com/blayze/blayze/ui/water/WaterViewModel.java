package com.blayze.blayze.ui.water;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class WaterViewModel extends AndroidViewModel {

    private static final String PREFS_NAME = "WaterPrefs";
    private static final String KEY_WATER_GOAL = "waterGoal";
    private static final String KEY_WATER_INTAKE = "waterIntake";

    private MutableLiveData<Integer> waterGoal;
    private MutableLiveData<Integer> currentWaterIntake;
    private SharedPreferences sharedPreferences;

    public WaterViewModel(@NonNull Application application) {
        super(application);
        sharedPreferences = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        waterGoal = new MutableLiveData<>(sharedPreferences.getInt(KEY_WATER_GOAL, 8)); // default goal is 8
        currentWaterIntake = new MutableLiveData<>(sharedPreferences.getInt(KEY_WATER_INTAKE, 0));
    }

    public LiveData<Integer> getWaterGoal() {
        return waterGoal;
    }

    public LiveData<Integer> getCurrentWaterIntake() {
        return currentWaterIntake;
    }

    public void setWaterGoal(int goal) {
        waterGoal.setValue(goal);
        sharedPreferences.edit().putInt(KEY_WATER_GOAL, goal).apply();
    }

    public void addWaterIntake() {
        int newIntake = currentWaterIntake.getValue() + 1;
        currentWaterIntake.setValue(newIntake);
        sharedPreferences.edit().putInt(KEY_WATER_INTAKE, newIntake).apply();
    }
}
