package com.ryan.hallermeier.golfrules.main.models;

/**
 * Created by Hal on 6/3/2014.
 */
public class Player {
    private int playerId;
    private int teamId;
    private String firstName;
    private String lastName;
    private int extraShot;

    public Player(){}

    public Player(int playerId, String firstName, String lastName, int extraShot) {
        super();
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.extraShot = extraShot;
    }

    //getters & setters

    @Override
    public String toString() {
        return firstName +" "+ lastName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getExtraShot() {
        return extraShot;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setExtraShot(int extraShot) {
        this.extraShot = extraShot;
    }
}
