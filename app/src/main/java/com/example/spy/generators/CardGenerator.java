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

    public CardGenerator() {
        spies = Settings.SPIES_NUMBER;
        spiesPos = new ArrayList<>();
        plType = new boolean[Settings.PLAYERS_NUMBER];
        Arrays.fill(plType, false);
    }

    public List<KolodaCard> generateCards() {
        List<KolodaCard> cards = new ArrayList<>();
        int i = 0;
        while (spies != 0) {
            if (Math.random() > 0.6 && !plType[i]) {
                plType[i] = true;
                spiesPos.add(i);
                spies--;
            }
            if (i == Settings.PLAYERS_NUMBER - 1) i = 0;
            i++;
        }
        String loc = Location.getRandomLocation();
        for (boolean type: plType) {
            KolodaCard card = new KolodaCard();
            if (type) {
                card.setPlayerType("SPY");
                card.setPlayerLoc("Try to guess location");
                card.setImg(R.drawable.icon_light); //spy img
            }
            else {
                card.setPlayerType("Ordinary person");
                card.setPlayerLoc(loc);
                card.setImg(R.drawable.icon_light); //no-spy img
            }
            cards.add(card);
        }
        return cards;
    }

    public String getSpiesPositions() {
        StringBuilder builder = new StringBuilder();
        for (int a: spiesPos) {
            builder.append("player ").append((a + 1)).append(" ");
        }
        return builder.toString();
    }
}
