package com.example.spy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.spy.models.Settings;

public class SettingsPage extends AppCompatActivity {

    private TextView playersNumber;
    private TextView spiesNumber;
    private TextView minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        playersNumber = findViewById(R.id.players_number);
        spiesNumber = findViewById(R.id.spies_number);
        minutes = findViewById(R.id.minutes);

        playersNumber.setText(Integer.toString(Settings.PLAYERS_NUMBER));
        spiesNumber.setText(Integer.toString(Settings.SPIES_NUMBER));
        minutes.setText(Integer.toString((int) Settings.TIME / 1000 / 60));
    }

    public void exitSettings(View view){
        finish();
    }

    public void openLocations(View view){
        Intent intent = new Intent(this, Locations.class);
        startActivity(intent);
    }

    public void minusPlayers(View view){
        if (Settings.PLAYERS_NUMBER > 2) Settings.PLAYERS_NUMBER--;
        playersNumber.setText(Integer.toString(Settings.PLAYERS_NUMBER));
    }

    public void plusPlayers(View view){
        if (Settings.PLAYERS_NUMBER < 100) Settings.PLAYERS_NUMBER++;
        playersNumber.setText(Integer.toString(Settings.PLAYERS_NUMBER));
    }

    public void minusSpies(View view) {
        if (Settings.SPIES_NUMBER >= 2) Settings.SPIES_NUMBER--;
        spiesNumber.setText(Integer.toString(Settings.SPIES_NUMBER));
    }

    public void plusSpies(View view){
        if (Settings.PLAYERS_NUMBER * 0.3 > Settings.SPIES_NUMBER) Settings.SPIES_NUMBER++;
        spiesNumber.setText(Integer.toString(Settings.SPIES_NUMBER));
    }

    public void minusMinute(View view){
        if (Settings.TIME >= 120_000) Settings.TIME -= 60000;
        minutes.setText(Integer.toString((int) Settings.TIME / 1000 / 60));
    }

    public void plusMinute(View view){
        if (Settings.TIME < 600_000) Settings.TIME += 60000;
        minutes.setText(Integer.toString((int) Settings.TIME / 1000 / 60));
    }
}