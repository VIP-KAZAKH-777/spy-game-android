package com.example.spy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.spy.adapters.KolodaAdapter;
import com.example.spy.generators.CardGenerator;
import com.example.spy.listeners.KolodaListener;
import com.example.spy.models.KolodaCard;
import com.example.spy.models.Settings;
import com.yalantis.library.Koloda;

import java.util.ArrayList;
import java.util.List;

public class GameStartedPage extends AppCompatActivity {
    private KolodaAdapter kolodaAdapter;
    private List<KolodaCard> kolodaCards;
    private Koloda koloda1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_started_page);

        LinearLayout cancelBtn = findViewById(R.id.cancel_layout);
        cancelBtn.setOnClickListener((v -> {
            finish();
            KolodaCard.resetPLayers();
        }));

        koloda1 = findViewById(R.id.koloda);

        CardGenerator gen = new CardGenerator();
        kolodaCards = gen.generateCards();
        kolodaAdapter = new KolodaAdapter(this, kolodaCards);
        koloda1.setAdapter(kolodaAdapter);
    }
}