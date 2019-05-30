package com.example.myapplication;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpellViewFragment extends Fragment {

    private TextView textView;

    public SpellViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // async class binnen spellview class
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        //String url = "http://www.dnd5eapi.co/api/spells/41/";
        String url = "http://www.dnd5eapi.co/api/spells/41/";
        textView = Objects.requireNonNull(getView()).findViewById(R.id.spell_text);
        // http://www.dnd5eapi.co/api/spells/21/

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: " + response.substring(0, 900));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
                textView.setText("That didn't work! Error message: \n" + error.toString());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.spell_view, container, false);

    }

}
