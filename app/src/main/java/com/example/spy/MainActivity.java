package com.example.spy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.spy.models.Location;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageButton github, theme, help, settings;
    boolean nightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", true);

        github = findViewById(R.id.github_button);
        theme = findViewById(R.id.moon_button);
        help = findViewById(R.id.help_button);
        settings = findViewById(R.id.settings_button);
        github.setOnClickListener(this::openGithub);
        theme.setOnClickListener(this::switchTheme);
        help.setOnClickListener(this::openRules);
        settings.setOnClickListener(this::openSettings);

        LinearLayout start = findViewById(R.id.start_layout);
        start.setOnClickListener((v) -> startGame());
    }

    private void startGame() {
        if (Location.getLocations().size() == 0) {
            Toast.makeText(MainActivity.this, "Please choose locations in settings!", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(MainActivity.this, GameStartedPage.class);
        startActivity(intent);
    }

    private void switchTheme(View view) {
        nightMode = !nightMode;
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            editor = sharedPreferences.edit();
            editor.putBoolean("night", true);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            editor = sharedPreferences.edit();
            editor.putBoolean("night", false);
        }
        editor.apply();
    }

    private void openRules(View view){
        Intent intent = new Intent(this, RulesPage.class);
        startActivity(intent);
    }

    private void openSettings(View view){
        Intent intent = new Intent(this, SettingsPage.class);
        startActivity(intent);
    }

    private void openGithub(View view){
        String url = "https://github.com/VIP-KAZAKH-777/spy-game-android";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Follow the link");
        builder.setMessage("You are going to open github");
        builder.setPositiveButton("Confirm", (dialog, which) -> startActivity(intent));
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> {

        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}