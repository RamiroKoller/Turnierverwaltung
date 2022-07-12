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
    public Response listTeams(
            @CookieParam("userRole") String userRole
    ){
        int httpStatus;
        List<Team> teamList = null;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 401;
        }else {
        teamList = DataHandler.getInstance().readAllTeams();
        httpStatus = 200;
       }
        return Response.status(httpStatus).entity(teamList).build();
    }
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readTeam(
            @QueryParam("nr") Integer teamNr,
            @CookieParam("userRole") String userRole
    ){
        Team team = null;
        int httpStatus;

        if (userRole == null || userRole.equals("guest")){
            httpStatus = 401;
        }else {
            httpStatus = 200;
            team = DataHandler.getInstance().readTeamByNr(teamNr);
        }
        return Response
                .status(httpStatus)
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
            @Valid @BeanParam Team team,
            @CookieParam("userRole") String userRole
            ){
              int httpStatus;

               if (userRole == null || userRole.equals("guest")|| userRole.equals("user")){
               httpStatus = 401;
               }else {
                   httpStatus = 200;
                Random rn = new Random();
                int maximum = 99999;
                int minimum= 1;
                int n = maximum - minimum + 1;
                int i = rn.nextInt() % n;
                team.setTeamNr(minimum + i);
                DataHandler.insertTeam(team);
               }

        return Response
                .status(httpStatus)
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
            @Valid @BeanParam Team team,
            @CookieParam("userRole") String userRole
    ){
        int httpStatus;

        if (userRole == null || userRole.equals("guest")|| userRole.equals("user")){
            httpStatus = 401;
        }else {
            httpStatus = 200;
            Team oldTeam = DataHandler.getInstance().readTeamByNr(team.getTeamNr());
            if (oldTeam != null) {
                oldTeam.setTeamNr(team.getTeamNr());
                oldTeam.setTeamName(team.getTeamName());
                oldTeam.setAmountOfTrophies(team.getAmountOfTrophies());
                oldTeam.setFoundingDate(team.getFoundingDate());
                DataHandler.updateTeam();
            } else {
                httpStatus = 410;
            }
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
            @QueryParam("nr") Integer teamNr,
            @CookieParam("userRole") String userRole
    ){
        int httpStatus;

        if (userRole == null || userRole.equals("guest")|| userRole.equals("user")){
            httpStatus = 401;
        }else {
            httpStatus = 200;
            if (!DataHandler.getInstance().deleteTeam(teamNr)){
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
