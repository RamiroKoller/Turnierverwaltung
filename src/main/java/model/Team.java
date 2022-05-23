package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import data.DataHandler;

import java.time.LocalDate;

/**
 *
 */
public class Team {
    private Integer teamNr;
    private String teamName;
    private Integer anzahlTrophaeen;
    private LocalDate gruendungsdatum;
    @JsonIgnore
    private Turnier turnier;



    public Integer getTeamNr() {
        return teamNr;
    }

    public void setTeamNr(Integer teamNr) {
        this.teamNr = teamNr;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getAnzahlTrophaeen() {
        return anzahlTrophaeen;
    }

    public void setAnzahlTrophaeen(Integer anzahlTrophaeen) {
        this.anzahlTrophaeen = anzahlTrophaeen;
    }

    public LocalDate getGruendungsdatum() {
        return gruendungsdatum;
    }

    public void setGruendungsdatum(LocalDate gruendungsdatum) {
        this.gruendungsdatum = gruendungsdatum;
    }

    public Turnier getTurnier() {
        return turnier;
    }

    public void setTurnier(Turnier turnier) {
        this.turnier = turnier;
    }
}
