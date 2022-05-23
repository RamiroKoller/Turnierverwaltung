package model;

import model.Team;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public class Turnier {
    private Integer turnierNr;
    private String turnierBezeichnung;
    private LocalDate startDatum;
    private List<Team> teamList;



    public Integer getTurnierNr() {
        return turnierNr;
    }

    public void setTurnierNr(Integer turnierNr) {
        this.turnierNr = turnierNr;
    }

    public String getTurnierBezeichnung() {
        return turnierBezeichnung;
    }

    public void setTurnierBezeichnung(String turnierBezeichnung) {
        this.turnierBezeichnung = turnierBezeichnung;
    }

    public LocalDate getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(LocalDate startDatum) {
        this.startDatum = startDatum;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

}
