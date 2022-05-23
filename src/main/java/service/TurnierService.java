package service;

import data.DataHandler;
import model.Team;
import model.Turnier;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

@Path("turnier")
public class TurnierService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTurniere(){
        List<Turnier> turnierList = DataHandler.getInstance().readAllTournaments();
        return Response.status(200).entity(turnierList).build();
    }
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTurniere(
            @QueryParam("nr") Integer turnierNr
    ){
        Turnier turnier = DataHandler.getInstance().readTournamentByNumber(turnierNr);
        return Response
                .status(200)
                .entity(turnier)
                .build();
    }
}