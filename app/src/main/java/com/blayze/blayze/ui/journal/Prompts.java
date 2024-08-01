package com.blayze.blayze.ui.journal;

import android.app.Application;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Prompts {

    private List<String> promptsList;
    private final Application application;

    public Prompts(Application application) {
        this.application = application;
        loadPromptsFromJson();
    }

    private void loadPromptsFromJson() {
        promptsList = new ArrayList<>();
        try {
            InputStream is = application.getAssets().open("prompts.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(jsonBuilder.toString());
            JSONArray promptsArray = jsonObject.getJSONArray("prompts");

            for (int i = 0; i < promptsArray.length(); i++) {
                promptsList.add(promptsArray.getString(i));
            }
        } catch (IOException | JSONException e) {
            Log.e("Prompts", "Error reading JSON", e);
        }
    }

    public String getRandomPrompt() {
        if (promptsList.isEmpty()) {
            return "No prompts available";
        }
        Random random = new Random();
        int index = random.nextInt(promptsList.size());
        return promptsList.get(index);
    }
}
