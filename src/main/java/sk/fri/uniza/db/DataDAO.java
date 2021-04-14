package sk.fri.uniza.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import sk.fri.uniza.model.AbstractData;
import sk.fri.uniza.model.Field;
import sk.fri.uniza.model.HouseHold;

import javax.ws.rs.WebApplicationException;
import java.time.LocalDateTime;
import java.util.List;

public class DataDAO extends AbstractDAO<AbstractData> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public DataDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public AbstractData create(Long houseHoldId, String fieldId,
                               AbstractData data) throws
            WebApplicationException {
        return null;
    }

    public AbstractData create(AbstractData data) {
        return null;
    }

    public List<AbstractData> findData(Long hhId, String fieldId,
                                       LocalDateTime from,
                                       LocalDateTime to) {
        return null;
    }
}
