package sk.fri.uniza.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;
import sk.fri.uniza.api.FilterEnum;
import sk.fri.uniza.core.DateTimeFormat;
import sk.fri.uniza.db.DataDAO;
import sk.fri.uniza.db.HouseHoldDAO;
import sk.fri.uniza.model.AbstractData;
import sk.fri.uniza.model.HouseHold;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;

// Určené na konfigurovanie swagger dokumentačného nástroja
@SwaggerDefinition(
        info = @Info(
                description = "HouseHold data",
                version = "V1.0.0",
                title = "HouseHold service",
                contact = @Contact(
                        name = "Martin Húdik",
                        email = "martin.hudik@fri.uniza.sk"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP}

)
@Api("/household") // Swagger
@Path("/household") // Koreňová adresa kolekcie koncových bodov
// pre prístup k zdrojom domácností // Súčasť JAX-RS
@Produces(MediaType.APPLICATION_JSON)// Výstupné dáta sú vo forme JSON //JAX-RS
@Consumes(MediaType.APPLICATION_JSON) //Vstupné dáta sú vo forme JSON //JAX-RS
public class HouseHoldResource {

    private HouseHoldDAO houseHoldDAO;
    private DataDAO dataDAO;

    public HouseHoldResource(HouseHoldDAO houseHoldDAO,
                             DataDAO dataDAO) {
        this.houseHoldDAO = houseHoldDAO;
        this.dataDAO = dataDAO;
    }

    @POST // JAX-RS
    @Path("{householdID}/{fieldID}") // JAX-RS
    @UnitOfWork //Otvorí novú hibernate session //Dropwizzard
    @ApiOperation(value = "Pridá nové dáta") // Swagger
    public AbstractData addData(@PathParam("householdID")/* JAX-RS */ Long hhId,
                                @PathParam("fieldID")/*JAX-RS*/ String fieldID,
                                AbstractData data) {
        return dataDAO.create(hhId, fieldID, data);
    }


    @GET // JAX-RS
    @Path("{householdID}/{fieldID}") // JAX-RS
    @UnitOfWork //Otvorí novú hibernate session // Dropwizzard
    @ApiOperation(value = "Získanie dát o konkrétnej domácnosti a konkrétneho" +
            " typu") // Swagger
    public List<AbstractData> getData(
            @PathParam("householdID")/*JAX-RS*/ Long hhId,
            @PathParam("fieldID")/*JAX-RS*/ String fieldID,
            @QueryParam("from") /*JAX-RS*/
            @DateTimeFormat("dd/MM/yyyy HH:mm")
            /*VLASTNÉ*/
            @ApiParam(format = "dd/MM/yyyy HH:mm")
            //SWAGGER
            final LocalDateTime from,
            @QueryParam("to")
            @DateTimeFormat("dd/MM/yyyy HH:mm")
            @ApiParam(format = "dd/MM/yyyy HH:mm") final LocalDateTime to) {
        return dataDAO.findData(hhId, fieldID, from, to);
    }

    @POST /*JAX-RS*/
    @UnitOfWork //Otvorí novú hibernate session // Dropwizard
    @ApiOperation(value = "Pridanie novej domácnosti")
    public HouseHold createHouseHold(@Valid HouseHold houshold) {
        return houseHoldDAO.create(houshold);
    }

    @PUT /*JAX-RS*/
    @Path("{id}") /*JAX-RS*/
    @UnitOfWork //Otvorí novú hibernate session // Dropwizard
    @ApiOperation(value = "Úprava existujúcej domácnosti")
    public HouseHold updateHouseHold(
            @ApiParam(value = "ID", required = true) @PathParam("id") Long id,
            @Valid HouseHold houshold) {
        houshold.setId(id);
        return houseHoldDAO.update(houshold);
    }


    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Zoznam všetkých domácnosti")
    public List<HouseHold> listHouseHold() {
        return houseHoldDAO.findAll();
    }

    @GET //HTTP metóda
    @Path("{id}") // Jedna vetva hlavnej adresy /household
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Údaje o konkrétnej domácnosť")
    public HouseHold getHouseHold(
            @ApiParam(required = true)
            @PathParam("id") Long id
    ) {
        return houseHoldDAO.findById(id);
    }

    @GET
    @Path("filter")
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Vyfiltrovaný zoznam domácnosti")
    public List<HouseHold> filterHouseHold(
            @QueryParam("filter") FilterEnum filter,
            @QueryParam("value") String value) {

        switch (filter) {
            case zip:
                return houseHoldDAO.findByZip(value);
            case firstName:
                return houseHoldDAO.findByFirstName(value);
            case lastName:
                return houseHoldDAO.findByLastName(value);
        }
        return null;
    }

}
