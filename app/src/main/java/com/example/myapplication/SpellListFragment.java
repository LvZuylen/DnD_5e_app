package com.example.myapplication;

import android.os.AsyncTask;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Objects;
import java.util.ArrayList;


public class SpellListFragment extends Fragment implements SpellAdapter.SpellOnItemClickHandler{
    public ArrayList<JsonSpell> spellBook;
    private SpellAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RequestQueue queue;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        spellBook = new ArrayList<>();
        recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.spell_list_view);
        // Initialize spells


        // Create adapter passing in the sample user data
        adapter = new SpellAdapter(spellBook, this);
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        // That's all!

        queue = Volley.newRequestQueue(getActivity());
        String[] urls = new String[]{
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
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
                "http://www.dnd5eapi.co/api/spells/119",
        };
        addToSpellBook(urls);
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

    public int addToSpellBook(String[] urls) {
        int count = urls.length;
        for (int i = 0; i < count; i++) {
            JsonObjectRequest spellRequest = new JsonObjectRequest(Request.Method.GET, urls[i],
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            spellBook.add(new JsonSpell(response));
                            adapter.notifyDataSetChanged();
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

    @Override
    public void onSpellClick(JsonSpell spell) {
        NavController controller = NavHostFragment.findNavController(SpellListFragment.this);
        controller.navigate(R.id.spellViewFragment);
    }
}


