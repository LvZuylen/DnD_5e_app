package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import java.util.Objects;
import java.util.ArrayList;


public class SpellListFragment extends Fragment {
    public ArrayList<JsonSpell> spellBook;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RequestQueue queue;
    // private OnFragmentInteractionListener mListener;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.spell_list_view);
        // Initialize spells
        queue = Volley.newRequestQueue(getActivity());
        String url = "http://www.dnd5eapi.co/api/spells/42/";
        new SpellInitController().execute(url);

        // Create adapter passing in the sample user data
        SpellAdapter adapter = new SpellAdapter(spellBook);
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        // That's all!
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

    public class SpellInitController extends AsyncTask<String, Void, Void> {
        private boolean responseReceived = false;

        @Override
        protected Void doInBackground(String... urls) {
            int count = urls.length;
            for (int i = 0; i < count; i++) {
                JsonObjectRequest spellRequest = new JsonObjectRequest(Request.Method.GET, urls[i],
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (spellBook.add(new JsonSpell(response))) {
                                }
                                responseReceived = true;
                                Log.e("Rest Response", "response is: " + response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response", error.toString());
                        responseReceived = true;
                    }
                }
                );
                queue.add(spellRequest);
                while (true) {
                    // wait until the json spell is ready, ugly but works
                    if (responseReceived) {
                        break;
                    }
                }
            }

            return null;
        }

    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment SpellListFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static SpellListFragment newInstance(String param1, String param2) {
//        SpellListFragment fragment = new SpellListFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.spell_list_view, container, false);
//
//
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.spell_list_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//
//        // specify an adapter (see also next example)
//        mAdapter = new SpellAdapter(spellBook);
//        recyclerView.setAdapter(mAdapter);
//    }
//
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
