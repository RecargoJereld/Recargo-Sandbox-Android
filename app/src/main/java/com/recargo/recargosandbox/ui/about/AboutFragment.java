package com.recargo.recargosandbox.ui.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recargo.recargosandbox.BuildConfig;
import com.recargo.recargosandbox.R;

import butterknife.ButterKnife;

/**
 * Created by jereld on 1/10/17.
 */

public class AboutFragment extends Fragment {

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView versionTextView = ButterKnife.findById(view, R.id.version_string);
        versionTextView.setText(getString(R.string.version_format, BuildConfig.VERSION_NAME));
    }
}
