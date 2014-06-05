package com.ryan.hallermeier.golfrules.main;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ryan.hallermeier.golfrules.main.models.Player;
import com.ryan.hallermeier.golfrules.main.models.Shot;

import java.util.ArrayList;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.ryan.hallermeier.golfrules.main.HoleDescriptionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link com.ryan.hallermeier.golfrules.main.HoleDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HoleDescriptionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String HOLE_ID = "hole_id";

    // TODO: Rename and change types of parameters
    private int hole_id;

    private TextView holeText;
    private TextView parText;
    private TextView mensDistance;
    private TextView womensDistance;

    private ScoreTrackingInteractionInterface mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoringHoleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HoleDescriptionFragment newInstance(int holeId) {
        HoleDescriptionFragment fragment = new HoleDescriptionFragment();
        Bundle args = new Bundle();
        args.putInt(HOLE_ID, holeId);
        fragment.setArguments(args);
        return fragment;
    }

    public HoleDescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hole_id = getArguments().getInt(HOLE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_description_hole,
                null);

        holeText = (TextView) layout.findViewById(R.id.holeName);
        parText = (TextView) layout.findViewById(R.id.par);
        mensDistance = (TextView) layout.findViewById(R.id.mensDistance);
        womensDistance = (TextView) layout.findViewById(R.id.womensDistance);


        holeText.setText("Hole " + mListener.getHole(hole_id).getHoleId());
        parText.setText("Par - " + mListener.getHole(hole_id).getPar());
        mensDistance.setText("White Tees - " + mListener.getHole(hole_id).getMenDistance() + " yds");
        womensDistance.setText("Red Tees - " + mListener.getHole(hole_id).getWomenDistance() + " yds");

        return layout;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ScoreTrackingInteractionInterface) activity;
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
