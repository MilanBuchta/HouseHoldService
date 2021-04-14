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
        return null;
    }

    public Field update(Field field) {
        return null;
    }

    public Field findById(String id) {
        return null;
    }

    public Field delete(String id) {
        return null;
    }

    public List<Field> allFields() {
        return null;
    }
}
