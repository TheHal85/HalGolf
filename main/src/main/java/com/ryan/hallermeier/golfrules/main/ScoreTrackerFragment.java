package com.ryan.hallermeier.golfrules.main;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScoreTrackerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScoreTrackerFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ScoreTrackerFragment extends Fragment {

    private GolfFragmentInteractionInterface mListener;


    public static ScoreTrackerFragment newInstance() {
        ScoreTrackerFragment fragment = new ScoreTrackerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public ScoreTrackerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score_tracker, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (GolfFragmentInteractionInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
