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
        return "Course";
    }
}
