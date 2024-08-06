package com.blayze.blayze.ui.journal;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import com.blayze.blayze.R;

public class ChooseEntryJournal extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_choice);

        Button promptButton = findViewById(R.id.promptButton);
        Button freeWriteButton = findViewById(R.id.freeWriteButton);

        promptButton.setOnClickListener(v -> startEntryActivity(true));
        freeWriteButton.setOnClickListener(v -> startEntryActivity(false));
    }

    private void startEntryActivity(boolean usePrompt) {
        Intent intent = new Intent(this, EntryAction.class);
        intent.putExtra("usePrompt", usePrompt);
        startActivity(intent);
        setResult(AppCompatActivity.RESULT_OK);
        finish();
    }
}
