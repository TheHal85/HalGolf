package com.ryan.hallermeier.golfrules.main;

import com.ryan.hallermeier.golfrules.main.models.Course;
import com.ryan.hallermeier.golfrules.main.models.Hole;
import com.ryan.hallermeier.golfrules.main.models.Player;
import com.ryan.hallermeier.golfrules.main.models.Round;
import com.ryan.hallermeier.golfrules.main.models.Team;

import java.util.ArrayList;

/**
 * Created by rhallermeier on 6/4/14.
 */
public interface GolfFragmentInteractionInterface {
    public ArrayList<Player> getPlayers();
    public void onCourseSelected(int courseId);
    public ArrayList<Hole> getHolesByCourseId(int courseId);
    public ArrayList<Course> getCourses();
    public ArrayList<String> getRules();
    public ArrayList<Round> getRounds();
    public void onRoundSelected(int roundId);
    public ArrayList<Team> getTeamsByRoundId(int roundId);
    public void setActionBarTitle(String title);
    public void createNewRound();
    public void onNewRoundCourseSelected(int teamId);
    public ArrayList<Team> getAllTeams();
    public void addRound(Round round);
    public void addTeam(Team team);
    public void updatePlayer(Player player);
    public void showScoreTrackerFragment();
}
