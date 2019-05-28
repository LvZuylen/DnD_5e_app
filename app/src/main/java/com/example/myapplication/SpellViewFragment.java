package com.example.myapplication;


import android.net.http.SslError;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpellViewFragment extends Fragment {


    public SpellViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        WebView webView = Objects.requireNonNull(getView()).findViewById(R.id.web_view);
        webView.loadUrl("https://www.dnd5eapi.co/api/spells/21/");
        // webView.loadUrl("https://www.dnd5eapi.co/");
        // webView.loadUrl("https://www.google.com");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.spell_view, container, false);

    }

}
