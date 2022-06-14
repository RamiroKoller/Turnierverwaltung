package service;

import data.DataHandler;
import model.Team;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.ws.rs.*;
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

    /**
     * inserts a new book
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertBook(
            @Valid @BeanParam Team team,
            @NotEmpty)

     {

         team.setTeamNr(team.getTeamNr());
         
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
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBook(
            @Valid @BeanParam Team team,
            @NotEmpty)
    {
        int httpStatus = 200;
        Team oldTeam = DataHandler.getInstance().readTeamByNr(team.getTeamNr());
        if (oldTeam != null) {
            oldTeam.setTitle(book.getTitle());
            oldTeam.setAuthor(book.getAuthor());
            oldTeam.setPublisherUUID(publisherUUID);
            oldTeam.setPrice(book.getPrice());
            oldTeam.setIsbn(book.getIsbn());
            oldTeam.setRelease(book.getRelease());

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
