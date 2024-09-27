package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 *
 */
public class Team {

    @FormParam("teamNr")
    private Integer teamNr;

    @FormParam("teamName")
    @NotEmpty
    @Size(min = 3, max = 50)
    private String teamName;

    @FormParam("anzahlTrophaeen")
    @NotEmpty
    private Integer amountOfTrophies;

    @FormParam("gruendungsdatum")

    private String foundingDate;

    @JsonIgnore
    private Tournament tournament;



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

    public Integer getAmountOfTrophies() {
        return amountOfTrophies;
    }

    public void setAmountOfTrophies(Integer amountOfTrophies) {
        this.amountOfTrophies = amountOfTrophies;
    }

    public String getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(String foundingDate) {
        this.foundingDate = foundingDate;
    }

    public Tournament getTurnier() {
        return tournament;
    }

    public void setTurnier(Tournament tournament) {
        this.tournament = tournament;
    }
}
