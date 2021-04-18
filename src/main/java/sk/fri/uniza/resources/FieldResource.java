package sk.fri.uniza.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sk.fri.uniza.db.FieldDAO;
import sk.fri.uniza.model.Field;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Api("/field")
@Path("/field")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FieldResource {

    private FieldDAO fieldDAO;

    public FieldResource(FieldDAO fieldDAO) {
        this.fieldDAO = fieldDAO;
    }

    @POST
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Prídá nový typ Field")
    public Field createField(@Valid Field field) {
        return fieldDAO.create(field);
    }


    @PUT
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Upraví existujúci Field")
    public Field updateField(@Valid Field field) {
        return fieldDAO.update(field);
    }


    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @Path("{id}")
    @ApiOperation(value = "Zobrazí typ Field")
    public Field findField(@PathParam("id") String id) {
        return fieldDAO.findById(id);
    }


    @DELETE
    @UnitOfWork //Otvorí novú hibernate session
    @Path("{id}")
    @ApiOperation(value = "Vymaze Field")
    public Field deleteField(@PathParam("id") String id) {
        return fieldDAO.delete(id);
    }


    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Zobrazí všetky typy Field")
    public List<Field> allFields() {
        return fieldDAO.allFields();
    }
}
