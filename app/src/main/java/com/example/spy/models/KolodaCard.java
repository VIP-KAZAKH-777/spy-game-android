package com.example.spy.models;


public class KolodaCard {
    private static int players = 0;
    private String playerType;
    private String playerNum;
    private String playerLoc;
    private int img;

    public KolodaCard(){
       playerNum = "Player " + (++players);
    }

    public KolodaCard(String playerType, int img) {
        this.playerType = playerType;
        this.img = img;
        playerNum = "Player " + (++players);
    }

    public static void resetPLayers() {
        players = 0;
    }

    public String getPlayerLoc() {
        return playerLoc;
    }

    public void setPlayerLoc(String playerLoc) {
        this.playerLoc = playerLoc;
    }

    public String getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(String playerNum) {
        this.playerNum = playerNum;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
