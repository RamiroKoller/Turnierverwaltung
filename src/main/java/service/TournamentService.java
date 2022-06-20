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
    public Response listTurniere(){
        List<Tournament> tournamentList = DataHandler.getInstance().readAllTournaments();
        return Response.status(200).entity(tournamentList).build();
    }
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTurniere(
            @QueryParam("nr") Integer turnierNr
    ){
        Tournament tournament = DataHandler.getInstance().readTournamentByNumber(turnierNr);
        return Response
                .status(200)
                .entity(tournament)
                .build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertTeam(
            @Valid @BeanParam Tournament tournament
    )

    {
        Random rn = new Random();
        int maximum = 99999;
        int minimum= 1;
        int n = maximum - minimum + 1;
        int i = rn.nextInt() % n;

        tournament.setTurnierNr(minimum + i);



        DataHandler.getInstance().insertTournament(tournament);
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
            @FormParam("tournamentNr") Integer tournamentNr,
            @FormParam("tournamentDescription") String tournamentDescription,
            @FormParam("startDate") String startDate
    )
    {
        int httpStatus = 200;
        Tournament oldTournament = DataHandler.getInstance().readTournamentByNumber(tournamentNr);
        if (oldTournament != null) {
            oldTournament.setTurnierNr(tournamentNr);
            oldTournament.setTournamentDescription(tournamentDescription);
            oldTournament.setStartDate(startDate);


            DataHandler.getInstance().updateTournament();
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
    public Response deleteTournament(
            @QueryParam("nr") Integer tournamentNr
    ){
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteTournament(tournamentNr)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}