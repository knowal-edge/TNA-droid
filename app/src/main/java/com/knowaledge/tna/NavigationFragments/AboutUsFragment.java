package com.knowaledge.tna.NavigationFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.knowaledge.tna.R;

public class AboutUsFragment extends Fragment {
    private View rootview;
    private WebView webview;
    private ProgressBar pbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.activity_login, container, false);
        return rootview;

    }
}