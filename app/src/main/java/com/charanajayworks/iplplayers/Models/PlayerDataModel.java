package com.charanajayworks.iplplayers.Models;

public class PlayerDataModel {
    public PlayerDataModel(String playerName, String imageUrl, String badge1, String badge2, String playerRole, String battingStyle, String bowlingStyle, String nationality, String dateOfBirth, String iplDebut, String playerDescription) {
        this.playerName = playerName;
        this.imageUrl = imageUrl;
        this.badge1 = badge1;
        this.badge2 = badge2;
        this.playerRole = playerRole;
        this.battingStyle = battingStyle;
        this.bowlingStyle = bowlingStyle;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.iplDebut = iplDebut;
        this.playerDescription = playerDescription;
    }

    private String playerName, imageUrl,badge1, badge2, playerRole, battingStyle, bowlingStyle, nationality, dateOfBirth, iplDebut, playerDescription;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBadge1() {
        return badge1;
    }

    public void setBadge1(String badge1) {
        this.badge1 = badge1;
    }

    public String getBadge2() {
        return badge2;
    }

    public void setBadge2(String badge2) {
        this.badge2 = badge2;
    }

    public String getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }

    public String getBattingStyle() {
        return battingStyle;
    }

    public void setBattingStyle(String battingStyle) {
        this.battingStyle = battingStyle;
    }

    public String getBowlingStyle() {
        return bowlingStyle;
    }

    public void setBowlingStyle(String bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIplDebut() {
        return iplDebut;
    }

    public void setIplDebut(String iplDebut) {
        this.iplDebut = iplDebut;
    }

    public String getPlayerDescription() {
        return playerDescription;
    }

    public void setPlayerDescription(String playerDescription) {
        this.playerDescription = playerDescription;
    }
}
