package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Team;
import model.Turnier;
import service.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static DataHandler instance = null;
    private List<Team> teamList;
    private List<Turnier> turnierList;


    private DataHandler(){
        setTeamList(new ArrayList<>());
        readTeamJSON();
        setTurnierList(new ArrayList<>());
        readTurnierJSON();
    }


    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }

    public static void setInstance(DataHandler instance) {
        DataHandler.instance = instance;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public List<Turnier> getTurnierList() {
        return turnierList;
    }

    public void setTurnierList(List<Turnier> turnierList) {
        this.turnierList = turnierList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    private void readTeamJSON() {
        try {
            String path = Config.getProperty("teamJSON");
            byte[]jsonData = Files.readAllBytes(Paths.get(path));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            Team[] teams = objectMapper.readValue(jsonData,Team[].class);
            for (Team team : teams){
                getTeamList().add(team);
            }

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    private void readTurnierJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("turnierJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            Turnier[] turniere = objectMapper.readValue(jsonData,Turnier[].class);
            for (Turnier turnier : turniere){
                getTurnierList().add(turnier);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Team> readAllTeams() {
        return getTeamList();
    }

    public Team readTeamByNr(Integer teamNr){
        Team team = null;
        for (Team entry : getTeamList()) {
            if (entry.getTeamNr().equals(teamNr)) {
                team = entry;
            }
        }
        return team;
    }

    public List<Turnier> readAllTournaments(){
        return getTurnierList();
    }

    public Turnier readTournamentByNumber(Integer turnierNr) {
        Turnier turnier = null;
        for (Turnier entry : getTurnierList()){
            if (entry.getTurnierNr().equals(turnierNr)) {
                turnier = entry;
            }
        }
        return turnier;
    }





}


