package service;

import data.DataHandler;
import model.Team;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

@Path("team")
public class TeamService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTeams(){
        List<Team> teamList = DataHandler.getInstance().readAllTeams();
        return Response.status(200).entity(teamList).build();
    }
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTeams(
            @QueryParam("nr") Integer teamNr
    ){
        Team team = DataHandler.getInstance().readTeamByNr(teamNr);
        return Response
                .status(200)
                .entity(team)
                .build();
    }
}