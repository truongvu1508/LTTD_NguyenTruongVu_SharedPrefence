package com.example.sharedpreference_nguyentruongvu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText etNote;
    private Button btnSave;
    private TextView tvNoteContent, tvSavedTime;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyNotes";
    private static final String KEY_NOTE = "note";
    private static final String KEY_TIMESTAMP = "timestamp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNote = findViewById(R.id.et_note);
        btnSave = findViewById(R.id.btn_save);
        tvNoteContent = findViewById(R.id.tv_note_content);
        tvSavedTime = findViewById(R.id.tv_saved_time);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        String savedNote = sharedPreferences.getString(KEY_NOTE, "");
        String savedTimestamp = sharedPreferences.getString(KEY_TIMESTAMP, "");

        tvNoteContent.setText(savedNote);
        tvSavedTime.setText(savedTimestamp.isEmpty() ? "" : "Lưu vào: " + savedTimestamp);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = etNote.getText().toString();
                String timestamp = getCurrentTimestamp();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NOTE, note);
                editor.putString(KEY_TIMESTAMP, timestamp);
                editor.apply();

                tvNoteContent.setText(note);
                tvSavedTime.setText("Lưu vào: " + timestamp);
            }
        });
    }

    private String getCurrentTimestamp() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }
}