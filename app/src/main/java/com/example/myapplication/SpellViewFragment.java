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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpellViewFragment extends Fragment {

    private TextView textView;
    private RequestQueue queue;
    public String textString = "nothing";
    // private boolean responseReceived;
    public JsonSpell jsonSpell;


    public SpellViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // itemadapter -> research
        textView = Objects.requireNonNull(getView()).findViewById(R.id.spell_text);
        queue = Volley.newRequestQueue(getActivity());
        textView.setText(textString);
        String url = "http://www.dnd5eapi.co/api/spells/42/";
        new DownloadSpellController().execute(url);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.spell_view, container, false);

    }

    private class DownloadSpellController extends AsyncTask<String, Void, JsonSpell> {
        private boolean responseReceived = false;

        @Override
        protected JsonSpell doInBackground(String... url) {

            JsonObjectRequest spellRequest = new JsonObjectRequest(Request.Method.GET, url[0],
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            jsonSpell = new JsonSpell(response);
                            responseReceived = true;
                            Log.e("Rest Response", "response is: " + response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Rest Response", error.toString());
                    textView.setText("That didn't work! Error message: \n" + error.toString());
                    responseReceived = true;
                }
            }
            );
            queue.add(spellRequest);
            textView.setText("loading..");
            while (true) {
                // wait until the json spell is ready, ugly but works
                if (responseReceived) {
                    break;
                }
            }
            return jsonSpell;
        }

        @Override
        protected void onPostExecute(JsonSpell spell) {
            // update UI elements
            textView.setText(jsonSpell.getSpellName());
            queue.cancelAll("Rest Response");

        }
    }
}

