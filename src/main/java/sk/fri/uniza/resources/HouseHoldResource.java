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

public class HouseHoldResource {

    private HouseHoldDAO houseHoldDAO;
    private DataDAO dataDAO;

    public HouseHoldResource(HouseHoldDAO houseHoldDAO,
                             DataDAO dataDAO) {
        this.houseHoldDAO = houseHoldDAO;
        this.dataDAO = dataDAO;
    }


    public AbstractData addData(Long hhId,
                                String fieldID,
                                AbstractData data) {
        return null;
    }


    public List<AbstractData> getData(Long hhId,
                                      String fieldID,
                                      final LocalDateTime from,
                                      final LocalDateTime to) {
        return null;
    }


    public HouseHold createHouseHold(HouseHold houshold) {
        return null;
    }

    public HouseHold updateHouseHold(
            Long id,
            HouseHold houshold) {
        houshold.setId(id);
        return null;
    }


    public List<HouseHold> listHouseHold() {
        return null;
    }


    public HouseHold getHouseHold(
            Long id) {
        return null;
    }


    public List<HouseHold> filterHouseHold(
            FilterEnum filter,
            String value) {

        return null;
    }

}
