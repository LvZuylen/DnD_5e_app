package com.example.myapplication;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpellViewFragment extends Fragment {

    private TextView textView;
    private String textString = "nothing";

    public SpellViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        textView = view.findViewById(R.id.spell_description);
        // textView = Objects.requireNonNull(getView()).findViewById(R.id.spell_description);
        SpellViewFragmentArgs args = SpellViewFragmentArgs.fromBundle(getArguments());
        textString = args.getSpelltext();
        textView.setText(textString);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.spell_view_details, container, false);

    }


}

