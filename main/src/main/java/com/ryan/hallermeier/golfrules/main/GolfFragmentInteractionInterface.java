package com.ryan.hallermeier.golfrules.main;

import com.ryan.hallermeier.golfrules.main.models.Course;
import com.ryan.hallermeier.golfrules.main.models.Hole;
import com.ryan.hallermeier.golfrules.main.models.Player;

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
}
