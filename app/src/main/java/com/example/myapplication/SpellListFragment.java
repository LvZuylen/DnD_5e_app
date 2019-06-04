package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class SpellListFragment extends Fragment implements SpellAdapter.SpellOnItemClickHandler {
    public ArrayList<JsonSpell> spellBook;
    private SpellAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RequestQueue queue;
    // private String[] urls;
    private ArrayList<String> urls;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        spellBook = new ArrayList<>();
        recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.spell_list_view);

        // Initialize spells
        adapter = new SpellAdapter(spellBook, this);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        if (!getListFromPreferences()) {
            defaultSpellBook();
        }

        queue = Volley.newRequestQueue(getActivity());
        addToSpellBook(urls);
        Button addSpell = getActivity().findViewById(R.id.addSpellButton);
        addSpell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMoreSpells("http://www.dnd5eapi.co/api/spells/1");
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.spell_list_view, container, false);

    }

    public int addToSpellBook(ArrayList<String> urls) {
        int count = urls.size();
        for (int i = 0; i < count; i++) {
            JsonObjectRequest spellRequest = new JsonObjectRequest(Request.Method.GET, urls.get(i),
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            spellBook.add(new JsonSpell(response));
                            adapter.notifyItemInserted(spellBook.size());
                            Log.e("Rest Response", "response is: " + response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Rest Response", error.toString());
                }
            }
            );
            queue.add(spellRequest);
        }
        return 1;
    }

    // adds one or more spells
    public void addMoreSpells(String newSpell) {
        ArrayList newURL = new ArrayList(0);
        newURL.add(newSpell);
        addToSpellBook(newURL);
        urls.add(newSpell);
        saveListInPreferences();
    }

    public void defaultSpellBook() {
        urls = new ArrayList<>(Arrays.asList(
                "http://www.dnd5eapi.co/api/spells/42/",
                "http://www.dnd5eapi.co/api/spells/43/",
                "http://www.dnd5eapi.co/api/spells/44/",
                "http://www.dnd5eapi.co/api/spells/45/",
                "http://www.dnd5eapi.co/api/spells/46/",
                "http://www.dnd5eapi.co/api/spells/47/",
                "http://www.dnd5eapi.co/api/spells/48/",
                "http://www.dnd5eapi.co/api/spells/484/",
                "http://www.dnd5eapi.co/api/spells/434/",
                "http://www.dnd5eapi.co/api/spells/435/",
                "http://www.dnd5eapi.co/api/spells/437/",
                "http://www.dnd5eapi.co/api/spells/439/",
                "http://www.dnd5eapi.co/api/spells/345/",
                "http://www.dnd5eapi.co/api/spells/223/",
                "http://www.dnd5eapi.co/api/spells/123/",
                "http://www.dnd5eapi.co/api/spells/119"));
    }

    public void saveListInPreferences() {
        String json = new Gson().toJson(urls);
        SharedPreferences prefs = getActivity().getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(getString(R.string.pref_key), json);
        editor.apply();
    }
//
//    public void deleteListFromPreferences() {
//        SharedPreferences prefs = getActivity().getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.clear();
//        editor.apply();
//        urls = null;
//    }

    public boolean getListFromPreferences() {
        SharedPreferences prefs = getActivity().getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        if (prefs.contains(getString(R.string.pref_key))) {
            String prefsString = prefs.getString(getString(R.string.pref_key), "");

            urls = new Gson().fromJson(prefsString, ArrayList.class);
            return true;
        }
        return false;
    }


    @Override
    public void onSpellClick(String spellDesc) {
        // NavController controller = NavHostFragment.findNavController(SpellListFragment.this);
        // controller.navigate(R.id.spellViewFragment);
        SpellListFragmentDirections.ActionSpellListViewToSpellViewFragment action;

        action = SpellListFragmentDirections.actionSpellListViewToSpellViewFragment();
        action.setSpelltext(spellDesc);        // should be:
        NavHostFragment.findNavController(SpellListFragment.this).navigate(action);
    }
}


