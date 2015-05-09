package com.mewa.langhub.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mewa.langhub.R;
import com.mewa.langhub.adapters.WordAdapter;


/**
 * A placeholder fragment containing a simple view.
 */
public class WordListFragment extends Fragment {

    //private FrictionMapView mFrictionMapView;
    // private TPad mTpad;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    public WordListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //mTpad = new TPadImpl(getActivity());

        // mFrictionMapView = (FrictionMapView)view.findViewById(R.id.friction_map_view);
        // mFrictionMapView.setTpad(mTpad);


        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new WordAdapter());

        return view;
    }
}
