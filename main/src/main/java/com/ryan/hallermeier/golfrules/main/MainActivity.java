package com.ryan.hallermeier.golfrules.main;

import android.app.Activity;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.ryan.hallermeier.golfrules.main.models.Course;
import com.ryan.hallermeier.golfrules.main.models.Hole;
import com.ryan.hallermeier.golfrules.main.models.Player;
import com.ryan.hallermeier.golfrules.main.util.DBUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, GolfFragmentInteractionInterface {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private DBUtils dbUtils;

    private ArrayList<String> rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        dbUtils = new DBUtils(this);
        Resources res = getResources();

        String[] courseNames = res.getStringArray(R.array.courses);
        if (courseNames.length != dbUtils.getAllCourses().size()) {
            createCourses(dbUtils, courseNames);
        }

        int[] parValues = res.getIntArray(R.array.par);
        int[] mensDistance = res.getIntArray(R.array.men_distance);
        int[] womensDistance = res.getIntArray(R.array.women_distance);
        if (parValues.length != dbUtils.getAllHolesByCourseId(0).size()) {
            createHoles(dbUtils, 0, parValues, mensDistance, womensDistance);
        }


        String[] lastNames = res.getStringArray(R.array.golf_last_name);
        String[] firstNames = res.getStringArray(R.array.golf_first_name);
        if (lastNames.length != dbUtils.getAllPlayers().size()) {
            createPlayers(dbUtils, lastNames, firstNames);
        }

        String[] rulesArray = res.getStringArray(R.array.rules);
        rules = new ArrayList<String>();
        Collections.addAll(rules, rulesArray);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                fragmentTransaction.replace(R.id.container, RulesFragment.newInstance());
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.container, PlayersFragment.newInstance());
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.container, ScoreTrackerFragment.newInstance());
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentTransaction.replace(R.id.container, CourseFragment.newInstance());
                fragmentTransaction.commit();
                break;
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    private void createCourses(DBUtils dbUtils, String[] courseNames) {
        ArrayList<Course> courses = dbUtils.getAllCourses();
        for (Course course : courses) {
            dbUtils.deleteCourse(course);
        }
        final int size = courseNames.length;
        for (int i = 0; i < size; i++) {
            dbUtils.addCourse(new Course(i, courseNames[i]));
        }
    }

    private void createPlayers(DBUtils dbUtils, String[] lastNames, String[] firstNames) {
        ArrayList<Player> players = dbUtils.getAllPlayers();
        for (Player player : players) {
            dbUtils.deletePlayer(player);
        }
        final int size = lastNames.length;
        for (int i = 0; i < size; i++) {
            dbUtils.addPlayer(new Player(i, firstNames[i], lastNames[i], 0));
        }
    }

    private void createHoles(DBUtils dbUtils, int courseId ,  int[] parValues, int[] menDistances, int[] womensDistance) {
        ArrayList<Hole> holes = dbUtils.getAllHolesByCourseId(courseId);
        for (Hole hole : holes) {
            dbUtils.deleteHole(hole);
        }
        final int size = parValues.length;
        for (int i = 0; i < size; i++) {
            dbUtils.addHole(new Hole(i+1, courseId, parValues[i], menDistances[i], womensDistance[i]));
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return dbUtils.getAllPlayers();
    }

    @Override
    public void onCourseSelected(int courseId) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, HoleInformationalFragment.newInstance(courseId));
                fragmentTransaction.commit();
    }

    @Override
    public ArrayList<Hole> getHolesByCourseId(int courseId) {
        return dbUtils.getAllHolesByCourseId(courseId);
    }

    @Override
    public ArrayList<Course> getCourses() {
        return dbUtils.getAllCourses();
    }

    @Override
    public ArrayList<String> getRules() {
        return rules;
    }

}
