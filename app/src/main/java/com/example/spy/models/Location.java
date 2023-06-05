package com.example.spy.models;

import java.util.ArrayList;

public class Location {
    private ArrayList<String> selectedLocations;

    Location(){
        selectedLocations = new ArrayList<>();

        /*
        Places: Entertainment park, Military base, Hospital, Shopping center, Pub, City hall, Police station
        Countries: Japan, China, Kazakhstan, USA, Mexico, Russia, Germany, Italy, Spain, Australia
        Transports: Bus, Ship, Car, Bicycle, Motorbike, Scooter,
        Rooms: Guest room, Living room, Hallway, Kitchen, Bedroom, Attic
         */
    }

    public void chooseRandom(){

    }

    public void addLocation(String location){
        selectedLocations.add(location);
    }
}
