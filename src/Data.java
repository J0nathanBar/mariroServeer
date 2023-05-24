package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer marioX, marioY, levelX;
    private Byte hp;
    private Byte playerIndex;
    private Byte level;
    private Boolean victory;
    private Byte gameStatus;
    private ArrayList<Integer> deadMonsters;

    public Data(Byte playerIndex) {
        this.playerIndex = playerIndex;

    }

    public Data(Integer marioX, Integer marioY, Integer levelX, Byte hp, Byte playerIndex, ArrayList<Integer> deadMonsters, Byte level, Boolean victory) {
        this.marioX = marioX;
        this.marioY = marioY;
        this.levelX = levelX;
        this.deadMonsters = deadMonsters;
        this.hp = hp;
        this.playerIndex = playerIndex;
        this.level = level;
        this.victory = victory;

    }


    public Data(Byte playerIndex, Byte gameStatus) {
        this.playerIndex = playerIndex;
        this.gameStatus = gameStatus;
    }

    public Integer getMarioX() {
        return marioX;
    }

    public void setMarioX(Integer marioX) {
        this.marioX = marioX;
    }

    public Integer getMarioY() {
        return marioY;
    }

    public void setMarioY(Integer marioY) {
        this.marioY = marioY;
    }

    public Integer getLevelX() {
        return levelX;
    }

    public void setLevelX(Integer levelX) {
        this.levelX = levelX;
    }

    public Byte getHp() {
        return hp;
    }

    public void setHp(Byte hp) {
        this.hp = hp;
    }

    public Byte getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(Byte playerIndex) {
        this.playerIndex = playerIndex;
    }

    public ArrayList<Integer> getDeadMonsters() {
        return deadMonsters;
    }

    public void setDeadMonsters(ArrayList<Integer> deadMonsters) {
        this.deadMonsters = deadMonsters;
    }

    @Override
    public String toString() {
        return "Data{" +
                "marioX=" + marioX +
                ", marioY=" + marioY +
                ", levelX=" + levelX +
                ", hp=" + hp +
                ", playerIndex=" + playerIndex +
                ", deadMonsters=" + deadMonsters +
                '}';
    }

    public Byte getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(Byte gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Boolean getVictory() {
        return victory;
    }

    public void setVictory(Boolean victory) {
        this.victory = victory;
    }
}


