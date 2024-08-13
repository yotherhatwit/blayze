package com.blayze.blayze.ui.journal;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import com.blayze.blayze.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EntryAction extends AppCompatActivity {

    private boolean usePrompt;
    private TextView promptTextView;
    private EditText entryEditText;
    private Button saveButton;
    private Prompts promptsRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_entry);

        promptTextView = findViewById(R.id.promptTextView);
        entryEditText = findViewById(R.id.entryEditText);
        saveButton = findViewById(R.id.saveButton);

        promptsRepository = new Prompts(getApplication());
        usePrompt = getIntent().getBooleanExtra("usePrompt", false);

        if (usePrompt) {
            String prompt = promptsRepository.getRandomPrompt();
            promptTextView.setText(prompt);
            promptTextView.setVisibility(View.VISIBLE);
        } else {
            promptTextView.setVisibility(View.GONE);
        }

        saveButton.setOnClickListener(v -> saveEntry());
    }

    private void saveEntry() {
        String entryText = entryEditText.getText().toString();
        if (!entryText.isEmpty()) {
            File file = new File(getFilesDir(), "journal_entries.txt");
            try (FileOutputStream fos = new FileOutputStream(file, true)) { // Append mode
                String entry = entryText + "\n" + System.currentTimeMillis() + "\n";
                fos.write(entry.getBytes());
                fos.flush(); // Ensure data is written to the file
                finish(); // Close activity after saving
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
