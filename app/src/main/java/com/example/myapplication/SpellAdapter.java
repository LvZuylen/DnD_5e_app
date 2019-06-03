package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SpellAdapter extends RecyclerView.Adapter<SpellAdapter.ViewHolder> {
    private ArrayList<JsonSpell> sb;
    private final SpellOnItemClickHandler spellOnItemClickHandler;

    public SpellAdapter(ArrayList<JsonSpell> spells, SpellOnItemClickHandler spellOnItemClickHandler) {
        sb = spells;
        this.spellOnItemClickHandler = spellOnItemClickHandler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.spell_preview, parent, false);

        // Inflate the custom layout
        // Return a new holder instance
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JsonSpell item = sb.get(position);
        holder.titleView.setText(item.getSpellName());
        holder.levelView.setText("Spell level: " + item.getSpellLevel());
    }

    @Override
    public int getItemCount() {
        return sb.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleView;
        TextView levelView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.spellTitle);
            levelView = itemView.findViewById(R.id.spellMaterials);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            // JsonSpell spell = sb.get(pos);
            // spellOnItemClickHandler.onSpellClick(spell);

            String description = sb.get(pos).getSpellDesc();
            spellOnItemClickHandler.onSpellClick(description);
        }
    }

    public interface SpellOnItemClickHandler {
        void onSpellClick(String spellDesc);
    }
}