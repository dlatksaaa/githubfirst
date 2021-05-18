package com.professionalandroid.apps.githubfirst;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class MusicAdepter extends ArrayAdapter<Music> {



    public MusicAdepter(@NonNull Context context, @NonNull List<Music> objects) {
        super(context, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.music_list_item, parent, false);
        }
        TextView category = (TextView) listItemView.findViewById(R.id.music_name);
        TextView name = (TextView) listItemView.findViewById(R.id.music_category);

        category.setText(getItem(position).getCategory());
        name.setText(getItem(position).getName());

        return listItemView;
    }

}
