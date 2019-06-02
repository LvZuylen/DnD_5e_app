package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SpellItemAdapter extends ArrayAdapter<JsonSpell> {
    private Activity myContext;
    private ArrayList<JsonSpell> linkItemArrayList;

    public SpellItemAdapter(Context context, int textViewResourceId,
                           ArrayList<JsonSpell> objects) {
        super(context, textViewResourceId, objects);
        myContext = (Activity) context;
        linkItemArrayList = objects;
    }

    static class ViewHolder {
        TextView titleView;
        TextView levelView;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        SpellItemAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = myContext.getLayoutInflater();
            convertView = inflater.inflate(R.layout.spell_preview, parent, false);

            viewHolder = new SpellItemAdapter.ViewHolder();
            viewHolder.titleView = convertView
                    .findViewById(R.id.spellTitle);
            viewHolder.levelView = convertView
                    .findViewById(R.id.spellLevel);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SpellItemAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.titleView.setText(linkItemArrayList.get(position).Title);
        viewHolder.levelView.setText(linkItemArrayList.get(position).Link);

        return convertView;
    }
}