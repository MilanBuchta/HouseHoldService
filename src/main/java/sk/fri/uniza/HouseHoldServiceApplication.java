package sk.fri.uniza;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import sk.fri.uniza.core.DateParameterConverterProvider;
import sk.fri.uniza.db.DataDAO;
import sk.fri.uniza.db.FieldDAO;
import sk.fri.uniza.db.HouseHoldDAO;
import sk.fri.uniza.db.IotNodeDAO;
import sk.fri.uniza.health.DatabaseHealthCheck;
import sk.fri.uniza.model.*;
import sk.fri.uniza.resources.FieldResource;
import sk.fri.uniza.resources.HouseHoldResource;
import sk.fri.uniza.resources.IoTNodeResource;
import sk.fri.uniza.health.DeleteHealthCheck;

public class HouseHoldServiceApplication
        extends Application<HouseHoldServiceConfiguration> {

    // Vytvorenie Hibernate baliká: tento balík kombinuje objekt určený na
    // nastavenie Hibernat a samotnú knižnicu Hibernate
    private final HibernateBundle<HouseHoldServiceConfiguration> hibernate =
            // Všetky triedy(v žargóne Hibernate sú označované ako Entity),
            // ktoré tvoria model musia byť prídané do Bundle
            new HibernateBundle<HouseHoldServiceConfiguration>(
                    HouseHold.class,
                    IotNode.class,
                    Field.class,
                    DataDouble.class,
                    DataString.class,
                    DataInteger.class,
                    ContactPerson.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(
                        HouseHoldServiceConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public static void main(final String[] args) throws Exception {
        new HouseHoldServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "HouseHoldService";
    }

    // V rámci životného cyklu, je táto metóda zavolaná hneď na úvod.
    // V tejto metóde sa pridávajú Bundle objekty, poprípade objekty Commands
    @Override
    public void initialize(
            final Bootstrap<HouseHoldServiceConfiguration> bootstrap) {
        // Pripojený Swagger dokumentačný nástroj
        bootstrap.addBundle(new SwaggerBundle<HouseHoldServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    HouseHoldServiceConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });

        // Pripojený balík Hibernate (ORM databáza)
        bootstrap.addBundle(hibernate);
    }

    // V rámci životného cyklu, je táto metóda zavolaná až po metóde initialize.
    // Slúži na pridanie poskytovteľov rôznych podporných služieb, zdrojov
    // tvoriacich REST API rozhranie servera a pod.
    @Override
    public void run(final HouseHoldServiceConfiguration configuration,
                    final Environment environment) {

        // Vytvorené objekty na prístup k databáze
        final HouseHoldDAO houseHoldDAO =
                new HouseHoldDAO(hibernate.getSessionFactory());
        final DataDAO dataDAO =
                new DataDAO(hibernate.getSessionFactory());
        final FieldDAO fieldDAO =
                new FieldDAO(hibernate.getSessionFactory());
        // Vytvorené objekty reprezentujúce REST rozhranie
        environment.jersey()
                .register(new HouseHoldResource(houseHoldDAO, dataDAO));
        environment.jersey()
                .register(new FieldResource(fieldDAO));
        environment.jersey()
                .register(new DateParameterConverterProvider());


        // Vytvorenie Healthcheck (overenie zdravia aplikácie), ktorý
        // využijeme na otestovanie databázy

        UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory =
                new UnitOfWorkAwareProxyFactory(hibernate);

        final DatabaseHealthCheck databaseHealthCheck =
                 unitOfWorkAwareProxyFactory
                        .create(DatabaseHealthCheck.class,
                                new Class[]{HouseHoldDAO.class,
                                        IotNodeDAO.class, FieldDAO.class,
                                        DataDAO.class},
                                new Object[]{houseHoldDAO, null,
                                        fieldDAO, dataDAO
                                });

        final DeleteHealthCheck deleteHealthCheck =
                unitOfWorkAwareProxyFactory
                        .create(DeleteHealthCheck.class,
                                FieldDAO.class,
                                fieldDAO);

        // Zaregistrovanie Healthcheck
        environment.healthChecks()
                .register("databaseHealthcheck", databaseHealthCheck);

        environment.healthChecks()
                .register("deleteHealthcheck", deleteHealthCheck);


        // Spustenie všetkých health kontrol
        environment.healthChecks().runHealthCheck("databaseHealthcheck");
        environment.healthChecks().runHealthCheck("deleteHealthcheck");
    }

}
