package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SpellAdapter extends RecyclerView.Adapter<SpellAdapter.MyViewHolder> {
    private ArrayList<JsonSpell> sb;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView spellTitleTV;
        public TextView spellLevelTV;
        public MyViewHolder(TextView spellPreview) {
            //wtf am i even doing here?
            super(spellPreview);
            spellTitleTV = spellPreview.findViewById(R.id.spellTitle);
        }
    }

    public SpellAdapter(ArrayList<JsonSpell> spells) {
        sb = spells;
    }

    @Override
    public SpellAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        TextView v = (TextView) inflater.inflate(R.layout.spell_preview, parent, false);
        // final TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        // Return a new holder instance
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        // Get the data model based on position
        JsonSpell spell = sb.get(position);
        // Set item views based on your views and data model
        // TextView spellTitleTV = viewHolder.titleTextView;
        viewHolder.spellTitleTV.setText(spell.getSpellName());

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return sb.size();
    }



}