package sk.fri.uniza.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import sk.fri.uniza.model.HouseHold;
import sk.fri.uniza.model.IotNode;

import javax.ws.rs.WebApplicationException;
import java.util.List;



public class IotNodeDAO extends AbstractDAO<IotNode> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public IotNodeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public IotNode create(Long houseHoldId,IotNode iotNode) throws
            WebApplicationException {

        HouseHold houseHold =
                currentSession().get(HouseHold.class, houseHoldId);

        if (houseHold == null) {
            throw new WebApplicationException("HouseHoldD  doesnt exist ", 400);
        }

        iotNode.setHouseHold(houseHold);
        currentSession().save(iotNode);
        return iotNode;
    }

    public IotNode findById(Long id) {
        return get(id);
    }

    public IotNode update(IotNode iotNode) {
        return (IotNode) currentSession().merge(iotNode);
    }

    public List<IotNode> findByHouseHold(Long houseHoldId) {
        return list(namedQuery("IoTNode_findByhouseHoldId")
                .setParameter("houseHoldId", houseHoldId));
    }

    public List<IotNode> allIotNodes() {
        return list(namedQuery("IoTNode_All"));
    }
}
