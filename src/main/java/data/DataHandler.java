package data;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Team;
import model.Turnier;
import service.Config;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    private static void writeTeamJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String teamPath = Config.getProperty("teamJSON");
        try {
            fileOutputStream = new FileOutputStream(teamPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getInstance().teamList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void writeTournamentJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("turnierJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getInstance().turnierList);
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

    public static void insertTeam(Team team){
        getInstance().getTeamList().add(team);
        writeTeamJSON();
    }

    public static void updateTeam() {
        writeTeamJSON();
    }


    public boolean deleteTeam(Integer teamNr){
        Team team = readTeamByNr(teamNr);
        if (team != null){
            getTeamList().remove(team);
            writeTeamJSON();
            return true;
        }
        return false;
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

    public static void insertTournament(Turnier turnier) {
        getInstance().getTurnierList().add(turnier);
        writeTournamentJSON();
    }

    /**
     * updates the tournamentList
     */
    public static void updateTournament() {
        writeTournamentJSON();
    }

    /**
     * deletes a Tournament identified by the turnierNr
     * @return  success=true/false
     */
    public static boolean deleteTournament(Integer turnierNr) {
        Turnier turnier = getInstance().readTournamentByNumber(turnierNr);
        if (turnier != null){
            getInstance().getTurnierList().remove(turnier);
            writeTournamentJSON();
            return true;
        }else {
            return false;
        }
    }







}


