package com.charanajayworks.iplplayers.Models;

public class ScheduleDataModel {
    public String matchNumberString;

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String matchDate;

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public String team1Name;

    public String team2Name;

    public String getMatchNumberString() {
        return matchNumberString;
    }

    public void setMatchNumberString(String matchNumberString) {
        this.matchNumberString = matchNumberString;
    }

    public String getTimingString() {
        return timingString;
    }

    public void setTimingString(String timingString) {
        this.timingString = timingString;
    }

    public String getVenueString() {
        return venueString;
    }

    public void setVenueString(String venueString) {
        this.venueString = venueString;
    }

    public String timingString;
    public String venueString;

    public ScheduleDataModel(String matchNumberString,String matchDate,String team1Name,String team2Name, String timingString, String venueString) {
        this.matchNumberString = matchNumberString;
        this.timingString = timingString;
        this.venueString = venueString;
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.matchDate = matchDate;
    }


}
