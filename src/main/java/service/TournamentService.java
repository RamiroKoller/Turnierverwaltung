package service;

import data.DataHandler;
import model.Tournament;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Random;

@Path("tournament")
public class TournamentService{

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTurniere(
            @CookieParam("userRole") String userRole
    ){
        int httpStatus;
        List<Tournament> tournamentList = null;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 401;
        } else {
            tournamentList = DataHandler.getInstance().readAllTournaments();
            httpStatus = 200;
        }
        return Response.status(httpStatus).entity(tournamentList).build();
    }
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTurniere(
            @QueryParam("nr") Integer turnierNr,
             @CookieParam("userRole") String userRole
    ){
        int httpStatus;
        Tournament tournament = null;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 401;
        }else {
            httpStatus = 200;
            tournament = DataHandler.getInstance().readTournamentByNumber(turnierNr);
        }
            return Response
                .status(httpStatus)
                .entity(tournament)
                .build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertTeam(
            @Valid @BeanParam Tournament tournament,
             @CookieParam("userRole") String userRole
    )
    {
        int httpStatus;
        if (userRole == null || userRole.equals("guest") || userRole.equals("user")) {
            httpStatus = 401;


        }else {
            httpStatus = 200;
            Random rn = new Random();
            int maximum = 99999;
            int minimum= 1;
            int n = maximum - minimum + 1;
            int i = rn.nextInt() % n;

            tournament.setTurnierNr(minimum + i);
        }



        DataHandler.getInstance().insertTournament(tournament);
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
            @FormParam("tournamentNr") Integer tournamentNr,
            @FormParam("tournamentDescription") String tournamentDescription,
            @FormParam("startDate") String startDate,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        Tournament oldTournament = DataHandler.getInstance().readTournamentByNumber(tournamentNr);
        if (userRole == null || userRole.equals("guest") || userRole.equals("user")) {
            httpStatus = 401;
        } else {
            if (oldTournament != null) {
                httpStatus = 200;
                oldTournament.setTurnierNr(tournamentNr);
                oldTournament.setTournamentDescription(tournamentDescription);
                oldTournament.setStartDate(startDate);

                DataHandler.getInstance().updateTournament();
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
    public Response deleteTournament(
            @QueryParam("nr") Integer tournamentNr,
            @CookieParam("userRole") String userRole
    ){
        int httpStatus;

        if (userRole == null || userRole.equals("guest")|| userRole.equals("user")){
            httpStatus = 401;
        }else {
            httpStatus = 200;
        }
        if (!DataHandler.getInstance().deleteTournament(tournamentNr)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}