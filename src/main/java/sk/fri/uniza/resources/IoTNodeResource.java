package sk.fri.uniza.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sk.fri.uniza.db.IotNodeDAO;
import sk.fri.uniza.model.IotNode;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class IoTNodeResource {

    private IotNodeDAO iotNodeDAO;

    public IoTNodeResource(IotNodeDAO iotNodeDAO) {
        this.iotNodeDAO = iotNodeDAO;
    }

    public IotNode createIotNode(IotNode iotNode) {
        return null;
    }

    public IotNode updateIotNode(IotNode iotNode) {
        return null;
    }

    public IotNode findIotNode(Long id) {
        return null;
    }

    public List<IotNode> allIotNodes() {
        return null;
    }

}
