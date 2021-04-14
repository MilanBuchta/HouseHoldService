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

public class FieldResource {

    private FieldDAO fieldDAO;

    public FieldResource(FieldDAO fieldDAO) {
        this.fieldDAO = fieldDAO;
    }

    public Field createField(Field field) {
        return null;
    }


    public Field updateField(Field field) {
        return null;
    }


    public Field findField(String id) {
        return null;
    }


    public Field deleteField(String id) {
        return null;
    }


    public List<Field> allFields() {
        return null;
    }
}
