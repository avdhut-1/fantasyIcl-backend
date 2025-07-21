package com.cricMaster.fantasyICL_backend.etl.pdf.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchDetails {
    public LocalDate date;
    public LocalTime time;
    public String venue;
    public String teamA, teamB;
    public String tossWinner, tossDecision;
    public String firstInningsRaw, secondInningsRaw;
    public String resultSummary;

    public MatchDetails() {}

    public MatchDetails(LocalDate date, LocalTime time, String venue, String teamA, String teamB, String tossWinner, String tossDecision, String firstInningsRaw, String secondInningsRaw, String resultSummary) {
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.teamA = teamA;
        this.teamB = teamB;
        this.tossWinner = tossWinner;
        this.tossDecision = tossDecision;
        this.firstInningsRaw = firstInningsRaw;
        this.secondInningsRaw = secondInningsRaw;
        this.resultSummary = resultSummary;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
    }

    public String getTossDecision() {
        return tossDecision;
    }

    public void setTossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
    }

    public String getFirstInningsRaw() {
        return firstInningsRaw;
    }

    public void setFirstInningsRaw(String firstInningsRaw) {
        this.firstInningsRaw = firstInningsRaw;
    }

    public String getSecondInningsRaw() {
        return secondInningsRaw;
    }

    public void setSecondInningsRaw(String secondInningsRaw) {
        this.secondInningsRaw = secondInningsRaw;
    }

    public String getResultSummary() {
        return resultSummary;
    }

    public void setResultSummary(String resultSummary) {
        this.resultSummary = resultSummary;
    }
}
