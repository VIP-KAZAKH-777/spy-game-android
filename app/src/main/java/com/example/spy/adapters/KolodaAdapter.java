package com.example.spy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spy.R;
import com.example.spy.models.KolodaCard;

import java.util.List;

public class KolodaAdapter extends BaseAdapter {
    private Context context;
    private final List<KolodaCard> kolodaCards;

    public KolodaAdapter(Context context, List<KolodaCard> kolodaCards) {
        this.context = context;
        this.kolodaCards = kolodaCards;
    }

    @Override
    public int getCount() {
        return kolodaCards.size();
    }

    @Override
    public Object getItem(int position) {
        return kolodaCards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        TextView playNum, playType, playLoc;
        ImageView img;

        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_koloda, parent, false);

            playNum = view.findViewById(R.id.player_num);
            img = view.findViewById(R.id.player_img);
            playType = view.findViewById(R.id.player_type);
            playLoc = view.findViewById(R.id.player_location);

            KolodaCard curr = kolodaCards.get(position);
            playNum.setText(curr.getPlayerNum());
            img.setImageResource(curr.getImg());
            playType.setText(curr.getPlayerType());
            playLoc.setText(curr.getPlayerLoc());
        }
        else view = convertView;
        return view;
    }
}
