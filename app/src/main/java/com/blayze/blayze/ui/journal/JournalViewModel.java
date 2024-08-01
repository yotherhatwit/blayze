package com.blayze.blayze.ui.journal;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class JournalViewModel extends AndroidViewModel {

    private MutableLiveData<String> prompt;
    private Prompts promptsRepository;

    public JournalViewModel(@NonNull Application application) {
        super(application);
        promptsRepository = new Prompts(application);
        prompt = new MutableLiveData<>();
        loadRandomPrompt();
    }

    public LiveData<String> getPrompt() {
        return prompt;
    }

    public void setPrompt(String newPrompt) {
        prompt.setValue(newPrompt);
    }

    private void loadRandomPrompt() {
        String randomPrompt = promptsRepository.getRandomPrompt();
        prompt.setValue(randomPrompt);
    }
}
