package ttps.spring.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration 
@EnableTransactionManagement 
@ComponentScan(basePackages = "ttps.spring")
public class PersistenceConfig {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUsername("ttps");
		driverManagerDataSource.setPassword("4qwvQMFK");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/buffet");
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return driverManagerDataSource;
	}

	/**
	 * El localContainerEntityManagerFactoryBean es un componente de Spring que
	 * facilita la configuración de la fábrica de administradores de entidades de
	 * JPA. Este bean permite la integración de JPA con el contenedor de Spring,
	 * proporcionando una forma sencilla de gestionar las entidades y las
	 * transacciones.
	 *
	 * @return
	 */

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("ttps.spring");
		emf.setEntityManagerFactoryInterface(jakarta.persistence.EntityManagerFactory.class);
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(jpaVendorAdapter);

        emf.setJpaPropertyMap(Map.of(
            "hibernate.hbm2ddl.auto", "update", 
            "hibernate.show_sql", "true", 
            "hibernate.format_sql", "true" 
        ));
		return emf;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
}
