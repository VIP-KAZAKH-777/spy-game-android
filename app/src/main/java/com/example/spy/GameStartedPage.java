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
    private KolodaAdapter kolodaAdapter; //adapter for swipe cards
    private List<KolodaCard> kolodaCards; //swipe cards list
    private Koloda koloda; //swipe cards layout
    private LinearLayout timerLayout; //timer layout
    private CardGenerator gen; //additional class to give every card player num, type

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_started_page);

        timerLayout = findViewById(R.id.timer_layout);
        LinearLayout cancelBtn = findViewById(R.id.cancel_layout);
        cancelBtn.setOnClickListener((v -> {
            finish();
            KolodaCard.resetPLayers(); //resets static field that sets player number
        }));

        koloda = findViewById(R.id.koloda);

        gen = new CardGenerator();
        kolodaCards = gen.generateCards(); //fills list by generated cards
        kolodaAdapter = new KolodaAdapter(this, kolodaCards); //creating adapter
        koloda.setAdapter(kolodaAdapter); //setting adapter
        koloda.setKolodaListener(new KolodaListener() { //swipe cards listener, where used only 1 method -> onEmptyDeck()
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
                startTimerWorking(); //Starts timer
            }
        });
    }

    //Code below creates and sets timer on the screen
    private CountDownTimer timer; //Timer
    private TextView changeText, timerText; //timerText -> main timer that is changed every second
    private ImageButton pauseBtn, resetBtn; //timer controlling buttons
    private long timeLeft = Settings.TIME; //left time in millsecs
    private boolean timerRunning; //boolean to manage pause and resume
    private void startTimerWorking() {
        timerLayout.setVisibility(View.VISIBLE); //by default visibility is gone, now it is visible
        changeText = findViewById(R.id.changeable_text);
        changeText.setText(R.string.started); //changes title
        timerText = findViewById(R.id.timer);
        pauseBtn = findViewById(R.id.pause_button);
        resetBtn = findViewById(R.id.reset_button);

        startTimer(); //starting timer
        pauseBtn.setOnClickListener(v -> {
            //if timerRunning is true, we pause timer, otherwise start/resume
            if (timerRunning) {
                pauseTimer();
            }
            else {
                startTimer();
            }
        });
        resetBtn.setOnClickListener(v -> {
            //resets time and pauses timer
            pauseTimer();
            timeLeft = Settings.TIME;
            updateTimer(timeLeft);
        });
    }

    private void startTimer() {
        pauseBtn.setImageResource(R.drawable.pause_circle); //if timer is run we set button pause image
        timerRunning = true;
        timer = new CountDownTimer(timeLeft /* starting time */, 1000 /* The interval along the way to receive callbacks. */) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTimer(timeLeft); // changes timer textview
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
                        builder.setMessage("Spies are: " + gen.getSpiesPositions()).show(); //if user presses show spies, new dialog is created with spies
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
        pauseBtn.setImageResource(R.drawable.start); //start button image
        timer.cancel();
        timerRunning = false;
    }

    private void updateTimer(long timeLeft) {
        int min = (int) (timeLeft/ 1000) / 60; //minutes
        int sec = (int) (timeLeft / 1000) % 60; //seconds
        String time = String.format(Locale.getDefault(),"%02d:%02d", min, sec); //formatting
        timerText.setText(time); //setting
    }
}