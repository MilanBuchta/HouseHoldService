package sk.fri.uniza.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import sk.fri.uniza.db.IotNodeDAO;
import sk.fri.uniza.model.Field;
import sk.fri.uniza.model.HouseHold;
import sk.fri.uniza.model.IotNode;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Api("/IoTNode")
@Path("/IoTNode")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IoTNodeResource {

    private IotNodeDAO iotNodeDAO;

    public IoTNodeResource(IotNodeDAO iotNodeDAO) {
        this.iotNodeDAO = iotNodeDAO;
    }

    @POST /*JAX-RS*/
    @UnitOfWork //Otvorí novú hibernate session // Dropwizard
    @Path("{householdId}")
    @ApiOperation(value = "Pridanie novej IoTNode")
    public IotNode createIotNode(
            @ApiParam(value = "householdId", required = true)  @PathParam("householdId") Long id,
            @Valid IotNode iotNode) {
        return iotNodeDAO.create(id,iotNode);
    }

    @PUT /*JAX-RS*/
    @Path("{id}")
    @UnitOfWork //Otvorí novú hibernate session // Dropwizard
    @ApiOperation(value = "Úprava existujúcej node")
    public IotNode updateIotNode(
            @ApiParam(value = "ID", required = true)  @PathParam("id") Long id,
            @Valid IotNode iotNode) throws WebApplicationException {

        IotNode iotNodeDatabase = iotNodeDAO.findById(id);

        if(iotNodeDatabase == null) {
            throw new WebApplicationException("Unable to find IOT node", 400);
        }

        iotNode.setId(id);
        iotNode.setHouseHold(iotNodeDatabase.getHouseHold());
        return iotNodeDAO.update(iotNode);
    }

    @GET
    @Path("{id}")
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Ziskaj IoTnode na zaklade ID")
    public IotNode findIotNode(@PathParam("id") Long id) {
        return iotNodeDAO.findById(id);
    }

    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Zoznam všetkých nodes")
    public List<IotNode> allIotNodes() {
        return iotNodeDAO.allIotNodes();
    }

}
