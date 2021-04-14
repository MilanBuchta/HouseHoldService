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

public class HouseHoldServiceApplication
        extends Application<HouseHoldServiceConfiguration> {


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

    }

    // V rámci životného cyklu, je táto metóda zavolaná až po metóde initialize.
    // Slúži na pridanie poskytovteľov rôznych podporných služieb, zdrojov
    // tvoriacich REST API rozhranie servera a pod.
    @Override
    public void run(final HouseHoldServiceConfiguration configuration,
                    final Environment environment) {

    }

}
