package ttps.spring.test;

import javax.sql.DataSource;
import java.sql.Connection;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ttps.spring.config.PersistenceConfig;
import ttps.spring.model.MiPrimerEntity;

public class SpringTestMain {
    public static void main(String[] args) {
        // Create a new AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        // Register the configuration class (PersistenceConfig in this case)
        ctx.register(PersistenceConfig.class);

        // Refresh the context to initialize Spring beans
        ctx.refresh();

        try {
            DataSource dataSource = ctx.getBean(DataSource.class);
            System.out.println("DataSource cargado: " + dataSource);

            // Open and immediately close a connection to test DataSource resource management
            try (Connection conn = dataSource.getConnection()) {
                System.out.println("Connection successfully opened and will be closed.");
            }

            // Retrieve and print your MiPrimerEntity bean
            MiPrimerEntity e = ctx.getBean("prueba", MiPrimerEntity.class);
            System.out.println("Bean cargado: " + e.hola());
        } catch (Exception ex) {
            System.err.println("Error during DataSource or Bean retrieval: " + ex.getMessage());
        } finally {
            // Ensure the context is closed to free resources
            ctx.close();
        }
    }
}
