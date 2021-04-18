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
        HouseHold houseHold =
                currentSession().get(HouseHold.class, houseHoldId);

        Field field = currentSession().get(Field.class, fieldId);
        if (houseHold == null || field == null)
            throw new WebApplicationException("Column 'fieldId' or " +
                    "'houseHoldId' don't exists  ", 400);
        data.setHouseHold(houseHold);
        data.setField(field);
        currentSession().save(data);
        return data;
    }

    public AbstractData create(AbstractData data) {
        currentSession().save(data);
        return data;
    }

    public List<AbstractData> findData(Long hhId, String fieldId,
                                       LocalDateTime from,
                                       LocalDateTime to) {
        return list(namedQuery("AbstractData_findDataFromTo")
                .setParameter("hhId", hhId)
                .setParameter("fieldId", fieldId)
                .setParameter("from", from)
                .setParameter("to", to)
        );
    }
}
