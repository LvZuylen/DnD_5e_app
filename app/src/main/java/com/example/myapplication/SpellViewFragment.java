package com.example.myapplication;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

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
        textView = Objects.requireNonNull(getView()).findViewById(R.id.spell_text);
        textString = SpellViewFragmentArgs.fromBundle(getArguments()).getSpell();
        textView.setText(textString);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.spell_preview, container, false);

    }




}

