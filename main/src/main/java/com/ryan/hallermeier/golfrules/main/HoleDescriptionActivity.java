package com.ryan.hallermeier.golfrules.main;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ryan.hallermeier.golfrules.main.models.Course;
import com.ryan.hallermeier.golfrules.main.models.Hole;
import com.ryan.hallermeier.golfrules.main.models.Player;
import com.ryan.hallermeier.golfrules.main.models.Round;
import com.ryan.hallermeier.golfrules.main.models.Shot;
import com.ryan.hallermeier.golfrules.main.models.Team;
import com.ryan.hallermeier.golfrules.main.util.DBUtils;

import java.util.ArrayList;


public class HoleDescriptionActivity extends Activity implements ScoreTrackingInteractionInterface, ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v13.app.FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link android.support.v4.view.ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    private DBUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_tracking);
        Intent intent = getIntent();

        dbUtils = new DBUtils(this);


        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int i, final float v, final int i2) {
            }
            @Override
            public void onPageSelected(final int i) {
                actionBar.setSelectedNavigationItem(i);
            }
            @Override
            public void onPageScrollStateChanged(final int i) {
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.score_tracking, menu);
        return true;
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public ArrayList<Hole> getHolesByCourseId(int courseId) {
       return dbUtils.getAllHolesByCourseId(courseId);
    }

    @Override
    public Round getRound(int roundId) {
        return dbUtils.getRound(roundId);
    }

    @Override
    public ArrayList<Team> getTeamsByRoundId() {
        return null;
    }

    @Override
    public ArrayList<Player> getPlayers() {
        int teamId = getTeamsByRoundId().get(0).getTeamId();
        return dbUtils.getAllPlayersByTeamId(teamId);
    }

    @Override
    public void setActionBarTitle(String title) {

    }

    @Override
    public void updatePlayer(Player player) {
        dbUtils.updatePlayer(player);
    }

    @Override
    public Hole getHole(int holeId) {
        return dbUtils.getHole(holeId);
    }

    @Override
    public ArrayList<Shot> getShotsbyHoldId(int holeId) {
        return dbUtils.getShotsByHoleId(holeId);
    }

    @Override
    public void addShot(Shot shot) {
        dbUtils.addShot(shot);
    }

    @Override
    public ArrayList<Hole> getAllHoles() {
        return dbUtils.getAllHoles();
    }

    /**
     * A {@link android.support.v13.app.FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return HoleDescriptionFragment.newInstance(position+1);
           // return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return getHolesByCourseId(0).size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
               return "Hole " + getHolesByCourseId(0).get(position).getHoleId();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.hole_scores, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    private void deleteAllPlayers()
    {
        ArrayList<Player> players = dbUtils.getAllPlayers();
        for (Player player : players) {
            dbUtils.deletePlayer(player);
        }
    }

    private void deleteAllShots()
    {
        ArrayList<Shot> shots = dbUtils.getAllShots();
        for (Shot shot : shots) {
            dbUtils.deleteShot(shot);
        }
    }

    private void deleteAllCourses()
    {
        ArrayList<Course> courses = dbUtils.getAllCourses();
        for (Course course : courses) {
            dbUtils.deleteCourse(course);
        }
    }

    private void deleteAllHoles()
    {
        ArrayList<Hole> holes = dbUtils.getAllHoles();
        for (Hole hole : holes) {
            dbUtils.deleteHole(hole);
        }
    }


    private void deleteAllTeams()
    {
        ArrayList<Team> teams = dbUtils.getAllTeams();
        for (Team team : teams) {
            dbUtils.deleteTeam(team);
        }
    }

    private void deleteAllRounds()
    {
        ArrayList<Round> rounds = dbUtils.getAllRounds();
        for (Round round : rounds) {
            dbUtils.deleteRound(round);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            deleteAllPlayers();
            deleteAllRounds();
            deleteAllTeams();
            deleteAllCourses();
            deleteAllHoles();
            deleteAllShots();
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
