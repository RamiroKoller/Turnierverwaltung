package service;

import data.DataHandler;
import model.Team;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Random;

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
    public Response readTeam(
            @QueryParam("nr") Integer teamNr
    ){
        Team team = DataHandler.getInstance().readTeamByNr(teamNr);
        return Response
                .status(200)
                .entity(team)
                .build();
    }

    /**
     * inserts a new book
     * @return Response
     */
   @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertTeam(
            @Valid @BeanParam Team team
            )

            {
                Random rn = new Random();
                int maximum = 99999;
                int minimum= 1;
                int n = maximum - minimum + 1;
                int i = rn.nextInt() % n;

                team.setTeamNr(minimum + i);

        DataHandler.insertTeam(team);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new book
     * @return Response
     */
    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBook(
            @FormParam("teamNr") Integer teamNr,
            @FormParam("teamName") String teamName,
            @FormParam("anzahlTrophaeen") Integer anzahlTrophaeen,
            @FormParam("gruendungsdatum") String gruendungsDatum
            )
    {
        int httpStatus = 200;
        Team oldTeam = DataHandler.getInstance().readTeamByNr(teamNr);
        if (oldTeam != null) {
            oldTeam.setTeamNr(teamNr);
            oldTeam.setTeamName(teamName);
            oldTeam.setAmountOfTrophies(anzahlTrophaeen);
            oldTeam.setFoundingDate(gruendungsDatum);

            DataHandler.updateTeam();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }


    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteTeam(
            @QueryParam("nr") Integer teamNr
    ){
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteTeam(teamNr)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
