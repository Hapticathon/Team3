package com.mewa.langhub;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nxr.tpad.lib.TPad;
import nxr.tpad.lib.TPadImpl;
import nxr.tpad.lib.views.FrictionMapView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private FrictionMapView mFrictionMapView;
    private TPad mTpad;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mTpad = new TPadImpl(getActivity());

        mFrictionMapView = (FrictionMapView)view.findViewById(R.id.friction_map_view);
        mFrictionMapView.setTpad(mTpad);

        return view;
    }
}
