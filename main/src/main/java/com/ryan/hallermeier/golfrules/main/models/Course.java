package com.ryan.hallermeier.golfrules.main.models;

import java.util.ArrayList;

/**
 * Created by Hal on 6/3/2014.
 */
public class Course {

    private int courseId;
    private String courseName;
    private ArrayList<Hole> holes;

    public Course(){}

    public Course(int courseId, String courseName) {
        super();
        this.courseId = courseId;
        this.courseName = courseName;
    }

    //getters & setters

    @Override
    public String toString() {
        return courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<Hole> getHoles() {
        return holes;
    }

    public void setHoles(ArrayList<Hole> holes) {
        this.holes = holes;
    }
}
