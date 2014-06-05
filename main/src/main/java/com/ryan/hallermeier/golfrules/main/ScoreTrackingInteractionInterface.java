package com.ryan.hallermeier.golfrules.main;

import com.ryan.hallermeier.golfrules.main.models.Course;
import com.ryan.hallermeier.golfrules.main.models.Hole;
import com.ryan.hallermeier.golfrules.main.models.Player;
import com.ryan.hallermeier.golfrules.main.models.Round;
import com.ryan.hallermeier.golfrules.main.models.Shot;
import com.ryan.hallermeier.golfrules.main.models.Team;

import java.util.ArrayList;

/**
 * Created by rhallermeier on 6/4/14.
 */
public interface ScoreTrackingInteractionInterface {
    public ArrayList<Hole> getHolesByCourseId(int courseId);
    public Round getRound(int roundId);
    public ArrayList<Team> getTeamsByRoundId();
    public ArrayList<Player> getPlayers();
    public void setActionBarTitle(String title);
    public void updatePlayer(Player player);
    public Hole getHole(int holeId);
    public ArrayList<Shot> getShotsbyHoldId(int holeId);
    public void addShot(Shot shot);
    public ArrayList<Hole> getAllHoles();

}
