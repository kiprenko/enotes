package enotes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:configuration/base.properties")
public class SpringContextConfiguration {

}
