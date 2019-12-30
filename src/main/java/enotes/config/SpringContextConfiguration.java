package enotes.config;

import enotes.db.ConnectionManager;
import enotes.db.JdbcConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringContextConfiguration {

    @Bean
    public ConnectionManager getConnectionManager() {
        return new JdbcConnectionManager();
    }
}
