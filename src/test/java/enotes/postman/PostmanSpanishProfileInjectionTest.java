package enotes.postman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:/spring/spring-configuration.xml",
        "classpath:/spring/letter-spring-configuration.xml",
        "classpath:/spring/spanish-spring-configuration.xml"
})
@Profile("spanish")
public class PostmanSpanishProfileInjectionTest {

    @Autowired
    Postman postman;

    @Test
    public void postmanShouldHaveAllHisBeanInjected() {
        postman.readLetters(); //simple check: presence of NPE means troubles with beans injection
    }
}
