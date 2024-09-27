package model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.List;

/**
 *
 */
public class Tournament {
    private Integer turnierNr;
    @FormParam("tournamentDescription")
    @NotEmpty
    @Size(min = 3, max = 50)
    private String tournamentDescription;
    @FormParam("startDate")
    @NotEmpty
    @Size(min = 10,max = 10)
    private String startDate;
    private List<Team> teamList;



    public Integer getTurnierNr() {
        return turnierNr;
    }

    public void setTurnierNr(Integer turnierNr) {
        this.turnierNr = turnierNr;
    }

    public String getTournamentDescription() {
        return tournamentDescription;
    }

    public void setTournamentDescription(String tournamentDescription) {
        this.tournamentDescription = tournamentDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

}
