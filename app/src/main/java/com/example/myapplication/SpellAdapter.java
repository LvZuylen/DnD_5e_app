package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SpellAdapter extends RecyclerView.Adapter<SpellAdapter.ViewHolder> {

    private ArrayList<JsonSpell> sb;

    public SpellAdapter(ArrayList<JsonSpell> spells) {
        sb = spells;
    }

    @Override
    public SpellAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View spellView = inflater.inflate(R.layout.spell_list_view, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(spellView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(SpellAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        JsonSpell spell = sb.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.titleTextView;
        textView.setText(spell.getSpellName());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return sb.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView titleTextView;
        public TextView levelTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            titleTextView = itemView.findViewById(R.id.spellTitle);
            levelTextView = itemView.findViewById(R.id.spellLevel);
        }
    }
}