package com.example.spy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.spy.models.Settings;

public class SettingsPage extends AppCompatActivity {

    private TextView playersNumber, spiesNumber, minutes;
    private ImageButton minusPl, minusSp, minusTm, plusPl, plusSp, plusTm;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        playersNumber = findViewById(R.id.players_number);
        spiesNumber = findViewById(R.id.spies_number);
        minutes = findViewById(R.id.minutes);
        playersNumber.setText(Integer.toString(Settings.PLAYERS_NUMBER));
        spiesNumber.setText(Integer.toString(Settings.SPIES_NUMBER));
        minutes.setText(Integer.toString((int) Settings.TIME / 1000 / 60));

        minusPl = findViewById(R.id.minus_players);
        plusPl = findViewById(R.id.plus_players);
        minusSp = findViewById(R.id.minus_spies);
        plusSp = findViewById(R.id.plus_spies);
        minusTm = findViewById(R.id.minus_time);
        plusTm = findViewById(R.id.plus_time);
        minusPl.setOnClickListener((v) -> Settings.minusPlayers(playersNumber));
        plusPl.setOnClickListener((v) -> Settings.plusPlayers(playersNumber));
        minusSp.setOnClickListener((v) -> Settings.minusSpies(spiesNumber));
        plusSp.setOnClickListener((v) -> Settings.plusSpies(spiesNumber));
        minusTm.setOnClickListener((v) -> Settings.minusMinute(minutes));
        plusTm.setOnClickListener((v) -> Settings.plusMinute(minutes));

        LinearLayout locationsBtn = findViewById(R.id.location_layout);
        LinearLayout saveBtn = findViewById(R.id.save_layout);
        locationsBtn.setOnClickListener(this::openLocations);
        saveBtn.setOnClickListener(this::exitSettings);
    }

    public void exitSettings(View view){
        finish();
    }

    public void openLocations(View view){
        Intent intent = new Intent(this, Locations.class);
        startActivity(intent);
    }
}