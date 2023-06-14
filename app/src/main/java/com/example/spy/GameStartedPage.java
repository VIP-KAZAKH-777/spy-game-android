package com.example.spy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.spy.adapters.KolodaAdapter;
import com.example.spy.generators.CardGenerator;
import com.example.spy.models.KolodaCard;
import com.example.spy.models.Settings;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.yalantis.library.Koloda;
import com.yalantis.library.KolodaListener;

import java.util.List;
import java.util.Locale;

public class GameStartedPage extends AppCompatActivity {
    private KolodaAdapter kolodaAdapter;
    private List<KolodaCard> kolodaCards;
    private Koloda koloda;
    private LinearLayout timerLayout;
    private CardGenerator gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_started_page);

        timerLayout = findViewById(R.id.timer_layout);
        LinearLayout cancelBtn = findViewById(R.id.cancel_layout);
        cancelBtn.setOnClickListener((v -> {
            finish();
            KolodaCard.resetPLayers();
        }));

        koloda = findViewById(R.id.koloda);

        gen = new CardGenerator();
        kolodaCards = gen.generateCards();
        kolodaAdapter = new KolodaAdapter(this, kolodaCards);
        koloda.setAdapter(kolodaAdapter);
        koloda.setKolodaListener(new KolodaListener() {
            @Override
            public void onNewTopCard(int i) {

            }

            @Override
            public void onCardDrag(int i, @NonNull View view, float v) {

            }

            @Override
            public void onCardSwipedLeft(int i) {

            }

            @Override
            public void onCardSwipedRight(int i) {

            }

            @Override
            public void onClickRight(int i) {

            }

            @Override
            public void onClickLeft(int i) {

            }

            @Override
            public void onCardSingleTap(int i) {
            }

            @Override
            public void onCardDoubleTap(int i) {

            }

            @Override
            public void onCardLongPress(int i) {

            }

            @Override
            public void onEmptyDeck() {
                startTimerWorking();
            }
        });
    }

    private CountDownTimer timer;
    private TextView changeText, timerText;
    private ImageButton pauseBtn, resetBtn;
    private long timeLeft = Settings.TIME;
    private boolean timerRunning;
    private void startTimerWorking() {
        timerLayout.setVisibility(View.VISIBLE);
        changeText = findViewById(R.id.changeable_text);
        changeText.setText(R.string.started);
        timerText = findViewById(R.id.timer);
        pauseBtn = findViewById(R.id.pause_button);
        resetBtn = findViewById(R.id.reset_button);

        startTimer();
        pauseBtn.setOnClickListener(v -> {
            if (timerRunning) {
                pauseTimer();
            }
            else {
                startTimer();
            }
        });
        resetBtn.setOnClickListener(v -> {
            pauseTimer();
            timeLeft = Settings.TIME;
            updateTimer(timeLeft);
        });
    }

    private void startTimer() {
        pauseBtn.setImageResource(R.drawable.pause_circle);
        timerRunning = true;
        timer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTimer(timeLeft);
            }

            @Override
            public void onFinish() {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(GameStartedPage.this);
                builder.setCancelable(true);
                builder.setTitle("Time's up!");
                builder.setPositiveButton("Show spies", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(GameStartedPage.this);
                        builder.setMessage("Spies are: " + gen.getSpiesPositions()).show();
                    }
                })
                        .setNegativeButton("Leave", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                builder.show();
                KolodaCard.resetPLayers();
            }
        }.start();
    }

    private void pauseTimer() {
        pauseBtn.setImageResource(R.drawable.start);
        timer.cancel();
        timerRunning = false;
    }

    private void updateTimer(long timeLeft) {
        int min = (int) (timeLeft/ 1000) / 60;
        int sec = (int) (timeLeft / 1000) % 60;
        String time = String.format(Locale.getDefault(),"%02d:%02d", min, sec);
        timerText.setText(time);
    }
}