package school21.spring.service.services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.TestApplicationConfig;

public class UsersServiceImplTest {

    static AnnotationConfigApplicationContext context;

    @BeforeAll
    public static void init(){
        context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
    }


    @Test
    void signUp() {
        UsersService bean = context.getBean(UsersService.class);

        String s = bean.signUp("newEmail@email.com");
        Assertions.assertNotEquals(s, "");
    }

    @AfterAll
    public static void  close(){
        context.close();;
    }
}
