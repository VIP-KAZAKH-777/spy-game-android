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
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean nightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", true);

        ImageView github, theme, help, settings;
        github = findViewById(R.id.github_button);
        theme = findViewById(R.id.moon_button);
        help = findViewById(R.id.help_button);
        settings = findViewById(R.id.settings_button);
        github.setOnClickListener(this::openGithub);
        theme.setOnClickListener(this::switchTheme);
        help.setOnClickListener(this::openRules);
        settings.setOnClickListener(this::openSettings);
    }

    public void switchTheme(View view) {
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

    public void openRules(View view){
        Intent intent = new Intent(this, RulesPage.class);
        startActivity(intent);
    }

    public void openSettings(View view){
        Intent intent = new Intent(this, SettingsPage.class);
        startActivity(intent);
    }

    public void openGithub(View view){
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