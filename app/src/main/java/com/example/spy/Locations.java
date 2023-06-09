package com.example.spy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.spy.models.Location;

public class Locations extends AppCompatActivity {

    RelativeLayout places;
    RelativeLayout countries;
    RelativeLayout transports;
    RelativeLayout rooms;
    LinearLayout save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        places = findViewById(R.id.places_layout);
        countries = findViewById(R.id.countries_layout);
        transports = findViewById(R.id.transports_layout);
        rooms = findViewById(R.id.rooms_layout);
        save_button = findViewById(R.id.save_button);

        places.setOnClickListener(v -> Location.selectLocation(1, this));
        countries.setOnClickListener(v -> Location.selectLocation(2, this));
        transports.setOnClickListener(v -> Location.selectLocation(3, this));
        rooms.setOnClickListener(v -> Location.selectLocation(4, this));

        save_button.setOnClickListener(v -> finish());
    }
}