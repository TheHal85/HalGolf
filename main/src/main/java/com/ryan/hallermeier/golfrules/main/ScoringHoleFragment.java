package com.ryan.hallermeier.golfrules.main;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
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
 * {@link ScoringHoleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScoringHoleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoringHoleFragment extends Fragment implements View.OnClickListener, ScoreFragmentInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String HOLE_ID = "hole_id";

    // TODO: Rename and change types of parameters
    private int hole_id;

    private TextView holeText;
    private TextView parText;
    private TextView mensDistance;
    private TextView womensDistance;
    private TextView instructions;

    private Button golfer1Button;
    private Button golfer2Button;
    private Button golfer3Button;
    private Button golfer4Button;

    private Player golfer1;
    private Player golfer2;
    private Player golfer3;
    private Player golfer4;

    ArrayList<Shot> shotsTaken = new ArrayList<Shot>();

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
    public static ScoringHoleFragment newInstance(int holeId) {
        ScoringHoleFragment fragment = new ScoringHoleFragment();
        Bundle args = new Bundle();
        args.putInt(HOLE_ID, holeId);
        fragment.setArguments(args);
        return fragment;
    }

    public ScoringHoleFragment() {
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
        View layout = inflater.inflate(R.layout.fragment_scoring_hole,
                null);

        holeText = (TextView) layout.findViewById(R.id.holeName);
        parText = (TextView) layout.findViewById(R.id.par);
        mensDistance = (TextView) layout.findViewById(R.id.mensDistance);
        womensDistance = (TextView) layout.findViewById(R.id.womensDistance);
        instructions = (TextView) layout.findViewById(R.id.holeInstructions);

        golfer1Button = (Button) layout.findViewById(R.id.golfer1);
        golfer1Button.setOnClickListener(this);
        golfer2Button = (Button) layout.findViewById(R.id.golfer2);
        golfer2Button.setOnClickListener(this);
        golfer3Button = (Button) layout.findViewById(R.id.golfer3);
        golfer3Button.setOnClickListener(this);
        golfer4Button = (Button) layout.findViewById(R.id.golfer4);
        golfer4Button.setOnClickListener(this);

        holeText.setText("Hole " + mListener.getHole(hole_id).getHoleId());
        parText.setText("Par - " + mListener.getHole(hole_id).getPar());
        mensDistance.setText("White Tees - " + mListener.getHole(hole_id).getMenDistance() + " yds");
        womensDistance.setText("Red Tees - " + mListener.getHole(hole_id).getWomenDistance() + " yds");
        instructions.setText("Who took shot " + (shotsTaken.size() + 1) + "?");
        if(hole_id==1)
        {
            fragmentBecameVisible();
        }
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.golfer1:
                createShot(golfer1, view);
                checkExtraShots();
                break;
            case R.id.golfer2:
                createShot(golfer2, view);
                checkExtraShots();
                break;
            case R.id.golfer3:
                createShot(golfer3, view);
                checkExtraShots();
                break;
            case R.id.golfer4:
                createShot(golfer4, view);
                checkExtraShots();
                break;
        }
    }

    private void createShot(Player player, View view) {
        if ((shotsTaken.size() + 1) > 4) {
            player.setExtraShot(player.getExtraShot()+1);
            mListener.updatePlayer(player);
        }
        Shot newShot = new Shot();
        newShot.setShotId(shotsTaken.size() + 1);
        newShot.setHoleId(hole_id);
        newShot.setPlayerId(player.getPlayerId());
        shotsTaken.add(newShot);
        mListener.addShot(newShot);
        view.setEnabled(false);

        instructions.setText("Who took shot " + (shotsTaken.size() + 1) + "?");
    }

    private void checkExtraShots()
    {
        if ((shotsTaken.size() + 1) > 4) {

            Log.d("Hallermeier", "Golfer 1 Extra = " + golfer1.getExtraShot());
            Log.d("Hallermeier", "Golfer 2 Extra = " + golfer2.getExtraShot());
            Log.d("Hallermeier", "Golfer 3 Extra = " + golfer3.getExtraShot());
            Log.d("Hallermeier", "Golfer 4 Extra = " + golfer4.getExtraShot());
            if ( (golfer1.getExtraShot() > golfer2.getExtraShot()) || (golfer1.getExtraShot() > golfer3.getExtraShot()) || (golfer1.getExtraShot() > golfer4.getExtraShot())  )
            {
                golfer1Button.setEnabled(false);
            } else {
                golfer1Button.setEnabled(true);
            }
            if ( (golfer2.getExtraShot() > golfer1.getExtraShot()) || (golfer2.getExtraShot() > golfer3.getExtraShot()) || (golfer2.getExtraShot() > golfer4.getExtraShot())  )
            {
                golfer2Button.setEnabled(false);
            } else {
                golfer2Button.setEnabled(true);
            }

            if ( (golfer3.getExtraShot() > golfer1.getExtraShot()) || (golfer3.getExtraShot() > golfer2.getExtraShot()) || (golfer3.getExtraShot() > golfer4.getExtraShot())  )
            {
                golfer3Button.setEnabled(false);
            } else {
                golfer3Button.setEnabled(true);
            }
            if ( (golfer4.getExtraShot() > golfer1.getExtraShot()) || (golfer4.getExtraShot() > golfer2.getExtraShot()) || (golfer4.getExtraShot() > golfer3.getExtraShot())  )
            {
                golfer4Button.setEnabled(false);
            } else {
                golfer4Button.setEnabled(true);
            }
        }
    }

    @Override
    public void fragmentBecameVisible() {
        shotsTaken = mListener.getShotsbyHoldId(hole_id);
        golfer1 = mListener.getPlayers().get(0);
        golfer2 = mListener.getPlayers().get(1);
        golfer3 = mListener.getPlayers().get(2);
        golfer4 = mListener.getPlayers().get(3);
        Log.d("Hallermeier", "Golfer 1 Extra = " + golfer1.getExtraShot());
        Log.d("Hallermeier", "Golfer 2 Extra = " + golfer2.getExtraShot());
        Log.d("Hallermeier", "Golfer 3 Extra = " + golfer3.getExtraShot());
        Log.d("Hallermeier", "Golfer 4 Extra = " + golfer4.getExtraShot());

        golfer1Button.setText(golfer1.getFirstName() + " " + golfer1.getLastName());
        golfer2Button.setText(golfer2.getFirstName() + " " + golfer2.getLastName());
        golfer3Button.setText(golfer3.getFirstName() + " " + golfer3.getLastName());
        golfer4Button.setText(golfer4.getFirstName() + " " + golfer4.getLastName());
        checkExtraShots();
    }
}
