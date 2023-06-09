package com.example.spy.models;

import android.widget.TextView;

public class Settings {
    public static int PLAYERS_NUMBER = 5;
    public static int SPIES_NUMBER = 1;
    public static long TIME = 300_001;

    public static void minusPlayers(TextView text){
        if (Settings.PLAYERS_NUMBER > 2 && Settings.PLAYERS_NUMBER * 0.3 > Settings.SPIES_NUMBER) Settings.PLAYERS_NUMBER--;
        text.setText((String) Integer.toString(Settings.PLAYERS_NUMBER));
    }

    public static void plusPlayers(TextView text){
        if (Settings.PLAYERS_NUMBER < 100) Settings.PLAYERS_NUMBER++;
        text.setText((String) Integer.toString(Settings.PLAYERS_NUMBER));
    }

    public static void minusSpies(TextView text) {
        if (Settings.SPIES_NUMBER >= 2) Settings.SPIES_NUMBER--;
        text.setText((String) Integer.toString(Settings.SPIES_NUMBER));
    }

    public static void plusSpies(TextView text){
        if (Settings.PLAYERS_NUMBER * 0.3 > Settings.SPIES_NUMBER) Settings.SPIES_NUMBER++;
        text.setText((String) Integer.toString(Settings.SPIES_NUMBER));
    }

    public static void minusMinute(TextView text){
        if (Settings.TIME >= 120_000) Settings.TIME -= 60000;
        text.setText((String) Integer.toString((int) Settings.TIME / 1000 / 60));
    }

    public static void plusMinute(TextView text){
        if (Settings.TIME < 600_000) Settings.TIME += 60000;
        text.setText((String) Integer.toString((int) Settings.TIME / 1000 / 60));
    }
}
