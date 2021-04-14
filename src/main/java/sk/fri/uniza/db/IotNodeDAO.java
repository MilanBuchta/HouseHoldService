package sk.fri.uniza.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import sk.fri.uniza.model.IotNode;

import java.util.List;

public class IotNodeDAO extends AbstractDAO<IotNodeDAO> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public IotNodeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public IotNode create(IotNode iotNode) {
        currentSession().save(iotNode);
        return iotNode;
    }

    public IotNode findById(Long id) {
        //TODO Doplniť
        return null;
    }

    public IotNode update(IotNode iotNode) {
        //TODO Doplniť
        return null;
    }

    public List<IotNode> findByHouseHold(Long houseHoldId) {
        //TODO Doplniť
        return null;
    }

    public List<IotNode> allIotNodes() {
        return null;
    }
}
