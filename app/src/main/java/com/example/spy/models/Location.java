package com.example.spy.models;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.spy.Locations;
import com.example.spy.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Location {
    private static final ArrayList<String> selectedLocations = new ArrayList<>();
    private static final String[] places = new String[]{"Entertainment park", "Military base", "Hospital", "Shopping center", "Pub", "City hall", "Police station"};
    private static final boolean[] selectedPlaces = new boolean[places.length];
    private static final String[] countries = new String[]{"Japan", "China", "Kazakhstan", "USA", "Mexico", "Russia", "Germany", "Italy", "Spain", "Australia"};
    private static final boolean[] selectedCountries = new boolean[countries.length];
    private static final String[] transports = new String[]{"Bus", "Ship", "Car", "Bicycle", "Motorbike", "Scooter"};
    private static final boolean[] selectedTransports = new boolean[transports.length];
    private static final String[] rooms = new String[]{"Guest room", "Living room", "Hallway", "Kitchen", "Bedroom", "Attic"};
    private static final boolean[] selectedRooms = new boolean[rooms.length];

    public static void addLocation(String location){
        selectedLocations.add(location);
    }

    public static void selectLocation(int which, Context context){
        switch (which) {
            case 1:
                openDialog(places, selectedPlaces, "places", context);
                break;
            case 2:
                openDialog(countries, selectedCountries,"countries", context);
                break;
            case 3:
                openDialog(transports, selectedTransports,"transports", context);
                break;
            case 4:
                openDialog(rooms, selectedRooms, "rooms", context);
                break;
        }
    }

    public static ArrayList<String> getLocations() {
        return selectedLocations;
    }

    public static void removeLocation(String location) {
        selectedLocations.remove(location);
    }

    public static void clearSelectedLocations() {
        selectedLocations.clear();
    }

    private static void openDialog(String[] array, boolean[] selected, String title, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DIALOG);

        builder.setTitle("Choose " + title);
        builder.setCancelable(true);

        builder.setMultiChoiceItems(array, selected, (dialog, which, isChecked) -> {
            if (isChecked) {
                Location.addLocation(array[which]);
            }
            else {
                Location.removeLocation(array[which]);
            }
        })
                .setPositiveButton("Save", (dialog, which) -> dialog.dismiss())
                .setNeutralButton("Clear all", (dialog, which) -> {
            for (int i = 0; i < selected.length; ++i) {
                selected[i] = false;
                Location.clearSelectedLocations();
            }
        });
        builder.show();
    }
}
