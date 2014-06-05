package com.ryan.hallermeier.golfrules.main;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.hallermeier.golfrules.main.models.Player;
import com.ryan.hallermeier.golfrules.main.models.Round;
import com.ryan.hallermeier.golfrules.main.models.Team;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class NewTeamPlayersFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TEAM_NUMBER = "team_number";

    // TODO: Rename and change types of parameters
    private int team_number;

    HashMap<Integer, Player> selectedPlayers = new HashMap<Integer, Player>();
    ArrayList<Player> playerArrayList = new ArrayList<Player>(4);
    private GolfFragmentInteractionInterface mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    public static NewTeamPlayersFragment newInstance(int teamId) {
        NewTeamPlayersFragment fragment = new NewTeamPlayersFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TEAM_NUMBER, teamId);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewTeamPlayersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            team_number = getArguments().getInt(ARG_TEAM_NUMBER);
        }
        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<Player>(getActivity(),
                android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, mListener.getPlayers() );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rules, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mListener.setActionBarTitle("Select Players ");
        return view;
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            CheckedTextView checkedTextView = ((CheckedTextView)view);
            if(checkedTextView.isChecked())
            {
                //selectedPlayers.put(position, mListener.getPlayers().get(position));
                playerArrayList.add((Player) parent.getItemAtPosition(position));
            }else {
                //selectedPlayers.remove(position);
                playerArrayList.remove(parent.getItemAtPosition(position));
            }
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.done, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.done_item:
                Time now = new Time();
                now.setToNow();
                Round newRound = new Round();
                newRound.setRoundId(mListener.getRounds().size() + 1 );
                newRound.setCourseId(0);
                newRound.setDate( now.format("%Y-%m-%d"));
                mListener.addRound(newRound);

                Team newTeam = new Team();
                newTeam.setTeamId(mListener.getAllTeams().size() + 1 );
                newTeam.setRoundId(newRound.getRoundId());
                newTeam.setTeamNumber(team_number);
                mListener.addTeam(newTeam);
                for ( Player selectedPlayer : playerArrayList)
                {
                   selectedPlayer.setTeamId(newTeam.getTeamId());
                   mListener.updatePlayer(selectedPlayer);
                }

                mListener.showScoreTrackerFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
