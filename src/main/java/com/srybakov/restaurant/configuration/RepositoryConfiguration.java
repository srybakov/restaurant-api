package com.srybakov.restaurant.configuration;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
import com.srybakov.restaurant.ApplicationConstants;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {ApplicationConstants.MODEL_PACKAGE})
@EnableJpaRepositories(basePackages = {ApplicationConstants.REPOSITORY_PACKAGE})
@EnableTransactionManagement
public class RepositoryConfiguration {
}