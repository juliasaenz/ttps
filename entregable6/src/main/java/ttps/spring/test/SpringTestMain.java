package ttps.spring.test;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ttps.spring.config.PersistenceConfig;
import ttps.spring.model.MiPrimerEntity;

public class SpringTestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(PersistenceConfig.class);

        ctx.refresh();

        try {
            DataSource dataSource = ctx.getBean(DataSource.class);
            System.out.println("DataSource cargado: " + dataSource);

            try (Connection conn = dataSource.getConnection()) {
                System.out.println("Connection successfully opened and will be closed.");
            }

            MiPrimerEntity e = ctx.getBean("prueba", MiPrimerEntity.class);
            System.out.println("Bean cargado: " + e.hola());
        } catch (Exception ex) {
            System.err.println("Error during DataSource or Bean retrieval: " + ex.getMessage());
        } finally {
            ctx.close();
        }
    }
}
