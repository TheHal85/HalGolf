package com.ryan.hallermeier.golfrules.main.models;

import java.util.ArrayList;

/**
 * Created by Hal on 6/3/2014.
 */
public class Shot {

    private int shotId;
    private int holeId;
    private int playerId;

    public Shot(){}

    public Shot(int shotId, int holeId, int playerId) {
        super();
        this.shotId = shotId;
        this.holeId = holeId;
        this.playerId = playerId;
    }

    //getters & setters

    @Override
    public String toString() {
        return "Shot";
    }
}
