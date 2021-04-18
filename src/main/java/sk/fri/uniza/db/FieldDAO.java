package sk.fri.uniza.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import sk.fri.uniza.model.Field;

import java.util.List;

public class FieldDAO extends AbstractDAO<Field> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public FieldDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Field create(Field field) {

        currentSession().save(field);
        return field;
    }

    public Field update(Field field)
    {
        return (Field) currentSession().merge(field);
    }

    public Field findById(String id) {
        return get(id);
    }

    public Field delete(String id) {
        Field field = get(id);
        currentSession().remove(field);
        return field;
    }

    public List<Field> allFields() {
        return list(namedQuery("Field_All"));
    }
}
