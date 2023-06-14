package com.example.spy.generators;

import com.example.spy.R;
import com.example.spy.models.KolodaCard;
import com.example.spy.models.Location;
import com.example.spy.models.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardGenerator {
    private int spies;
    private List<Integer> spiesPos;
    private final boolean[] plType;

    public CardGenerator() { //basic constructor
        spies = Settings.SPIES_NUMBER;
        spiesPos = new ArrayList<>();
        plType = new boolean[Settings.PLAYERS_NUMBER];
        Arrays.fill(plType, false);
    }

    public List<KolodaCard> generateCards() {
        List<KolodaCard> cards = new ArrayList<>();
        int i = 0;
        //firstly we need to position spies
        while (spies != 0) {
            //the position of spies are saved in spiesPos list and boolean array plType
            if (Math.random() > 0.6 && !plType[i]) {
                plType[i] = true;
                spiesPos.add(i);
                spies--;
            }
            if (i == Settings.PLAYERS_NUMBER - 1) i = 0;
            i++;
        }
        String loc = Location.getRandomLocation(); //receives random location to add in card
        //creating cards using boolean array
        for (boolean type: plType) {
            KolodaCard card = new KolodaCard();
            //if current boolean is true it means player at this number and position is spy
            if (type) {
                card.setPlayerType("SPY"); //player type
                card.setPlayerLoc("Try to guess location"); //card text for spies
                card.setImg(R.drawable.icon_light); //spy img
            }
            //else player is ordinary person
            else {
                card.setPlayerType("Ordinary person"); //player type
                card.setPlayerLoc(loc); //card text with location name
                card.setImg(R.drawable.icon__3_); //no-spy img
            }
            cards.add(card); //adding card to list
        }
        return cards; //returns generated list of cards
    }

    //returns spy-players position in string
    public String getSpiesPositions() {
        StringBuilder builder = new StringBuilder();
        for (int a: spiesPos) {
            builder.append("player ").append((a + 1)).append(" ");
        }
        return builder.toString();
    }
}
