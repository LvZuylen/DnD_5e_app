package com.example.myapplication;


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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpellViewFragment extends Fragment {

    private TextView textView;
    private Gson gsonSpell;

    public SpellViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // itemadapter -> research
        // async class binnen spellview class
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "http://www.dnd5eapi.co/api/spells/41/";
        textView = Objects.requireNonNull(getView()).findViewById(R.id.spell_text);
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gsonSpell = gsonBuilder.create();

        JsonObjectRequest spellRequest = new JsonObjectRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response", response.toString());
                        JsonSpell jsonSpell = gsonSpell.fromJson(response.getJSONObject("id").toString(), JsonSpell.class);
                        textView.setText("Response is: " + response.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Rest Response", error.toString());
                textView.setText("That didn't work! Error message: \n" + error.toString());
            }
        }
        );

        // Add the request to the RequestQueue.
        // queue.add(stringRequest);
        queue.add(spellRequest);
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.spell_view, container, false);

    }

}

