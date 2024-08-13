package com.blayze.blayze.ui.journal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.blayze.blayze.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class JournalFragment extends Fragment {

    private TextView welcomeText;
    private TextView userName;
    private TextView todaysDate;
    private FloatingActionButton addEntryButton;

    private final ActivityResultLauncher<Intent> chooseEntryLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                    // Handle the result if needed
                }
            });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        welcomeText = view.findViewById(R.id.welcomeText);
        userName = view.findViewById(R.id.userName);
        todaysDate = view.findViewById(R.id.todaysDate);
        addEntryButton = view.findViewById(R.id.addEntryButton);

        addEntryButton.setOnClickListener(v -> showChooseEntryJournalActivity());

        updateTodaysDate();

        return view;
    }

    private void showChooseEntryJournalActivity() {
        Intent intent = new Intent(getActivity(), ChooseEntryJournal.class);
        chooseEntryLauncher.launch(intent);
    }

    private void updateTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd\nyyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(calendar.getTime());
        todaysDate.setText(formattedDate);
    }
}
